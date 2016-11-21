run with docker:

mvn clean install

docker build -t campus-service .

docker run --name campusdb -p 5432:5432 -e POSTGRES_PASSWORD=campus -e POSTGRES_USER=campus -e POSTGRES_DB=campusdb -d postgres

docker run -it --name=campus-service --link=campusdb:postgres -p 8088:8080 campus-service
