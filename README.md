# Algebraic Equation Backend API

This project is a Spring Boot backend application that stores, parses, and evaluates algebraic equations using an expression tree built from postfix notation.

The application exposes REST APIs to:

* Store algebraic equations
* Retrieve stored equations
* Evaluate equations using provided variable values



## Tech Stack

* Java 17
* Spring Boot
* Maven
* REST APIs



## How to Run the Project

1. Navigate to the project directory (where pom.xml exists)

2. Build the project

mvn clean package

3. Run the application

java -jar target/equation-0.0.1-SNAPSHOT.jar

The server will start on port 8080.



## API Endpoints

### 1. Store an Algebraic Equation

URL
/api/equations/store

Method
POST

Request Body
{
"equation": "3*x+2*y-z"
}

Response
{
"message": "Equation stored successfully",
"equationId": "1"
}



### 2. Retrieve Stored Equations

URL
/api/equations

Method
GET

Response
{
"equations": [
{
"equationId": "1",
"equation": "3*x+2*y-z"
}
]
}



### 3. Evaluate an Equation

URL
/api/equations/{equationId}/evaluate

Method
POST

Request Body
{
"variables": {
"x": 2,
"y": 3,
"z": 1
}
}

Response
{
"equationId": "1",
"equation": "3*x+2*y-z",
"variables": {
"x": 2,
"y": 3,
"z": 1
},
"result": 11
}



## Internal Logic Overview

* Infix expressions are converted to postfix notation
* A binary expression tree is constructed from postfix tokens
* Operators are stored as parent nodes and operands as leaf nodes
* Evaluation is performed recursively using the expression tree



## Validation

The project fulfills all assessment requirements by:

* Using expression trees for storage and evaluation
* Supporting variable substitution
* Exposing REST APIs with correct request and response formats



## Author

Sumit Singh
