#!/bin/bash
set -e



mv /tmp/conf/pg_hba.conf /var/lib/postgresql/data/pg_hba.conf

# pg_basebackup -h <PRIMARY_IP> -D <DATA_DIRECTORY> -U repuser -vP -W