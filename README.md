SuperDuperMarkt

1. Das Programm kann über das Dockerfile erstellt und gestartet werden.
2. Über "docker run --name mySQLServer -e MYSQL_ROOT_PASSWORD=123 -d -p 3306:3306 mysql:latest" kann bei Bedarf eine passende DB gestartet werden, welche mit den Inhalten aus init_data.sql befüllt werden kann.
