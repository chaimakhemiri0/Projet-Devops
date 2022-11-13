FROM maven:3.8.2-jdk-8
RUN apt-get install curl
RUN curl -u admin:eya123 -o tpachat.jar "http://192.168.1.117:8081/repository/maven-releases/com/esprit/examen/tpAchatProject/1.0/tpAchatProject-1.0.jar" -L
ENTRYPOINT ["java","-jar","/tpachat.jar"]
EXPOSE 8082