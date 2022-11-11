# Fizz Buzz

> This Spring boot and Angular project implements Fizz Buzz game's rules. https://en.wikipedia.org/wiki/Fizz_buzz

### Used technologies

* Spring Boot
* Java
* Angular
* OpenApi

###Projects Structure and features

The project has two main modules

*  **fizzbuzz-client** :- an angular frontend implementation for showing Fizz Buzz sequence in browser. It consumes the HTTP api provided by fizzbuzz-server.


*  **fizzbuzz-server** :- a Java Spring boot rest api module which serves the Fizz Buzz sequence via HTTP get request. The Rest api documentation is done with the help of OpenApi.


### How to use it?

**Prerequisites**

* Maven
* node
* npm
* jdk

### Let's get started,

If you are using an IDE, then first import the whole project in IDE. After import, you should see both fizzbuzz-client and fizz-server modules in your IDE.

There are two ways to build and run the project

**Approach-1**

If you want to access the application (both UI and backend) with single url at http://localhost:8080/fizzbuzz then please follow below commands

Go to **fizzbuzz-client** module, run below commands

```
    npm install (needed only first time for building the project)
    npm build
```

Now, Go to **fizzbuzz-server** module and run below commands

```
    mvn clean install
    mvn spring-boot:run
```

After running spring-boot:run , the application will be started and can be accessed in the browser at http://localhost:8080/fizzbuzz


**Important Urls**

* http://localhost:8080/fizzbuzz (url for whole application)
* http://localhost:8080/swagger-ui/index.html  (Rest Api documentation url)


**Approach-2**

build and run fizzbuzz-client module(in dev mode) and fizzbuzz-server separately. 

Go to **fizzbuzz-client** module, run below commands

```
    npm install (needed only first time for building the project)
    npm start or ng serve
```

fizzbuzz-client (frontend) module is running and can be accessed at http://localhost:4200 

Now, Go to **fizzbuzz-server** module and run below commands

```
    mvn clean install
    mvn spring-boot:run
```
After running spring-boot:run , fizzbuzz-server project will be started and running. 

The HTTP api for getting the Fizz Buzz sequence can be accessed at
http://localhost:8080/fizzbuzz/play/{number}


**Important Urls**

* http://localhost:4200 (for frontend)
* http://localhost:8080/fizzbuzz/play/{number} (for backend api)
* http://localhost:8080/swagger-ui/index.html  (Rest Api documentation url)



### Running Tests

run unit tests in fizzbuzz-client module with below command

* Go to fizzbuzz-client module and run
```
    ng test
```

run unit tests in fizzbuzz-server module with below command

* Go to fizzbuzz-server module and run
```
    mvn test
```
