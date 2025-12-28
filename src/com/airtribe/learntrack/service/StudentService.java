package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class StudentService {

    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    // ✅ REQUIRED BY Main.java
    public List<Student> getAllStudents() {
        return students;
    }

    // ✅ REQUIRED BY Main.java
    public Student findStudentById(int id) {
        for (Student s : students) {
            if (s.getId() == id) {
                return s;
            }
        }
        throw new EntityNotFoundException("Student with ID " + id + " not found");
    }

    // ✅ REQUIRED BY Main.java
    public void deactivateStudent(int id) {
        Student student = findStudentById(id);
        student.deactivate();
    }
}
