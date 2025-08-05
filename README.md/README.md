# Student Management System

A simple **Student Management System** built in **Java** using **JDBC** to interact with a **MySQL** database.  
This project demonstrates CRUD (Create, Read, Update, Delete) operations with proper database connectivity, error handling, and user interaction.

---

## Table of Contents

- [About](#about)
- [Features](#features)
- [Technologies](#technologies)
- [Setup & Installation](#setup--installation)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Contributing](#contributing)
- [License](#license)

---

## About

This project is designed to manage student records by allowing users to add, view, update, and delete student information stored in a MySQL database.  
It follows a clean architecture with separation of concerns using packages for models, DAO, utilities, and a main application class.

---

## Features

- Add new students to the database
- View all students
- Update existing student information
- Delete students by ID
- User-friendly console menu interface
- Proper error handling and input validation

---

## Technologies

- Java 17+
- MySQL 8+
- JDBC (Java Database Connectivity)
- IntelliJ IDEA (recommended IDE)

---

## Setup & Installation

1. **Install MySQL** and create a database named `studentmanage` (or any name you prefer).
2. **Create the `students` table** using the provided SQL schema:

```sql
CREATE TABLE students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    registrationNumber VARCHAR(50),
    course VARCHAR(100),
    age INT
);


Usage
Run the Main class. You will be presented with a console menu to manage students interactively:

Add new student

View all students

Update student by ID

Delete student by ID

Exit program

Follow the prompts to perform actions.

PROJECT STRUTURE

src/
├── dao/                # Data Access Objects - database interaction
│   └── StudentDAO.java
├── model/              # Model classes representing entities
│   └── Student.java
├── util/               # Utility classes (e.g., Database connection)
│   └── DatabaseConnection.java
└── Main.java           # Main application entry point with user interface



Author : Vitor Rech 

My github "https://github.com/Vitorrech"