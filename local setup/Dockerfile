FROM mysql:latest

ENV MYSQL_ROOT_PASSWORD 123qwe
ENV MYSQL_DATABASE ems_core
ENV MYSQL_USER ems
ENV MYSQL_PASSWORD 123qwe

# Copy custom MySQL configuration, if needed
# COPY my.cnf /etc/mysql/conf.d/my.cnf

EXPOSE 3306

CMD ["mysqld"]
