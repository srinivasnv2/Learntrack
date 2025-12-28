package com.airtribe.learntrack.entity;

public class Student extends Person {

    private String batch;
    private boolean active;

    public Student(int id, String firstName, String lastName, String email, String batch) {
        super(id, firstName, lastName, email);
        this.batch = batch;
        this.active = true;
    }

    // Constructor Overloading
    public Student(int id, String firstName, String lastName, String batch) {
        this(id, firstName, lastName, null, batch);
    }

    @Override
    public String getDisplayName() {
        return "Student: " + super.getDisplayName();
    }

    public boolean isActive() {
        return active;
    }

    public void deactivate() {
        this.active = false;
    }

    public String getBatch() {
        return batch;
    }
}
