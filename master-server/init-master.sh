#!/bin/bash
set -e

# Add support for multiple replicas

# Allow replication user to connect
# TODO(bgulowaty): current mask allows all addresses
# TODO(bgulowaty): should allow only replica address 
echo "host replication $REPLICATION_USER 0.0.0.0/0 scram-sha-256" >> "$PGDATA/pg_hba.conf"

# Create replication user
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
SET password_encryption = 'scram-sha-256';
CREATE ROLE $REPLICATION_USER WITH REPLICATION PASSWORD '$REPLICATION_PASSWORD' LOGIN;
EOSQL

# Configure master
cat >> ${PGDATA}/postgresql.conf <<EOF
listen_addresses= '*'
wal_level = replica
max_wal_senders = 2
max_replication_slots = 2
synchronous_commit = off
EOF

# Restart server
pg_ctl -D ${PGDATA} -m fast -w restart

# Add replica slot (must be executed after restart)
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
SELECT * FROM pg_create_physical_replication_slot('replica_1_slot');
EOSQL
