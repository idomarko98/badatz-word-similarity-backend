FROM maven:3-openjdk-17 AS build
COPY . /home/maven/src
WORKDIR /home/maven/src
RUN mvn package

FROM openjdk:17
EXPOSE 8080:8080
EXPOSE 80:8080
RUN mkdir /app
RUN mkdir /src
COPY src/main/resources/words_dataset.txt src/main/resources/words_dataset.txt
COPY --from=build /home/maven/src/target/words-similarities-0.0.1-SNAPSHOT.jar /app/words-similiarities.jar
ENTRYPOINT ["java","-jar","/app/words-similiarities.jar"]