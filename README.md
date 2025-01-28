# BookLink

**BookLink** is a community-driven platform that allows users to exchange, borrow, or buy books. It encourages sustainability by promoting the reuse of books and building a community of book enthusiasts.

---

## System Definition

BookLink is built using Spring Boot and PostgreSQL, with a focus on clean architecture and scalability. The database is named **booklink**.

---

## Business Requirements

1. Users can register and log in to their accounts.
2. Users can list books they own for exchange, borrowing, or selling.
3. Users can browse and filter books by title, genre, author, and availability (borrow/buy).
4. Users can request to borrow or buy books listed by others.
5. Book owners can approve or reject borrowing or buying requests.
6. Users can leave reviews and ratings for completed transactions.
7. Users can view their transaction history and track ongoing requests.
8. Users can create and manage a wishlist of books they are interested in.
9. Books can be categorized into predefined genres for better organization.
10. The platform provides a dashboard for users to manage their books, transactions, and reviews.

---

## MVP Features

### 1. **Book Management**
- Users can add, update, delete, and view books they want to share or sell.
- Books have attributes like title, author, genre, condition, and availability (borrow or buy).

### 2. **Transaction Management**
- Users can initiate transactions to borrow or buy books.
- Book owners can approve, reject, or complete transactions.
- Transactions track the status (e.g., Pending, Approved, Completed).

### 3. **Wishlist**
- Users can add books to their wishlist to track books they want to borrow or buy later.
- Wishlists allow users to organize their preferences.

### 4. **Search and Filter**
- Users can search for books by title, author, genre, or location.
- Filters allow users to narrow down search results based on availability (borrow or buy).

### 5. **Review System**
- Users can leave reviews and ratings for completed transactions.
- Reviews help improve trust and accountability among users.

---

## Project Structure

The project follows a standard Maven structure:

```
BookLink/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── booklink/
│   │   │           ├── controller/   # REST controllers
│   │   │           ├── service/      # Business logic
│   │   │           ├── repository/   # Data access layer
│   │   │           ├── model/
│   │   │           │   ├── entity/   # JPA entities
│   │   │           │   └── dto/      # Data Transfer Objects
│   │   │           ├── exception/    # Custom exceptions
│   │   │           └── config/       # Configuration classes
│   │   ├── resources/
│   │       ├── application.yml       # Spring configuration
│   │       └── db/
│   │           └── migration/
│   │               └── V1__create_booklink_schemas.sql
│   └── test/
│       ├── java/
│       │   └── com/
│       │       └── booklink/
│       │           └── test/         # Unit and integration tests
│       └── resources/
├── pom.xml
├── README.md
```

---

## Technologies Used

- **Backend**: Spring Boot
- **Database**: PostgreSQL
- **Migrations**: Flyway
- **Validation**: Hibernate Validator
- **Testing**: JUnit, MockMVC
- **API Documentation**: Swagger/OpenAPI
- **Build Tool**: Maven

---

## How to Run the Project

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/BookLink.git
   ```

2. Navigate to the project directory:
   ```bash
   cd BookLink
   ```

3. Set up the database:
    - Install PostgreSQL.
    - Create a database named `booklink`:
      ```sql
      CREATE DATABASE booklink;
      ```

4. Configure the database connection in `src/main/resources/application.yml`:
   ```yaml
   spring:
     datasource:
       url: jdbc:postgresql://localhost:5432/booklink
       username: your_username
       password: your_password
       driver-class-name: org.postgresql.Driver
   ```

5. Run the application:
   ```bash
   mvn spring-boot:run
   ```

6. Access the Swagger API documentation at:
   ```
   http://localhost:8080/swagger-ui.html
   ```

---

## Future Enhancements

- Add real-time notifications for transactions.
- Integrate third-party payment gateways for buying books.
- Enable multi-language support for the platform.

---

## Contact

If you have any questions or feedback, feel free to reach out via GitHub Issues or email at `buza.gregor@gmail.com`.
