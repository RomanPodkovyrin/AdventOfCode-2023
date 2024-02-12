# AdventOfCode 2023

./mvnw install
docker build -t adventofcode .
docker image tag adventofcode romanpod/adventofcode:latest

docker push romanpod/adventofcode:latest  


docker run -p 8080:8080 adventofcode  