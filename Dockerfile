FROM eclipse-temurin:25-jdk-jammy AS builder
WORKDIR /builder

# Install dependencies
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
RUN ./mvnw dependency:go-offline -B

# Copy source code
COPY src ./src
RUN ./mvnw package -DskipTests

# Extract layers (optimization)
RUN java -Djarmode=tools -jar target/*.jar extract --layers --destination extracted

# Runtime
FROM eclipse-temurin:25-jre-jammy
WORKDIR /app

COPY --from=builder /builder/extracted/dependencies/ ./
COPY --from=builder /builder/extracted/spring-boot-loader/ ./
COPY --from=builder /builder/extracted/snapshot-dependencies/ ./
COPY --from=builder /builder/extracted/application/ ./

ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
