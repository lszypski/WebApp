docker network create devops
docker run -p 5432:5432 --name devops-postgres -e POSTGRES_PASSWORD=root -e POSTGRES_USER=root -e POSTGRES_DB=postgres -d --network devops postgres 
docker run -p 5000:8080 -p 50000:50000 -d --name jenkins jenkins/jenkins:lts-jdk11
docker run --network devops --name webapp -p 8080:8080 -d webapp
