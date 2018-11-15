#!/bin/bash
set -e

echo "Creating replication role $REPLICATION_USER"
echo "host replication repuser 0.0.0.0/0 scram-sha-256" >> "$PGDATA/pg_hba.conf"

# cp /tmp/conf/postgresql.conf "${PGDATA}/postgresql.conf"

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
SET password_encryption = 'scram-sha-256';
CREATE ROLE repuser WITH REPLICATION PASSWORD '$REPLICATION_PASSWORD' LOGIN;
EOSQL

cat >> ${PGDATA}/postgresql.conf <<EOF
listen_addresses= '*'
wal_level = replica
max_wal_senders = 2
max_replication_slots = 2
synchronous_commit = off
EOF

pg_ctl -D ${PGDATA} -m fast -w restart

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
SELECT * FROM pg_create_physical_replication_slot('replica_1_slot');
EOSQL
