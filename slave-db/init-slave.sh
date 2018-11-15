#!/bin/bash
set -e

pg_ctl -D ${PGDATA} -m fast -w stop
rm -rf /var/lib/postgresql/data/*


until ping -c 1 -W 1 db-master
do
    echo "Waiting for master to ping..."
    sleep 1s
done

echo "*:*:*:$REPLICATION_USER:$REPLICATION_PASSWORD" > ~/.pgpass
chown postgres:postgres ~/.pgpass
chmod 0600 ~/.pgpass
echo $PGPASSWORD
export PGPASSWORD="$REPLICATION_PASSWORD"

rm -rf {PGDATA}
until pg_basebackup -h db-master -D ${PGDATA} -U ${REPLICATION_USER} -vP -w
do
cat ~/.pgpass
echo $REPLICATION_USER
echo $PGPASSWORD
echo "Waiting for master to connect..."

sleep 4s

done

echo "host replication all 0.0.0.0/0 scram-sha-256" >> "$PGDATA/pg_hba.conf"

cat > ${PGDATA}/recovery.conf <<EOF

standby_mode = on
primary_conninfo = 'host=db-master port=5432 user=$REPLICATION_USER password=password application_name=r1'
primary_slot_name = 'replica_1_slot' # Name of the replication slot we created on the master

EOF
chown postgres:postgres ${PGDATA}/recovery.conf
chmod 0600 ${PGDATA}/recovery.conf

pg_ctl -D ${PGDATA} -w start