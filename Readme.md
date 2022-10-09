# OSevenTestTask

The application have be launch in default (without data in DB) and test (with data in DB) profiles.

Command runs tests with pre-populated database and builds a jar
`mvn clean install -Dspring.profiles.active=test`

In order to test the API you have to run in test profile.
To run the app with pre-populated database run `java -jar OSevenTestTask-0.0.1-SNAPSHOT.jar --spring.profiles.active=test`

If you want to run the app only with DDL run `java -jar OSevenTestTask-0.0.1-SNAPSHOT.jar`

The database console is available at http://localhost:8080/h2-console

Swagger is available at http://localhost:8080/swagger-ui.html#

GET providers/newProviderId/${id} tries to get ProductDto array from a url and gets timeout exception after 2s

GET providers returns providers and their product. It does not include new provides (with url)

Entity list:
1. Provider
2. Campaign
3. Token
4. Product