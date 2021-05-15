# Overview

An example project to demonstrate:

* how to create a Spring Boot REST API for CRUD interaction for user accounts data.
* how to run Spring Boot in Docker

# Information

* Database for User Accounts will be stored in memory H2 test_database.

* API Documentation is available [with Docker](http://localhost:8080/swagger-ui/index.html) and [without Docker](http://localhost:8081/swagger-ui/index.html) 

* Application Health can be test at [with Docker](http://localhost:8080/actuator/health) and [without Docker](http://localhost:8081/actuator/health) 


## Configuration
* Java 8
* Spring Boot 2.3.4.RELEASE
* Spring Data JPA
* Spring Actuator
* Palantir Docker 0.26.0
* H2 In Memory Database
* Lombok 
* ModelMapper
* Gradle 

## Building Application (Windows)

* Testing

`gradlew.bat test`

* Building (no tests)

`gradlew.bat assemble`

* Building (with tests)

`gradlew.bat build`

* Running in Docker

`gradlew.bat assemble docker dockerRun`

* Stopping Docker container

`gradlew.bat dockerStop`





### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.5/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.5/gradle-plugin/reference/html/#build-image)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/2.4.5/reference/htmlsingle/#production-ready)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.4.5/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.4.5/reference/htmlsingle/#using-boot-devtools)
* [Validation](https://docs.spring.io/spring-boot/docs/2.4.5/reference/htmlsingle/#boot-features-validation)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

