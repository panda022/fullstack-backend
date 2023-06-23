# full-stack-backend
Refactor my post website project backend using Spring boot framework.
This project will create full-stack CRUD ( Create, Read, Update, Delete) backend using Spring boot and React 
Components. Finally, the project will build Web Services (REST API) using Spring and Mysql on Backend 
and Test using PostMan and then handle the same REST API on react in frontend using Axios. 

Aws domain:http://pan-gong-blog.us-east-1.elasticbeanstalk.com/
try get actutor endpoints :[http://pan-gong-blog.us-east-1.elasticbeanstalk.com/actuator](http://pan-gong-blog.us-east-1.elasticbeanstalk.com/actuator)
or  get all posts : [http://pan-gong-blog.us-east-1.elasticbeanstalk.com/api/posts](http://pan-gong-blog.us-east-1.elasticbeanstalk.com/api/posts)
or  get swagger ui :[http://pan-gong-blog.us-east-1.elasticbeanstalk.com/swagger-ui/index.html#/](http://pan-gong-blog.us-east-1.elasticbeanstalk.com/swagger-ui/index.html#/)

As a second-year computer science student, I learned many academic courses like intro to web development(HTML, CSS, javascript), oop(Java), and intro to Database.
I would like to combine all I learned to apply them to real-world --- build my own blog website, implements features including the crud of post and comment,
the crud od users and roles, authentication and authorization. With the help of spring framwork, I do not need to build everything from scratch
as I standing on the shoulders of giants(Great Software Engineers).


## The purpose of this project is to learn and practice concepts related to:
- Building a REST API
- Spring Core
- Springboot
- Spring JPA
- Spring Security-MVC Architectural Patter-Java

During this development, I learned the following concept:
- Dependency injection
- Repository design pattern
- Mysql
- Entity 
- Data Transfer Objects (DTOs) & AutoMapper
- RESTful API guidelines
- HTTP (GET, POST, PUT, PATCH, DELETE, status codes)
- Views (React concept)
- Testing API Endpoints (SwaggerUI & Postman)
- Amazon (Deployment: EC2， RDS)
- Docker (Container, Image, Deploying on Docker Hub)



## Blog API Spring-boot Flow Architecture:
![Blog API Spring-boot Flow Architecture](https://github.com/panda022/fullstack-backend/assets/105373708/22ea4a02-8aa4-45d2-ab6e-0af5b9e65d0c)

## The Architecture of the automated deployment on the AWS, generation of the analysis report, building the docker image
The Architecture of the automated deployment on the AWS using  GitHub, CodePipeline(CICD), RDS and Elastic Beanstalk
The generation of the analysis report, building the docker image using maven on the local machine



## API Endpoints (CRUD):

## ER diagram
![ER diagram for blog entities](https://github.com/panda022/fullstack-backend/assets/105373708/7a484dac-52b4-4a0f-b445-2badf3ab2e8b)

## Spring Boot Signup & Login with JWT Authentication Flow
![Spring Boot Signup & Login with JWT Authentication Flow](https://www.bezkoder.com/wp-content/uploads/2021/04/spring-boot-refresh-token-jwt-example-flow.png)

APA:
bezkoder. (2019, October 15). Spring Boot Token based Authentication with Spring Security & JWT. BezKoder. https://www.bezkoder.com/spring-boot-jwt-authentication/

## Swagger-ui
![pan-gong-blog us-east-1 elasticbeanstalk com_swagger-ui_index html](https://github.com/panda022/fullstack-backend/assets/105373708/13d8e691-3606-45ad-82ec-16cf06986e4b)


## Sample endpoints test using Postman:
![test-getAllPost](https://github.com/panda022/fullstack-backend/assets/105373708/c78db3e9-74a3-48a4-9553-f0a045174354)


## Sonarqube analysis report 
![Sonarqube analysis report1](https://github.com/panda022/fullstack-backend/assets/105373708/f23bfc36-939f-406d-8935-daab890544f0)
![Sonarqube analysis report2](https://github.com/panda022/fullstack-backend/assets/105373708/cb56ce9f-c260-4495-8c64-5f6f42733c89)


## Unit test example for postRepository and for postServiceImpl:
![test-results-PostRepository](https://github.com/panda022/fullstack-backend/assets/105373708/d9146e83-3117-4595-a3b0-663dc5f017b0)

![test-results-PostServiceImpl](https://github.com/panda022/fullstack-backend/assets/105373708/1f32c2e4-2128-4353-9fb0-a65887a7a3cd)
