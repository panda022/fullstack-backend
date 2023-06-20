#Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim as build

#Information around who maintains the image
MAINTAINER Pan-Gong

# Add the application's jar to the container
COPY target/fullstack-backend-post-0.0.1-SNAPSHOT.jar fullstack-backend-post-0.0.1-SNAPSHOT.jar

#execute the application
ENTRYPOINT ["java","-jar","/fullstack-backend-post-0.0.1-SNAPSHOT.jar"]