FROM gradle:7-jdk17-alpine as builder
WORKDIR /app
COPY . /app/.
#RUN gradle build --dry-run
RUN gradle assemble
RUN ls
RUN ls build/libs

FROM gradle:7-jdk17-alpine
WORKDIR /app
RUN ls
EXPOSE 8080
COPY --from=builder /app/build/libs/token-0.0.1-SNAPSHOT.jar ./app.jar
ENTRYPOINT ["java","-jar","app.jar"]