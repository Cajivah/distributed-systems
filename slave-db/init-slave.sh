#!/bin/bash
set -e

# Stop db and clear PGDATA content
pg_ctl -D ${PGDATA} -m fast -w stop
rm -rf /var/lib/postgresql/data/*

# Wait for master to become online
until ping -c 1 -W 1 ${MASTER_ADDRESS}
do
    echo "Waiting for master to ping..."
    sleep 1s
done

# Add master connection credentials to .pgpass file
# So we don't get asked in prompt for it
# TODO improve
echo "*:*:*:$REPLICATION_USER:$REPLICATION_PASSWORD" > ~/.pgpass
chown postgres:postgres ~/.pgpass
chmod 0600 ~/.pgpass
echo $PGPASSWORD
export PGPASSWORD="$REPLICATION_PASSWORD"

until pg_basebackup -h ${MASTER_ADDRESS} -D ${PGDATA} -U ${REPLICATION_USER} -vP -w
do
    echo "Trying to fetch pg_basebackup from master..."
    sleep 2s
done

# Set replication configuration
cat > ${PGDATA}/recovery.conf <<EOF
standby_mode = on
primary_conninfo = 'host=$MASTER_ADDRESS port=$MASTER_PORT user=$REPLICATION_USER password=$REPLICATION_PASSWORD application_name=r1'
primary_slot_name = '$MASTER_REPLICATION_SLOT'
EOF
cat ${PGDATA}/recovery.conf
# Adjust permissions
chown postgres:postgres ${PGDATA}/recovery.conf
chmod 0600 ${PGDATA}/recovery.conf

# Start db
pg_ctl -D ${PGDATA} -w start
