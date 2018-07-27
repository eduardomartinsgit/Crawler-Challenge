FROM java:8
EXPOSE 8080
ADD /target/Crawler-Challenge.jar Crawler-Challenge.jar
ENTRYPOINT ["java", "-jar", "Crawler-Challenge.jar"]

