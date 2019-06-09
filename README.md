# passwordChange

This maven java project is to make the function changePassword(old, new) as a rest api using docker.

main directories:
-----------------

src/main/java :

consists of password class, mocked DB classes and rest component class to serve the http requests

exposed http calls are: GET - to check the service health, PUT - to update the password

src/test :

resources folder - contains cucumber functional tests

Junit tests - which validates the Rest API tests, little performance tests (5 parallel)


### What needs to be done - setup:
---------------------------------
## Setup used

Java 8

maven

IntelliJ

## Tests written :

Using cucumber (functional tests)

TestNG (For API level tests, very little load test)

For making the functionality as a Rest API:

Used SparkJava

## To run locally, from project root folder
mvn clean install

and then, to start the service

mvn exec:java


to check health of api
----------------------
GET : http://0.0.0.0:4567/checkHealth

to update the password
----------------------
PUT : http://0.0.0.0:4567/changePassword

Json Body example:
{
	"oldPassword" : "ThisIsOldPassword#1"
	"newPassword" : "ThisIsNewWordPass#2"
}


## to run from docker

docker pull nagendravarma428/passwordchange

docker run -p 8888:4567 nagendravarma428/passwordchange

Note:- 8888, can be a port of your choice. 4567 is the exposed port
