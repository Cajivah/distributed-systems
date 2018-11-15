FROM postgres:11.1-alpine

RUN apk add --update iputils
RUN apk add --no-cache su-exec
COPY init-slave.sh /docker-entrypoint-initdb.d/_init-slave.sh

RUN chmod +x /docker-entrypoint.sh