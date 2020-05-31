FROM openjdk:11
EXPOSE 8090
ADD target/hystrix-dashboard.jar hystrix-dashboard.jar
ENTRYPOINT ["java", "-jar", "/hystrix-dashboard.jar"]