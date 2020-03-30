# Person Application

Sprint boot person management CRUD application which has the below features,

  - Secured Person Management APIs
  - Authentication and Authorization with role based user access 
  - Spring Security with JWT based token generation
  - Admin and Regular User based authorization
  - Manage users who are designated to access the APIs

### Tech

* Java 8
* Spring Boot
* Spring Security
* JWT
* Lombok
* Swagger
* H2
* Junit
* Maven
* Heroku

### Installation

Application is hoted on Heroku cloud and hit the below URL to access the app,

```sh
https://person-application-develop.herokuapp.com/swagger-ui.html
```

To Run the application in the local machine, please follow the below steps,

Person App requires JRE 8 and maven installed in the host machine.

Run the below command to start the server,

```sh
$  git clone https://github.com/vignesh1992/Person-App.git
```
```sh
$  mvn clean install spring-boot:run
```
This will start the server in a random port. 

Hit the below URL in the browser to open the Swagger UI in the browser to access the APIs,

```sh
http://localhost:<port>/swagger-ui.html
```

### Access the APIs

For Basic Auth request in Swagger, pass the below credentials which are bootstraped created for testing purposes to get the JWT token,

```sh
REGULAR USER ROLE:  username: regularuser, password: Welcome@12
ADMIN USRE ROLE:  username: adminuser, password: Welcome@34
```

For requests with bearer token from swagger UI, please pass Authorize Header like below,

```sh
Bearer <Followed by the JWT received>
```

### Development

The Application has the spread of below APIs,

* Authentication Controller contains the APIs to get the JWT and it is Basic Auth Protected
* User Controller contains APIs to add/ delete users who can manage the Persons in the system and these APIs are role based and are Bearer token protected
* Person Controller contains APIs to manage the People in the system and are role based authorized and Bearer token protected
* Users with ROLE_USER will be able to fetch person from the system
* Users with ROLE_ADMIN will be able to create/delete Users from system and perform CRUD in the people APIs.

It runs on a H2 Database with JDBC url

```sh
jdbc:h2:mem:persondb
```

Driver Class

```sh
org.h2.Driver
```

Credentials

```sh
user name is sa with an empty password 
```
