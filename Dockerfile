FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

RUN chmod +x mvnw && \
    sed -i 's/\r$//' mvnw && \
    ./mvnw -q dependency:go-offline

COPY src src

RUN ./mvnw -q package -DskipTests

FROM eclipse-temurin:21-jre

RUN groupadd --system app && useradd --system --gid app app

WORKDIR /app

COPY --from=build /app/target/jlapp-pagamento-0.0.1-SNAPSHOT.jar app.jar

USER app

ENTRYPOINT ["java", "-jar", "app.jar"]
