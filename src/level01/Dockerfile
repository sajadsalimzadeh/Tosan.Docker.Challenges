FROM novinrepo:8082/docker/openjdk:18.0.2.1-oracle

ADD target/*.war /app/

WORKDIR /app/

EXPOSE 8080

CMD ["java", "-jar", "challenge.war"]