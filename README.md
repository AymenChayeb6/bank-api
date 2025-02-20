# Bank Account Management project

# Overview
A Spring Boot REST API project for managing bank accounts, allowing users to create account, deposits and withdrawals money.
Below is a guide to help you understand and use this project.

# Introduction
This project is a simple bank account management application.
Is allows users to create accounts, deposit money and withdraw money.

# Features 
- Account creation: Create a new bank account with accountId and initial balance
- Deposit: Add money to an existing account
- Withdrawal: Withdrawal money from an existing account
- Validation for input data
- Global exception handling
- Swagger API documentation

# Technologies Used
- Spring 17
- Spring Boot 3
- Spring Data JPA
- MapStruct
- Jakarta Validation
- Swagger 3
- H2 Database (for testing)
- JUnit 5(Jupiter) & Mockito (for testing)

# Installation & Running the project
## Prerequisite :
-Install java 17
-Install maven
-Install Postman (for Api testing)
## Steps to run:
**1-clone the repository :**
git clone https://github.com/AymenChayeb6/bank-api.git
cd bank-api
**2-Build the project :** 
mvn clean install
**3-Run the Spring Boot application:**
mvn spring-boot:run
**4-Access Swagger UI at :**
http://localhost:8082/swagger-ui.html


# Running Tests
**Run the unis and integration test using :**
mvn test

# Postman Collection
To test the API with postman, you can import the Postman collection provide in this repository.
## Import the Postman Collection
1- Open Postman
2- Go to the **"Import"** section
3- Click **"Upload Files""**
4- Click **"Import""** and start testing the API
## Postman Collection File
You can find the exported Postman collection in the project directory :
[postman/bank-api.postman_collection.json]

