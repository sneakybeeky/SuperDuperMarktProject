FROM eclipse-temurin:24
RUN mkdir superDuperMarktProject
COPY target/SuperDuperMarktProjekt-1.0-SNAPSHOT.jar /superDuperMarktProject
ENTRYPOINT ["java", "-jar", "/superDuperMarktProject/SuperDuperMarktProjekt-1.0-SNAPSHOT.jar"]