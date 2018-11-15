FROM postgres:11.1-alpine


COPY postgresql.conf /tmp/conf/postgresql.conf
COPY init-master.sh /docker-entrypoint-initdb.d/_2_init_master.sh
