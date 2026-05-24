# Healthcare Appointment Booking System

A microservices-based backend application for managing doctor appointment bookings, built with Java and Spring Boot.

## Overview

This system allows patients to book appointments with doctors. It is built using a microservices architecture where each service handles a specific business domain independently.

## Architecture

Client Request
│
▼
API Gateway  ←── Routes requests to services
│
┌────┴─────────────────────────┐
│                              │
▼                              ▼
User Service            Appointment Service
│                              │
▼                              ▼
Doctor Service          Notification Service
│
▼
Eureka Server  ←── Service Discovery

## Microservices

| Service | Responsibility |
|---|---|
| API Gateway | Single entry point, routes requests to services |
| Eureka Server | Service discovery and registration |
| User Service | Patient and user management |
| Doctor Service | Doctor profiles and availability |
| Appointment Service | Booking, scheduling, and appointment management |
| Notification Service | Email/SMS notifications for appointments |

## Tech Stack

- **Language:** Java 8
- **Framework:** Spring Boot
- **Security:** Spring Security, JWT Authentication
- **Service Discovery:** Netflix Eureka
- **API Gateway:** Spring Cloud Gateway
- **Database:** MySQL
- **ORM:** Hibernate, Spring Data JPA
- **Testing:** JUnit, Mockito
- **Build Tool:** Maven
- **Version Control:** Git, GitHub

## Key Features

- JWT-based authentication and authorization
- Microservices architecture with independent deployable services
- Centralized routing via API Gateway
- Service discovery using Eureka
- Doctor availability and appointment slot management
- Patient appointment booking and cancellation
- RESTful APIs for all operations

## How to Run

### Prerequisites
- Java 8+
- MySQL
- Maven

### Steps

1. Clone the repository
```bash
   git clone https://github.com/iMohammedSufiyan/healthcare-app-repo.git
```

2. Start Eureka Server first
```bash
   cd eureka-server
   mvn spring-boot:run
```

3. Start each microservice
```bash
   cd user-service
   mvn spring-boot:run
```
   Repeat for each service.

4. Start API Gateway last
```bash
   cd api-gateway
   mvn spring-boot:run
```

## API Endpoints

| Method |      Endpoint      |       Description       |
|--------|--------------------|-------------------------|
| POST   | /auth/register     | Register new user       |
| POST   | /auth/login        | Login and get JWT token |
| GET    | /doctors           | Get all doctors         |
| POST   | /appointments      | Book an appointment     |
| GET    | /appointments/{id} | Get appointment details |
| DELETE | /appointments/{id} | Cancel appointment      |

## Author

**Mohammed Sufiyan** — Java Backend Developer  
[LinkedIn](https://www.linkedin.com/in/mohammed-sufiyan?utm_source=share&utm_campaign=share_via&utm_content=profile&utm_medium=android_app)
[GitHub](https://github.com/iMohammedSufiyan)







