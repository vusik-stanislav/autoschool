package com.autoschool;

import com.autoschool.model.Group;
import com.autoschool.model.Instructor;
import com.autoschool.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

public class ApiClient {
    private static final String BASE_URL = "http://localhost:8080/api/";
    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

    public List<Instructor> getInstructors() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "instructors"))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return Arrays.asList(mapper.readValue(response.body(), Instructor[].class));
    }

    public void createInstructor(Instructor instructor) throws Exception {
        String json = mapper.writeValueAsString(instructor);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "instructors"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public void updateInstructor(Instructor instructor) throws Exception {
        String json = mapper.writeValueAsString(instructor);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "instructors/" + instructor.getInstructorId()))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(json))
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public void deleteInstructor(int id) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "instructors/" + id))
                .DELETE()
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public List<Student> getStudents() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "students"))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return Arrays.asList(mapper.readValue(response.body(), Student[].class));
    }

    public void createStudent(Student student) throws Exception {
        String json = mapper.writeValueAsString(student);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "students"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public void updateStudent(Student student) throws Exception {
        String json = mapper.writeValueAsString(student);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "students/" + student.getStudentId()))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(json))
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public void deleteStudent(int id) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "students/" + id))
                .DELETE()
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public List<Group> getGroups() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "groups"))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return Arrays.asList(mapper.readValue(response.body(), Group[].class));
    }

    public void createGroup(Group group) throws Exception {
        String json = mapper.writeValueAsString(group);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "groups"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public void updateGroup(Group group) throws Exception {
        String json = mapper.writeValueAsString(group);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "groups/" + group.getGroupId()))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(json))
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public void deleteGroup(int id) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "groups/" + id))
                .DELETE()
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
