FROM postgres:11.1-alpine

RUN apk add --update iputils
COPY init-slave.sh /docker-entrypoint-initdb.d/_init-slave.sh
