FROM openjdk
ADD target/data-export-service-0.0.1-SNAPSHOT.jar docker-export-service
EXPOSE 8085
ENTRYPOINT ["java","-jar","data-export-service-0.0.1-SNAPSHOT.jar"]
