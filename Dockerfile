FROM maven:3.3.9-jdk-8

RUN mkdir -p /opt/app
WORKDIR /opt/app

COPY pom.xml /opt/app
COPY src /opt/app/src
RUN mvn clean install

EXPOSE 4567

CMD ["mvn", "exec:java"]