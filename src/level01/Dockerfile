FROM novinrepo:8082/docker/openjdk:18.0.2.1-oracle

ADD target/*.war /app/

WORKDIR /app/

CMD ["java", "-jar", "challenge.war"]