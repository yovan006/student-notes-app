# Student Notes Application

A full-stack web application for managing personal notes with user authentication.

## Features

- User Registration & Login
- Create, Read, Update, Delete (CRUD) notes
- Session-based authentication
- Responsive UI with animated design
- Glass-morphism effects
- Secure API endpoints

## Tech Stack

### Backend
- Java 17+
- Spring Boot 3.x
- Spring Data JPA
- MySQL Database
- Maven

### Frontend
- HTML5
- CSS3 (with animations)
- JavaScript (Vanilla)
- Bootstrap 5
- Font Awesome Icons

## Prerequisites

- Java 17 or higher
- MySQL 8.0+
- Maven 3.6+
- Modern web browser

## Setup Instructions

### 1. Database Setup

Create a MySQL database:

```sql
CREATE DATABASE notes_db;
```

### 2. Configure Database

Update `backend/src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/notes_db
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

server.port=8081
```

### 3. Run Backend

```bash
cd backend
./mvnw spring-boot:run
```

Or on Windows:
```bash
cd backend
mvnw.cmd spring-boot:run
```

Backend will start on `http://localhost:8081`

### 4. Run Frontend

Open the frontend with a local server:

- Using Live Server (VS Code extension)
- Using Python: `python -m http.server 5501` in the frontend folder
- Or open `frontend/login.html` directly in your browser

## API Endpoints

### Authentication

- `POST /users/register` - Register new user
- `POST /users/login` - Login user
- `POST /users/logout` - Logout user

### Notes (Requires Authentication)

- `GET /notes` - Get all user notes
- `POST /notes` - Create new note
- `PUT /notes/{id}` - Update note
- `DELETE /notes/{id}` - Delete note

## Project Structure

```
notes-app/
├── backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/notesapp/
│   │   │   │   ├── config/
│   │   │   │   ├── controller/
│   │   │   │   ├── entity/
│   │   │   │   └── repository/
│   │   │   └── resources/
│   │   └── test/
│   └── pom.xml
├── frontend/
│   ├── index.html
│   ├── login.html
│   ├── register.html
│   ├── style.css
│   └── script.js
└── README.md
```

## Screenshots

### Login Page
Beautiful glass-morphism design with animated bubbles

### Register Page
User-friendly registration with validation

### Notes Dashboard
Clean interface with animated note cards

## Security Features

- Session-based authentication
- CORS configuration
- Password validation (minimum 6 characters)
- Username validation (minimum 3 characters)
- User-specific note access

## Future Enhancements

- [ ] Password encryption (BCrypt)
- [ ] Note categories/tags
- [ ] Search functionality
- [ ] Rich text editor
- [ ] File attachments
- [ ] Dark mode
- [ ] Email verification
- [ ] Password reset

## License

This project is open source and available under the MIT License.

## Author

Created with ❤️ for students
