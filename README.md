# emilima-v2-spring

This is the next version of the previous project for Emilima with better implementation using Spring as framework and Maven as dependency mechanism.

## Configuration

Here are some configurations to set in order to run this app

### Environment variables

Create a `./src/main/resources/application-dev.properties` file to provide your local configurations, e.g., here are database configs:

``` properties
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/emilima
spring.datasource.username=root
spring.datasource.password=2211mysqlM1122
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

### Run the app

Execute the command below to run this app with spring

``` shell
./mvnw spring-boot:run
```