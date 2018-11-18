#!/bin/bash
set -e

replicas_count=0

for replication in $(cat $REPLICATION_USERS_FILE); do
    USER=$(echo $replication | cut -d ":" -f2)
    PASS=$(echo $replication | cut -d ":" -f3)
    
    echo Adding $USER with $PASS
    # Allow replication user to connect
    # TODO(bgulowaty): current mask allows all addresses
    # TODO(bgulowaty): should allow only replica address 
    echo "host replication $USER 0.0.0.0/0 scram-sha-256" >> "$PGDATA/pg_hba.conf"

    # Create replication user
    psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
	SET password_encryption = 'scram-sha-256';
	CREATE ROLE $USER WITH REPLICATION PASSWORD '$PASS' LOGIN;
	EOSQL
    replicas_count=$((replicas_count+1))
done;

# Configure master
cat >> ${PGDATA}/postgresql.conf <<EOF
listen_addresses= '*'
wal_level = replica
max_wal_senders = $((replicas_count+2))
max_replication_slots = $replicas_count
synchronous_commit = off
EOF

# Restart server
pg_ctl -D ${PGDATA} -m fast -w restart

# Add replica slots (must be executed after restart)
for replication in $(cat $REPLICATION_USERS_FILE); do
	SLOT=$(echo $replication | cut -d ":" -f1)
	echo Adding replication slot $SLOT

	psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
	SELECT * FROM pg_create_physical_replication_slot('$SLOT');
	EOSQL
done;
