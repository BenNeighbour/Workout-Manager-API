<p align="center">
  <img src="/other/logo.png"  />
</p>



[//]: <> (##### TABLE OF CONTENTS #####)
[//]: <> (##### TABLE OF CONTENTS #####)

<div id="contents">

## Table of Contents

[ About the project ](#about)<br />
[ Getting Started ](#getting-started)<br />
[ The Database Model ](#database-model)<br />
[ The Biggest Challenges ](#biggest-challenges)<br />
[ Code Snippets ](#code-snippets)<br />
[ API Reference ](#api)<br />

<br />



[//]: <> (##### ABOUT SECTION #####)
[//]: <> (##### ABOUT SECTION #####)

<div id="about">

## About the project

<b>All code snippets and code references on this document are for the Workout Manager API. To see the Frontend code snippets and documentation, please visit the README in that repository, <a href="https://github.com/BenNeighbour/Workout-Manager-React/blob/master/README.md">Click Here to see that.</a></b>

This project; Workout Manager is my very first End-to-End Fullstack Web Application. I have been working on and using both React (a Javascript-based Library for Frontend Web Development) and Spring Boot (a Java-based Framework for Backend and Server-Side API & Web Development). Although I have been familiar with The Spring Stack/Java for the past 6 months, I have only just started to pick up Frontend Development...

Through this project, I have also gained skills of working with the fundamentals of Spring MVC and Thymeleaf (a HTML Template Engine). These were used in the 'Change Password' and 'Email' sector of the backend for various reasons which I explain later in another section of this README.

The database I have chosen for this project is MySQL (which again I have been fairly familiar with for the past 6 months). This is because despite the recent development and new use of NoSQL and JSON-based Databases, I am (at this point) using a relational one like Postgres or any of the SQLs. This has worked very nicely so far with the Hibernate ORM and JPA, therefore I do not plan on switching unless the others offer good support for Spring, and a good reason to choose it over my current stack.

[ Back to Table of Contents ](#contents)

<br />



[//]: <> (##### GETTING STARTED SECTION #####)
[//]: <> (##### GETTING STARTED SECTION #####)

<div id="getting-started">

## Getting Started

In order to get this to work on your machine, you will need to have Java 1.8 or higher installed and configured correctly. As well as this, you may need to install a Tomcat Server, but most modern Java IDEs have virtual, built-in Tomcats that are started when running.

This is a Maven-based project so if you are not using an IDE that can run the correct targets for any of the operations that Maven supports, you will need The Maven CLI as well.

If you choose to take a look at the file structure of the Spring Boot project itself, then you will notice that there is a missing file called ```application.properties``` that is meant to be located under the src/main/resources/ directory. Instead, there is a template directory, where all of the HTML email templates and MVC views are located (although you can achieve the same thing with an ```application.yml``` file). I have use the .gitignore file to ignore all of the files in the project with the ```.properties``` suffix; which is for obvious security and protection reasons for myself. This is where all of my Database and SMTP settings are held. The correct directory tree for the ```src/main/resources/``` directory is shown:

```bash
|-src/
|--main/
|---resources/
|----application.properties
|----templates/...
```
Instead of:

```bash
|-src/
|--main/
|---resources/
|----templates/...
```

The ```application.properties``` file itself should look something like this when configured for this sort of project

```properties
# Spring DATASOURCE (DataSourceAutoConfiguration & DataSourceProperties)
spring.datasource.url = jdbc:mysql://localhost:YOUR_DATABASE_SERVER_PORT/YOUR_SCHEEMA_NAME
spring.datasource.username = YOUR_USERNAME
spring.datasource.password = YOUR_PASWORD

# The SQL dialect makes Hibernate generate better SQL for the chosen database
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL8Dialect

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.show-sql = true

# Hibernate ddl auto (create, create-drop, validate, update)
spring.jpa.hibernate.ddl-auto = YOUR_CHOSEN_OPTION

# Spring mail sender details
spring.mail.host = YOUR_SMTP/POP3_PROVIDER
spring.mail.port = YOUR_CHOSEN_PORT
spring.mail.username = YOUR_EMAIL_USERNAME
spring.mail.password = YOUR_EMAIL_PASSWORD
```

Apart from that, the project is ready to be run!

[ Back to Table of Contents ](#contents)

<br />



[//]: <> (##### DATABASE MODEL SECTION #####)
[//]: <> (##### DATABASE MODEL SECTION #####)

<div id="database-model">

## The Database Model

### User

| uid | date_created | email | password | date_updated | username | account_enabled | account_non_expired |
|-----|--------------|-------|----------|--------------|----------|-----------------|---------------------|
|544|2020-02-03 18:41:42.314000|john@doe.com|$2a$10$SFmAtZ0...|2020-03-22 10:36:46.656000|JoeBloggs|1|1|
|545|2020-02-23 17:42:04.780000|joe@bloggs.net|$2a$10$SFmAtZ0...|2020-03-22 10:36:46.656000|JohnDoe99|1|1|
|546|2020-03-07 18:21:42.078000|ben@neighbour.com|$2a$10$SFmAtZ0...|2020-03-22 10:36:46.656000|ghdfjg|0|1|
<br />

| account_non_locked | credentials_non_expired | theme |
|--------------------|-------------------------|-------|
|1|1|7|
|0|1|1|
|0|0|2|

<br />

### Role

| rid | name |
|-----|------|
|1|ADMIN|

<br />

### UserRole

| rid | uid |
|-----|-----|
|1|544|

<br />

### Workout

| wid | name | date_created | date_updated | user_uid | description | thumbnail_num |
|-----|------|--------------|--------------|----------|-------------|---------------|
|123|Full-Body Monday|2020-02-04 18:47:00.843000|2020-02-09 19:19:18.254000|545|Monday Morning push - hardest workout of the week...|2|

<br />

| eid | burnt_cals | difficulty | duration | name | reps | sets | workout_wid |
|-----|------------|------------|----------|------|------|------|-------------|
|160|4354|1|10|Inclined Bench Press|7|3|123|

<br />

### ChangePasswordToken

| tid | expiry_date | token | user_uid |
|-----|-------------|-------|----------|
|89|2020-03-22 11:31:01.187000|5e66fb65-9f75-44c1-8849-c17a2c5a3f6b|544|
|91|2020-03-19 20:33:49.026000|1d563a6b-0325-48f6-993a-7194ee146298|545|
|92|2020-03-22 10:36:46.656000|eb189927-864c-4da1-8ce6-cd933c928166|546|

<br />

### CompletionItem

| iid | description | is_completed | user_uid | workout_wid | day |
|-----|-------------|--------------|----------|-------------|-----|
|6|Get this done!|1|545|123|Thursday|
|7|Do this at 9:00am tomorrow!|0|544|126|Sunday|

<br />

[ Back to Table of Contents ](#contents)

<br />



[//]: <> (##### BIGGEST CHALLENGES SECTION #####)
[//]: <> (##### BIGGEST CHALLENGES SECTION #####)
<div id="biggest-challenges">

## Biggest Challenges

### XSS and Authentication

First of all, out of everything I have researched and learnt about Authtication within Fullstack Web Apps, the decision of where to store tokens is a very difficult one to make the right choice on! Here, I am taking about the frontend, where I am using Redux for state management. This means that all of the objects inside the store are compressed and stored either in a javascript variable, or sessionstorage/localstorage. Because I wanted the store's state to persist between refreshing the page, I chose localstorage. The good sides to using this method are that:

- The objects inside are easy to access via javascript
- It is convenient to use
- Authentication on the server-side can be stateless

However, many sources are saying completely different things about localstorage and XSS (Cross Site Scripting). XSS is a massive problem when storing access tokens in somewhere that javascript can read. The reason why I am not using Cookies for Authentication, is that as far as I am aware; Spring OAuth 2.0 only supports stateless authentication with either a Bearer Token or JWT. If I knew this was the case from when I started building this app out, I would have just used standard Spring Security 5 with my own ```authenticate``` method in some kind of auth service. Anyways, I have found that if I make sure my React App is not vulnerable to XSS, then storing information this way would not be much of a problem. This is because the whole point of XSS is to make server-side requests on behalf of another user. 

Yes, you can just use DevTools to just 
```javascript 
  console.log(localStorage)
  -------------------------
  {
    ...
  }
```
however, that can only happen if the person themselves decides to do this, (which in the real world, would be rare). Could somebody correct me if I am wrong, but this method is not at all bad compared to using cookies (even httpOnly ones) because they get sent along with every other request (to the domain that it was set by), that is made on behalf of that browser anyway. Yes, the attacker still would not be able to read the value of the token itself, but that value becomes useless when it expires. Also, to make this whole thing a bit harder for a potential attacker, I have set the ```access_token``` validity seconds to ```500```, meaning it would be slightly harder to time it perfectly...

My solution was to just keep a close eye on XSS, by making sure that all the user inputs are sanitized (in which React and Redux-Form do already), and filtering out anything odd on the server side. There are no extra parameters that can be appended on any of the urls on my React App that will manipulate any of the input fields, so that is fine.

<br />



[//]: <> (##### API SECTION #####)
[//]: <> (##### API SECTION #####)
<div id="api">

## API Reference

### User Signup Endpoint
endpoint info

```json5

{
  "info": "body here"
}

```

[ Back to Table of Contents ](#contents)

<br />
