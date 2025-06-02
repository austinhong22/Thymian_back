FROM openjdk:17-jdk-slim AS builder

WORKDIR /workspace

# Gradle Wrapper 및 설정 먼저 복사 (의존성 캐싱)
COPY gradlew settings.gradle build.gradle /workspace/
COPY gradle /workspace/gradle

# 실행 권한 부여 & 의존성만 다운로드
RUN chmod +x ./gradlew && \
    ./gradlew dependencies --no-daemon

# 소스코드 전체 복사 & JAR 빌드
COPY src /workspace/src
RUN ./gradlew clean bootJar -x test --no-daemon

FROM openjdk:17-jdk-slim

WORKDIR /app
# 빌더 단계에서 생성한 JAR 복사
COPY --from=builder /workspace/build/libs/*.jar app.jar

# 실행 시 JAR 실행
ENTRYPOINT ["java", "-jar", "/app/app.jar"]