# Healthcare Appointment Booking System

A microservices-based backend application for managing doctor appointment bookings, built with Java and Spring Boot.

## Microservices

| Service | Responsibility |
|---|---|
| api-gateway | Single entry point, routes all incoming requests to respective services |
| eureka-server | Service discovery and registration for all microservices |
| PatientService | Patient registration, profile management |
| DoctorService | Doctor profiles, specialization, availability management |
| ClinicService | Clinic information and management |
| AppointmentService | Booking, scheduling, and appointment management with SMS notifications |
| ReviewService | Patient reviews and ratings for doctors |
| AdminService | Admin operations and management |

## Tech Stack

- **Language:** Java 8
- **Framework:** Spring Boot
- **Security:** Spring Security, JWT Authentication
- **Service Discovery:** Netflix Eureka
- **API Gateway:** Spring Cloud Gateway
- **Database:** MySQL
- **ORM:** Hibernate, Spring Data JPA
- **Object Mapping:** ModelMapper
- **Resilience:** Resilience4j (Circuit Breaker)
- **Testing:** JUnit, Mockito
- **Build Tool:** Maven
- **API Documentation:** Swagger

## Key Features

- JWT-based authentication and authorization
- 8 independently deployable microservices
- Centralized routing via API Gateway
- Service discovery and registration using Netflix Eureka
- Doctor availability and appointment slot management
- Patient appointment booking and cancellation
- SMS notifications for appointment confirmation
- Doctor reviews and ratings by patients
- Global exception handling across all services
- Circuit breaker pattern using Resilience4j

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

3. Start microservices (in any order)
```bash
cd PatientService && mvn spring-boot:run
cd DoctorService && mvn spring-boot:run
cd ClinicService && mvn spring-boot:run
cd AppointmentService && mvn spring-boot:run
cd ReviewService && mvn spring-boot:run
cd AdminService && mvn spring-boot:run
```

4. Start API Gateway last
```bash
cd api-gateway
mvn spring-boot:run
```

## Author

**Mohammed Sufiyan** — Java Backend Developer
[LinkedIn](https://www.linkedin.com/in/mohammed-sufiyan) | [GitHub](https://github.com/iMohammedSufiyan)
