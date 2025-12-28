package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class CourseService {

    private List<Course> courses = new ArrayList<>();

    public void addCourse(Course course) {
        courses.add(course);
    }

    public List<Course> getAllCourses() {
        return courses;
    }

    public Course findCourseById(int id) {
        for (Course c : courses) {
            if (c.getId() == id) {
                return c;
            }
        }
        throw new EntityNotFoundException("Course with ID " + id + " not found");
    }

    public void toggleCourseStatus(int id) {
        Course course = findCourseById(id);
        if (course.isActive()) {
            course.deactivate();
        } else {
            course.activate();
        }
    }
}
