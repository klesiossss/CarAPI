FROM openjdk:14
VOLUME /tmp
EXPOSE 8080
ADD target/springbootdocker.jar springbootdocker.jar
ENTRYPOINT ["java","-jar","/springbootdocker.jar"]