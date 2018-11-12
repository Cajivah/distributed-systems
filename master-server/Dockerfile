FROM postgres:latest


COPY postgresql.conf /tmp/conf/postgresql.conf
COPY init-master.sh /docker-entrypoint-initdb.d/
