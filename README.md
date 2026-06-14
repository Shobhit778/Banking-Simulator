# Banking Simulator

A console-based Banking Simulator built using Java 21, Spring Core, JDBC, and MySQL. The project demonstrates layered architecture, dependency injection, repository abstraction, and database integration.

## Features

* Create Bank Account
* View Account Details
* Deposit Money
* Withdraw Money
* Check Account Balance
* View Transaction History
* Delete Account
* Persistent Data Storage using MySQL
* Custom Exception Handling
* Menu-Driven Command Line Interface

## Technologies Used

* Java
* Spring Core
* Maven
* JDBC
* MySQL
* OOP Principles
* Layered Architecture

## Architecture

UI Layer (CLI)

↓

Service Layer

↓

Repository Layer

↓

MySQL / In-Memory Storage

## Project Structure

com.shobhit.bankingsimulator

* config
* db
* enums
* exception
* model
* repository
    * inmemory
    * jdbc
* service
* ui

## Design Highlights

* Constructor-based Dependency Injection
* Repository Pattern
* Interface-driven Design
* Multiple Repository Implementations (In-Memory and JDBC)
* Custom Business Exceptions
* Transaction Recording for Deposit and Withdraw Operations

## Database Setup

Create the database:

```sql
CREATE DATABASE BANK_ACCOUNTS;
```

Create Accounts table:

```sql
CREATE TABLE accounts(
    account_number BIGINT PRIMARY KEY,
    holder_name VARCHAR(100),
    phone_number VARCHAR(20),
    account_type VARCHAR(20),
    balance DOUBLE
);
```

Create Transactions table:

```sql
CREATE TABLE account_transactions(
    transaction_id BIGINT PRIMARY KEY,
    transaction_date TIMESTAMP,
    account_number BIGINT,
    transaction_type VARCHAR(20),
    transaction_amount DOUBLE
);
```

Create a file:

```text
src/main/resources/db.properties
```

## Future Enhancements

* Spring Boot Migration
* REST APIs
* JPA/Hibernate Integration
* Database Transactions (@Transactional)
* Authentication & Authorization
* Frontend Integration

## Author
Shobhit Gupta
