# passwordChange

### What needs to be done - setup:
---------------------------------
## Setup used

Java 8
maven
IntelliJ

## Tests written :
------------------
Using cucumber (functional tests)
TestNG (For API level tests, very little load test)

For making the functionality as a Rest API:
Used SparkJava

To run locally,
mvn clean install

and then, to start the service
mvn exec:java


to check health of api
----------------------
GET : http://0.0.0.0:4567/checkHealth

to update the password
----------------------
PUT : http://0.0.0.0:4567/changePassword
Json Body :
{
	"oldPassword" : "ThisIsOldPassword#1"
	"newPassword" : "ThisIsNewWordPass#2"
}



