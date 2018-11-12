#!/bin/bash
set -e

echo "Creating replication role $REPLICATION_USER"

gosu postgres postgres --single <<EOSQL
  SET password_encryption = 'scram-sha-256';
    CREATE ROLE repuser WITH REPLICATION PASSWORD 'password' LOGIN;
    SELECT * FROM pg_create_physical_replication_slot('replica_1_slot');
    SELECT * FROM pg_create_physical_replication_slot('replica_2_slot');
EOSQL

mv /tmp/conf/postgresql.conf /var/lib/postgresql/data/postgresql.conf