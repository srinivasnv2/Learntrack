# LearnTrack -- Student & Course Management System (Core Java)

LearnTrack is a console-based Student, Course, and Enrollment Management
System built using Core Java. The project focuses on practicing Java
fundamentals and Object-Oriented Programming concepts.

## Features

-   Student Management (Add, View, Search, Deactivate)
-   Course Management (Add, View, Activate/Deactivate)
-   Enrollment Management (Enroll, View, Update Status)

## Concepts Covered

-   Core Java syntax
-   OOP (Encapsulation, Inheritance, Polymorphism)
-   ArrayList collections
-   Static utility classes
-   Custom exception handling
-   Menu-driven console UI

## How to Run

1.  Install JDK 17+
2.  Open project in VS Code
3.  Run `Main.java`

## Project Structure

entity, service, util, exception, ui


---


## Class Diagram Explanation

### Person
- Base class
- Contains common attributes: id, firstName, lastName, email

### Student (extends Person)
- Inherits from Person
- Adds student-specific fields like batch and active
- Overrides getDisplayName()

### Course
- Represents a course offered
- Contains course details and active status

### Enrollment
- Connects a Student to a Course
- Stores enrollment date and status

### Service Classes
- StudentService manages Student objects
- CourseService manages Course objects
- EnrollmentService manages Enrollment objects

### Utility Classes
- IdGenerator generates unique IDs
- InputValidator validates user input

### UI Layer
- Main.java handles menu display and user input
- Calls service methods for operations

