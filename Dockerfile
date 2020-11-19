FROM mariadb
COPY ./init/01.sql /docker-entrypoint-initdb.d