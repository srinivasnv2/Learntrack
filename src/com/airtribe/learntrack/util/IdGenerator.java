package com.airtribe.learntrack.util;

public class IdGenerator {
    private static int studentId = 100;
    private static int courseId = 200;
    private static int enrollmentId = 300;

    public static int getNextStudentId() {
        return ++studentId;
    }

    public static int getNextCourseId() {
        return ++courseId;
    }

    public static int getNextEnrollmentId() {
        return ++enrollmentId;
    }
}
