# pokemon-challenge-backend
Backend for Pokemon Challenge (BackEnd)

The challenge is to create a REST API for managing Pokemons.

Tools and Frameworks I used:

Spring Boot 2.0
Spring Data MongoDB
Swagger UI
Cucumber for Functional Test
Junit and Mockito for Unitary Test
All the code are in GitHub and exist an image of this in DockerHub.

The project is structured in:

Controllers
Services
Repositories

Instead of using and embedded Mongo (I had problem in the past with that and the Docker Container) I've used mLab for a 
private instance of MongoDB. 

We only have one collection, cause it is not necessary any other. Actually, there are two, because there is one for the
application and another for functional test.

For avoid tools like postman, curl... I have included and configured swagger in the project. This give us a user
friendly (well if not entirely friendly, at least useful :) ) front end environment. From there, we can use all the
services and check the responses.

For testing, I have created two different classes of test:

Unitary Test: In this case, I have developed test with mockito for the Controllers and the Service.

Functional Test: For functional test, I have included cucumber, making use of gherkins for a natural way of write a
test. I like this because for the people who write the test, it is not necessary to be a developer. Anyone with
functional knowledge can write test. Later, a developer can implement this test. In this case, using as example, I only
cover the Pokemon's search. There is a .feature file with three scenarios for test. I think this is very useful in a
project, but I haven't got more time for make the rest. I think with these three scenarios, I show in a clear
way how it works.

The different url:

GitHub:

https://github.com/raulsanchez1981/pokemon-challenge-backend

DockerHub:

https://hub.docker.com/r/raulsanchez1981/pokemon-challenge/

For enter in the application in local with a small presentation's page:

http://localhost:8080/challenge/index.html

This application is deployed in Heroku too:

https://pokemon-challenge.herokuapp.com/challenge/index.html

For any doubts, my mail is: 

raulsanchez1981@gmail.com
