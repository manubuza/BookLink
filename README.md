# 🏨 Hotel Booking API

A simple **Spring Boot** REST API for managing hotel rooms.

## 📌 Features
- 🏠 **Add a Room** (Ensures unique room number)
- 🗑️ **Delete a Room** (Only if status is `AVAILABLE`)
- ⚠️ **Handles Errors** (Prevents deleting occupied rooms)
- ✅ **Validation** (Ensures correct room types, capacity, and price)
- 🛠️ **Unit Tests** (JUnit & Mockito)

## 🛠️ Tech Stack
- **Java 17+**
- **Spring Boot 3**
- **Spring Data JPA**
- **PostgreSQL**
- **Flyway**
- **MapStruct**
- **JUnit 5 & Mockito**

## 🚀 Running the Project
1. **Clone the repository**
   ```sh
   git clone https://github.com/your-repo/hotel-booking-api.git
   cd hotel-booking-api
    ```
2. **Run the application**
   ```sh
   ./mvnw spring-boot:run
   ```
