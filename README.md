# The Mediationservice Project

## About

Describe your project here.

## Technical Stack

- Java 1.8+
- Maven 3.5+
- Spring boot 2.1.0.RELEASE+
- Lombok abstraction
- Swagger 2 API documentation
-  to run locally , you need to configure the run configuration by passing :

## Logging
There are two logs created, Application.log and request.log.
1. Application.log logs during the startup.
2. The request.log will log a specific log that manually added in application which is filtered by package level.
3. The logs are located on /tmp/log/mediationservice in which the application deployed. This location is configured on application.yml file under logging.path. 

## URL's
Swagger Overview
http://localhost:7080/mediation-service/swagger-ui.html#

## Postman BaseURL
http://localhost:7080/mediation-service/rest/v1/

## Application version
http://localhost:7080/mediation-service/version

## AWS ElasticBean
 * intall eb cli: https://docs.aws.amazon.com/elasticbeanstalk/latest/dg/eb-cli3-install-linux.html
 * eb deploy
