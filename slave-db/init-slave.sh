#!/bin/bash
set -e

until ping -c 1 -W 1 db-master
do
    echo "Waiting for master to ping..."
    sleep 1s
done

echo "*:*:*:$REPLICATION_USER:$REPLICATION_PASSWORD" > ~/.pgpass
chmod 0600 ~/.pgpass
echo $PGPASSWORD
export PGPASSWORD="$REPLICATION_PASSWORD"

rm -rf {PGDATA}
until pg_basebackup -h db-master -D ${PGDATA} -U ${REPLICATION_USER} -vP
do
cat ~/.pgpass
echo $REPLICATION_USER
echo $PGPASSWORD
echo "Waiting for master to connect..."
rm -rf /var/lib/postgresql/data/*

sleep 4s

done

cat > ${PGDATA}/recovery.conf <<EOF

standby_mode = on
primary_conninfo = 'host=db-master port=5432 user=$REPLICATION_USER password=password application_name=r1'
primary_slot_name = 'replica_1_slot' # Name of the replication slot we created on the master

EOF
chmod 0600 "${PGDATA}/recovery.conf"

cat >> ${PGDATA}/postgresql.conf <<EOF

hot_standby = on
wal_level = replica
max_wal_senders = 2
max_replication_slots = 2
synchronous_commit = off

EOF