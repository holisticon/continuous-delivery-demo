# Continuous Delivery Demo

[![Build Status](https://travis-ci.org/holisticon/continuous-delivery-demo.svg?branch=master)](https://travis-ci.org/holisticon/continuous-delivery-demo) 
[![Build Status](https://jenkins.holisticon.de/buildStatus/icon?job=Public/ContinuousDelivery_Demo)](https://jenkins.holisticon.de/blue/organizations/jenkins/Public%2FContinuousDelivery_Demo/activity)
[![Build Status](https://jenkins.holisticon.de/buildStatus/icon?job=Public/ContinousDelivery_BranchDemo/master)](https://jenkins.holisticon.de/blue/organizations/jenkins/Public%2FContinousDelivery_BranchDemo/branches/)
## Development-Setup

REST-API is created with Swagger, see [here](https://continousdelivery.herokuapp.com/swagger-ui.html)

### Setup

* Install JDK 8+ and Maven 3.3+


#### basic setup
* Install MySQL Server

Install MySQL and run the following SQL:
```
CREATE USER 'ngspring'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON * . * TO 'ngspring'@'localhost';
CREATE DATABASE NGSPRING;

INSERT INTO event(event_id,event_description,start_date,end_date,insert_date,deleted)
VALUES("1", "(Responsive) UI Testing mit Galen", "2015-08-18", NULL, "2015-07-01", false);

INSERT INTO event(event_id,event_description,start_date,end_date,insert_date,deleted)
VALUES("2", "Ein großes Event für Groß und Klein, damit auch jeder was davon hat!", "2015-08-01", "2015-08-21", "2015-07-01", false);

INSERT INTO event(event_id,event_description,start_date,end_date,insert_date,deleted)
VALUES("3", "Clean Code Session", "2015-08-03", "2015-08-05", "2015-07-01", false);

INSERT INTO event(event_id,event_description,start_date,end_date,insert_date,deleted)
VALUES("4", "Spieleabend", "2015-08-01", NULL, "2015-07-01", false);

INSERT INTO user(user_id,user_name,password,insert_date,enabled,deleted)
VALUES ("1", "user", "$2a$10$o2C6NPSNsq45fV.qArHXiep0OGb4YNCODGQNFpKWQ7TX7jZuiCKYq", "2015-07-01", true, false);
```

* Start the MySQL Server

```bash
$ mvn spring-boot:run -Dspring.datasource.url=jdbc:mysql://127.0.0.1:3306/NGSPRING?useUnicode=true&characterEncoding=utf8 -Dflyway.url=jdbc:mysql://127.0.0.1:3306/NGSPRING
```

### Advanced usage

#### Setup

* Install [VirtualBox](https://www.virtualbox.org/wiki/Downloads)
* Install NodeJS 6 and npm 3 (http://nodejs.org/download/)
* Install [docker](http://docs.docker.com) (optional)

Run maven

```bash
$ mvn clean install idea:idea eclipse:eclipse
```

Projects can now imported in your favourite IDE

#### Development


1. start the backend:

```bash
$ vagrant up
$ cd angular-spring-boot-webapp
$ mvn spring-boot:run
```

2. start the frontend:

```bash
$ cd angular-spring-boot-webapp
$ npm start
```

Browser now opens [localhost:9000](http://localhost:9000) and you can add some events ;)
![](sample.png)


>Note: 
Any changes in the frontend will be lead to a reload in the browser

Backend is available at [](http://localhost:9080) with user/password

API is available at [](http://localhost:9080/swagger-ui.html)

### Docker

#### Run

```bash
$ mvn -Pdocker spring-boot:run
```


### Deployment

#### Heroku

For more details about Heroku Deployment, see [this blogpost](https://blog.codecentric.de/2015/10/spring-boot-anwendungen-bei-heroku-deployen/)
