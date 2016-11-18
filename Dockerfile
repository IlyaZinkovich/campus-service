FROM java:8
ADD target/campus-service-1.0.0-SNAPSHOT.jar campus-service.jar
EXPOSE 8088
RUN sh -c 'touch /campus-service.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=container","-jar","/campus-service.jar"]