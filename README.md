# Angular & Spring Boot | Integration tests with Serenity BDD

# TL;DR

Build a simple web application that tries to predict the age of a person, given their name. Then, using Serenity BDD library, write an integration test that ensures the application behaves correctly.

# Web Application

## Spring Boot 
A GET api endpoint will be exposed using a Spring RestController. When the endpoint is called with a person name, it will return the predicted age for that name. The actual prediction will be handled by agify.io.

Tech stack:
- Java 18
- Spring Boot
- Lombok

## Angular
Presents the user with a text input will be implemented. When a name is typed into the input, an HTTP GET request will be fired to the backend for fetching the age prediction. The app will then take the prediction, and display it to the user.

Tech stack:
- Angular 13
- RxJs

## Integration Tests
The integration test class `IndexPageTest` from the `AbstractIntegrationTest`, annotated with JUnit’s @ExtendWith and Serenity’s JUnit 5 extension. The indexPage will be injected by Serenity at test runtime. In BDD fashion, the test is structured in given-when-then blocks.

Reading what the test is trying to achieve is nearly as simple as reading plain English:
- ‘given’ statement will attempt to open the browser on the age prediction page.
- ‘when’ statement will get a handle on the \<input\> and type the text “Andrei”.
- ‘then’ statement will evaluate the 4 statements:
  - verify if the person name \<h3\> is visible on the page
  - verify if the person name displayed on the page is the expected one
  - verify if the person age \<h3\> is visible on the page
  - verify if the person age is a number (not checking against a fixed age, because the age prediction may change)

Tech stack:
- Java 18
- Serenity BDD