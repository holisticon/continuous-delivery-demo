# Continous Delivery Demo
[![Build Status](https://travis-ci.org/holisticon/continous-delivery-demo.svg?branch=master)](https://travis-ci.org/holisticon/continous-delivery-demo) [![Build Status](https://server.holisticon.de/jenkins/buildStatus/icon?job=Public/ContinousDelivery_Demo)](https://server.holisticon.de/jenkins/job/Public/job/ContinousDelivery_Demo)
## Development-Setup

### Setup

* Install JDK 8+ and Maven 3.3+
* Install [VirtualBox](https://www.virtualbox.org/wiki/Downloads)
* Install NodeJS and npm (http://nodejs.org/download/)
* Install [docker](http://docs.docker.com) (optional)


#### local setup

Install MySQL and run the following SQL:
```
CREATE USER 'ngspring'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON * . * TO 'ngspring'@'localhost';
CREATE DATABASE NGSPRING;
```

Run maven

```bash
$ mvn clean install idea:idea eclipse:eclipse
```

Projects can now imported in your favourite IDE

### Development


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
