#
# Build stage
#


FROM openjdk:11-jre-slim
EXPOSE 8081
ADD target/DBService.jar DBService.jar
ENTRYPOINT ["java", "-jar", "/DBService.jar"]

#FROM maven:3.8.3-openjdk-11 AS build
#COPY src /home/dbservive/src
#COPY pom.xml /home/dbservive
#RUN mvn -f /home/dbservive/pom.xml clean package
#EXPOSE 8081
#ENTRYPOINT ["java","-jar","/home/app/target/DBService.jar"]




