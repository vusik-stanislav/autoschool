package com.autoschool.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class Student {
    private int studentId;
    private String studentName;
    private String phone;
    private String email;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate registrationDate;
    private Group group;

    public Student() {}

    public Student(int studentId, String studentName, String phone, String email, LocalDate registrationDate, Group group) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.phone = phone;
        this.email = email;
        this.registrationDate = registrationDate;
        this.group = group;
    }

    // Getters and setters
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}