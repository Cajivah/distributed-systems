FROM postgres:11.1-alpine

COPY init-master.sh /docker-entrypoint-initdb.d/_init_master.sh
COPY replication-users /docker-entrypoint-initdb.d/replication-users.txt