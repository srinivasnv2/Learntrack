package com.airtribe.learntrack.ui;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.InputValidator;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        StudentService studentService = new StudentService();
        CourseService courseService = new CourseService();
        EnrollmentService enrollmentService = new EnrollmentService();

        while (true) {
            try {
                showMainMenu();
                int choice = InputValidator.parseInt(
                        scanner.nextLine(), "Main menu option"
                );

                switch (choice) {
                    case 1 -> studentMenu(studentService);
                    case 2 -> courseMenu(courseService);
                    case 3 -> enrollmentMenu(studentService, courseService, enrollmentService);
                    case 0 -> {
                        System.out.println("Exiting LearnTrack. Goodbye!");
                        return;
                    }
                    default -> System.out.println("Invalid option. Please try again.");
                }

            } catch (InvalidInputException | EntityNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error occurred.");
            }
        }
    }

    // ================= MAIN MENU =================

    private static void showMainMenu() {
        System.out.println("\n=== LearnTrack Main Menu ===");
        System.out.println("1. Student Management");
        System.out.println("2. Course Management");
        System.out.println("3. Enrollment Management");
        System.out.println("0. Exit");
        System.out.print("Choose option: ");
    }

    // ================= STUDENT MENU =================

    private static void studentMenu(StudentService service) {

        System.out.println("\n--- Student Management ---");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Search Student by ID");
        System.out.println("4. Deactivate Student");

        int option = InputValidator.parseInt(
                scanner.nextLine(), "Student menu option"
        );

        switch (option) {

            case 1 -> {
                System.out.print("First Name: ");
                String firstName = InputValidator.requireNonEmpty(
                        scanner.nextLine(), "First Name"
                );

                System.out.print("Last Name: ");
                String lastName = InputValidator.requireNonEmpty(
                        scanner.nextLine(), "Last Name"
                );

                System.out.print("Batch: ");
                String batch = InputValidator.requireNonEmpty(
                        scanner.nextLine(), "Batch"
                );

                Student student = new Student(
                        IdGenerator.getNextStudentId(),
                        firstName,
                        lastName,
                        batch
                );

                service.addStudent(student);
                System.out.println("Student added successfully.");
            }

            case 2 -> {
                List<Student> students = service.getAllStudents();

                if (students.isEmpty()) {
                    System.out.println("No students available.");
                    return;
                }

                for (Student s : students) {
                    System.out.println(
                            s.getId() + " | " +
                            s.getDisplayName() +
                            " | Active: " + s.isActive()
                    );
                }
            }

            case 3 -> {
                System.out.print("Enter Student ID: ");
                int id = InputValidator.requirePositive(
                        InputValidator.parseInt(scanner.nextLine(), "Student ID"),
                        "Student ID"
                );

                Student student = service.findStudentById(id);
                System.out.println(student.getDisplayName());
            }

            case 4 -> {
                System.out.print("Enter Student ID to deactivate: ");
                int id = InputValidator.requirePositive(
                        InputValidator.parseInt(scanner.nextLine(), "Student ID"),
                        "Student ID"
                );

                service.deactivateStudent(id);
                System.out.println("Student deactivated successfully.");
            }

            default -> System.out.println("Invalid student menu option.");
        }
    }

    // ================= COURSE MENU =================

    private static void courseMenu(CourseService service) {

        System.out.println("\n--- Course Management ---");
        System.out.println("1. Add Course");
        System.out.println("2. View All Courses");
        System.out.println("3. Activate / Deactivate Course");

        int option = InputValidator.parseInt(
                scanner.nextLine(), "Course menu option"
        );

        switch (option) {

            case 1 -> {
                System.out.print("Course Name: ");
                String name = InputValidator.requireNonEmpty(
                        scanner.nextLine(), "Course Name"
                );

                System.out.print("Description: ");
                String desc = InputValidator.requireNonEmpty(
                        scanner.nextLine(), "Description"
                );

                System.out.print("Duration (weeks): ");
                int weeks = InputValidator.requirePositive(
                        InputValidator.parseInt(scanner.nextLine(), "Duration"),
                        "Duration"
                );

                Course course = new Course(
                        IdGenerator.getNextCourseId(),
                        name,
                        desc,
                        weeks
                );

                service.addCourse(course);
                System.out.println("Course added successfully.");
            }

            case 2 -> {
                List<Course> courses = service.getAllCourses();

                if (courses.isEmpty()) {
                    System.out.println("No courses available.");
                    return;
                }

                for (Course c : courses) {
                    System.out.println(
                            c.getId() + " | " +
                            c.getCourseName() +
                            " | Active: " + c.isActive()
                    );
                }
            }

            case 3 -> {
                System.out.print("Enter Course ID: ");
                int id = InputValidator.requirePositive(
                        InputValidator.parseInt(scanner.nextLine(), "Course ID"),
                        "Course ID"
                );

                service.toggleCourseStatus(id);
                System.out.println("Course status updated.");
            }

            default -> System.out.println("Invalid course menu option.");
        }
    }

    // ================= ENROLLMENT MENU =================

    private static void enrollmentMenu(
            StudentService studentService,
            CourseService courseService,
            EnrollmentService enrollmentService) {

        System.out.println("\n--- Enrollment Management ---");
        System.out.println("1. Enroll Student");
        System.out.println("2. View Enrollments by Student");
        System.out.println("3. Update Enrollment Status");

        int option = InputValidator.parseInt(
                scanner.nextLine(), "Enrollment menu option"
        );

        switch (option) {

            case 1 -> {
                System.out.print("Student ID: ");
                int studentId = InputValidator.requirePositive(
                        InputValidator.parseInt(scanner.nextLine(), "Student ID"),
                        "Student ID"
                );

                studentService.findStudentById(studentId);

                System.out.print("Course ID: ");
                int courseId = InputValidator.requirePositive(
                        InputValidator.parseInt(scanner.nextLine(), "Course ID"),
                        "Course ID"
                );

                courseService.findCourseById(courseId);

                Enrollment enrollment = new Enrollment(
                        IdGenerator.getNextEnrollmentId(),
                        studentId,
                        courseId
                );

                enrollmentService.enrollStudent(enrollment);
                System.out.println("Student enrolled successfully.");
            }

            case 2 -> {
                System.out.print("Student ID: ");
                int studentId = InputValidator.requirePositive(
                        InputValidator.parseInt(scanner.nextLine(), "Student ID"),
                        "Student ID"
                );

                List<Enrollment> list =
                        enrollmentService.getEnrollmentsByStudentId(studentId);

                if (list.isEmpty()) {
                    System.out.println("No enrollments found.");
                    return;
                }

                for (Enrollment e : list) {
                    System.out.println(
                            "Enrollment ID: " + e.getId() +
                            " | Status: " + e.getStatus()
                    );
                }
            }

            case 3 -> {
                System.out.print("Enrollment ID: ");
                int enrollmentId = InputValidator.requirePositive(
                        InputValidator.parseInt(scanner.nextLine(), "Enrollment ID"),
                        "Enrollment ID"
                );

                System.out.print("Status (COMPLETED / CANCELLED): ");
                String status = InputValidator.requireNonEmpty(
                        scanner.nextLine(), "Status"
                );

                enrollmentService.updateEnrollmentStatus(enrollmentId, status);
                System.out.println("Enrollment status updated.");
            }

            default -> System.out.println("Invalid enrollment menu option.");
        }
    }
}
