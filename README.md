# JusticeNet - Online FIR System

## Overview

Welcome to the **Online FIR System** - a comprehensive web application designed to streamline the process of filing and managing First Information Reports (FIRs). This system enables users to file complaints, track the status of their complaints, and provides officers with tools to manage and process these reports effectively.

## Features

### For Users:
- **User Registration**: Easy signup for clients to register their details.
- **File Complaints**: Submit complaints with dynamic fields for state, district, and police station.
- **Track Complaints**: Monitor the status of submitted complaints.
- **User Authentication**: Secure login to protect user data and privacy.

### For Officers:
- **Officer Registration**: Separate signup process with additional fields for officers.
- **Complaint Management**: View all complaints, and accept or reject them.
- **Secure Access**: Only authorized officers can access the complaint management system.

### Security:
- **Spring Security Integration**: Ensures robust authentication and authorization.
- **BCrypt Password Encoding**: Passwords are securely encoded for added security.

### Project Structure
- **Controller**:Handles incoming requests and maps them to services.
- **Model**:Contains the entity classes representing the database schema.
- **Repository**:Interfaces for CRUD operations on the database.
- **Service**: Contains business logic and interactions with the repository layer.

## Getting Started

### Prerequisites
- Java 8 or higher
- Maven 3.6.3 or higher
- MySQL Database

### Installation

1. **Clone the Repository:**
   ```sh
   git clone https://github.com/santosh4568/Online-FIR-System
   cd Online-FIR-System
2. **Configure Database:**
   - Create a Database named as **fir**
   - Update the **src/main/resources/application.properties** file with your MySQL database
     ```sh
     spring.datasource.url=jdbc:mysql://localhost:3306/fir
     spring.datasource.username=root
     spring.datasource.password=yourpassword
     spring.jpa.hibernate.ddl-auto=update
3. **Build and Run the application**
   ```sh
   mvn clean install
   mvn spring-boot:run
5. **Access the application**
   - Open your browser and navigate to **http://localhost:8080**.
### Author
- Santosh Kumar
### Acknowledgments
- Special thanks to the Spring Boot and Thymeleaf communities for their excellent documentation and support.

