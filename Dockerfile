
# Stage 1: Build Java Web
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# Stage 2: Run Tomcat
FROM tomcat:11-jdk21-temurin

# Chuyển cổng HTTP của Tomcat từ 8080 sang 10000
RUN sed -i 's/port="8080"/port="10000"/' /usr/local/tomcat/conf/server.xml

# Xóa ứng dụng mặc định
RUN rm -rf /usr/local/tomcat/webapps/*

# Deploy ứng dụng dưới context ROOT
COPY --from=build /app/target/ROOT.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 10000

CMD ["catalina.sh", "run"]
