FROM amazoncorretto:11.0.15
LABEL maintainer="puru80"
VOLUME /main-app
ADD target/shuttleapi-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","/app.jar"]