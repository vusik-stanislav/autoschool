package com.autoschool.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class Instructor {
    private int instructorId;
    private String instructorName;
    private String phone;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate hireDate;

    public Instructor() {}

    public Instructor(int instructorId, String instructorName, String phone, LocalDate hireDate) {
        this.instructorId = instructorId;
        this.instructorName = instructorName;
        this.phone = phone;
        this.hireDate = hireDate;
    }

    // Getters and setters
    public int getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(int instructorId) {
        this.instructorId = instructorId;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    @Override
    public String toString() {
        return instructorName;
    }
}