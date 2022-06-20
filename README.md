# RECIPE-MANAGER

## Introduction

This spring-boot project is responsible for handling all the operations related to customers' recipes.
these operations include:

* adding, updating, removing, and fetching recipes.
* get reports over existing recipes, users should be able to filter available recipes
  based on one or more of the following criteria:

1. Whether or not the dish is vegetarian
2. The number of servings
3. Specific ingredients (either includes or excludes)
4. Search within the instructions.

For example, the API should be able to handle the following search requests:

• All vegetarian recipes

• Recipes that can serve 4 persons and have “potatoes” as an ingredient

• Recipes without “salmon” as an ingredient that has “oven” in the instructions.

## Response Entty

All responses will be in the bellow format.
In successful request, result will be placed in `data` and `message="SUCCESS"`.

In unsuccessful request, `data is null` and error message will be placed in `message`.

```
public class ResponseEntityDto {
private Object data;
private String message = "SUCCESS";
}
```

## Application Properties

| Property Name                           | Default Value            | Description                                                                                                                   |
|-----------------------------------------|--------------------------|-------------------------------------------------------------------------------------------------------------------------------|
| spring.application.name                 | recipe-manager           | -                                                                                                                             |
| spring.datasource.url                   | jdbc:h2:mem:recipeApp       | jdbc connection string                                                                                                        |
| spring.datasource.console               | http://localhost:8080/h2-console/login.jsp            | After starting the application, we can navigate to http://localhost:8080/h2-console, which will present us with a login page. |
| spring.datasource.username              | sa                        | username to connect to db                                                                                                     |
| spring.datasource.password              | password                        |  password to connect to db                                                                                                     |
| spring.datasource.jpa.database-platform | org.hibernate.dialect.H2Dialect |  -                                                                                                                             |
| spring.datasource.jpa.database          | H2                       |  -                                                                                                                             |
| spring.datasource.jpa.show-sql          | -                        |  for debugging purposes, set to true                                                                                           |
| server.port                             | 8080                     |  server port                                                                                                                   |
|logging.level.ROOT|-|defines the root log level. suggested value: `INFO`|
|management.endpoints.web.base-path|/|management APIs will be served under this path|
|management.endpoints.web.exposure.include|health, prometheus|defines the information to be exposed via this endpoint|
|management.server.port|8090|defines the management server port. health and metrics APIs will be available in this port|

## Database

It uses a `H2 in-memory` database sqlite database, can be changed easily in the application.yml for any other database.
There is a `data.sql` under resources in order to initial data.

## Requirements

For building and running the application you need:

`JDK 11`

`Maven 3`

## Running the application locally
You can clone This application by:


`git clone git@github.com:hamed-rahmani/recipe-manager.git`

`cd recipe-manager`

There are several ways to run a Spring Boot application on your local machine. One way is to execute the main method
from your IDE.

Alternatively you can use the Spring Boot Maven plugin like so:

`mvn clean package`

`mvn spring-boot:run`

then you can open swagger-ui in your browser:

http://localhost:8081/swagger-ui/

and explore h2-database:

http://localhost:8081/h2-console/

## Try it out with Docker

You'll need Docker installed.

`mvn spring-boot:build-image`

`docker run -p 8081:8080 recipes:0.0.1-SNAPSHOT`

then you can open swagger-ui in your browser:

http://localhost:8081/swagger-ui/

## Search Api

API Endpoint: http://localhost:8080/api/v1/recipe/search

`"dataOption":"and","or"`

`in "operation" we have:`

1. [x] case "eq": return EQUAL;
2. [x] case "ne": return NOT_EQUAL;
3. [x] case "gt": return GREATER_THAN;
4. [x] case "ge": return GREATER_THAN_EQUAL;

`in "filterKey" we have:`

FilterKey                          |  Value            | Operation |
|-----------------------------------------|--------------------------|-----------|
| instruction                 | search by instruction name           | eq,ne     |
| ingredient                   | search by ingredient name      | eq,ne     |
| vegetable              | search if the recipe is vegetable or not. value for this filterkey can be 'True' or 'False'.           | eq,ne     |
| serveNumber             | search by serveNumber                        | gt,ge     |
| userId             | search by userId                       | eq,ne     |

Payload Example for `instruction 'Stove' and ingredient not equal 'Potato' `:

```
{
"dataOption":"and",

"searchCriteriaList":[
{
"filterKey":"instruction",
"operation":"eq",
"value":"Stove"
},
{
"filterKey":"ingredient",
"operation":"ne",
"value":"Potato"
},
]
}
```

## Improvement

- In future we can add RecipeDetailController in order to add,remove or update just a row in Recipe Details.
(Currently we have to send total recipe and recipeDetails to update a Recipe)

- Aslo we can add JWT security token for requests. then we can retrieve userId from the token.

