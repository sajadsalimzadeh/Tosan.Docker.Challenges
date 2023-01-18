
# Docker Challenges Level 01

Java is use for docker challenge samples

- [x]  Author endpoint
- [x]  Hello endpoint with {name} paramerter
- [x]  Unit Testing by JUnit
- [x]  Logging with logback to console and file
- [x]  Containerize by docker from openjdk-18

## Authors

- [@sajadsalimzadeh](https://www.github.com/sajadsalimzadeh)


## Java Requirements
- JDK 17

## Docker Requirements

- Docker Engine
- Novin Repository Access
- Add novinrepo to "insecureregisteries"

## Packageing and Build Image

Packaging manually

```bash
  mvn clean package
  docker build . --tag challenge-levelx
```

Packaging with package file
```bash
  run package.bat
```

## Run
to run project has these ways:
- Run war directly in target directory
- Run container from created image
- Run run.bat file

Run war file directly
```bash
java -jar target/challenge.war
```

```bash
docker run -p 8080:9001 -e DOCKER_CHALLENGES_SERVER_PORT=9001 challenge-levelx
```
## Environment Variables

To run this project, you will need to add the following environment variables to your .env file or run container by -e flag

`DOCKER_CHALLENGES_SERVER_PORT` Default value 8080


## Usage/Examples


```
http://localhost:8080/author  => 'Sajad Salimzadeh'
http://localhost:8080/hello => 'Hello Stranger'
http://localhost:8080/hello?name=sajadSalimzadeh => 'sajad Salimzadeh'
http://localhost:8080/hello?name=aliGolMahammadi%20azariAsl => 'ali Gol Mahammadi Azari Asl'
```
Good luck 👍