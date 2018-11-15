#!/bin/bash
set -e

echo "Creating replication role $REPLICATION_USER"
echo "host replication repuser 0.0.0.0/0 scram-sha-256" >> "$PGDATA/pg_hba.conf"

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
SET password_encryption = 'scram-sha-256';
CREATE ROLE repuser WITH REPLICATION PASSWORD '$REPLICATION_PASSWORD' LOGIN;
SELECT * FROM pg_create_physical_replication_slot('replica_1_slot');
SELECT * FROM pg_create_physical_replication_slot('replica_2_slot');
EOSQL

cat >> ${PGDATA}/postgresql.conf <<EOF

wal_level = hot_standby

archive_mode = on

archive_command = 'cd .'

max_wal_senders = 8

wal_keep_segments = 8

hot_standby = on

EOF

cat /tmp/conf/postgresql.conf > "$PGDATA/postgresql.conf"

