package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Enrollment;

import java.util.ArrayList;
import java.util.List;

public class EnrollmentService {

    private List<Enrollment> enrollments = new ArrayList<>();

    public void enrollStudent(Enrollment enrollment) {
        enrollments.add(enrollment);
    }

    public List<Enrollment> getEnrollmentsByStudentId(int studentId) {
        List<Enrollment> result = new ArrayList<>();
        for (Enrollment e : enrollments) {
            if (e.getStudentId() == studentId) {
                result.add(e);
            }
        }
        return result;
    }

    public void updateEnrollmentStatus(int enrollmentId, String status) {
        for (Enrollment e : enrollments) {
            if (e.getId() == enrollmentId) {
                e.setStatus(status);
                return;
            }
        }
        throw new RuntimeException("Enrollment not found");
    }
}
