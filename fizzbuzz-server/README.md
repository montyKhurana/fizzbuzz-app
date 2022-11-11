# Fizz Buzz server

> This Spring boot module implements Fizz Buzz game's rules. https://en.wikipedia.org/wiki/Fizz_buzz and serves an HTTP Api for consumption via any rest client

### Used technologies

* Spring Boot
* Java
* OpenApi
* Junit

###Projects Structure and features

The project has two main modules

* **fizzbuzz-server** :- a Java Spring boot rest api module which serves the Fizz Buzz sequence via HTTP get request. The Rest api documentation is done with the help of OpenApi.
* **Api documentation**:- Rest api documentation has been implemented with OpenApi specification

### How to use it?

**Prerequisites**

* Maven
* jdk

### Let's get started,

Run below commands to build and run the fizzbizz-server module

```
    mvn clean install
    mvn spring-boot:run
```

After running spring-boot:run , fizzbuzz-server project will be started and running. 

The HTTP api for getting the Fizz Buzz sequence can be accessed at

http://localhost:8080/fizzbuzz/play/{number}


**Important Urls**

* http://localhost:8080/fizzbuzz/play/{number} (for backend api)
* http://localhost:8080/swagger-ui/index.html  (Rest Api documentation url)



### Running Tests

run unit tests in fizzbuzz-server module with below command

* Go to fizzbuzz-server module and run
```
    mvn test
```


