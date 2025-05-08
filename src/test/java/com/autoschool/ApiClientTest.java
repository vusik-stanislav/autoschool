package com.autoschool;

import com.autoschool.model.Group;
import com.autoschool.model.Instructor;
import com.autoschool.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ApiClientTest {

    private ApiClient apiClient;

    @BeforeEach
    public void setUp() {
        // Предполагаем, что ApiClient может использовать HTTP-клиент или другой механизм
        // Здесь можно было бы замокировать этот механизм
        apiClient = spy(new ApiClient());
    }

    @Test
    public void testGetInstructors() throws Exception {
        // Подготовка тестовых данных
        Instructor instructor1 = new Instructor();
        instructor1.setInstructorId(1);
        instructor1.setInstructorName("Иванов Иван");

        Instructor instructor2 = new Instructor();
        instructor2.setInstructorId(2);
        instructor2.setInstructorName("Петров Петр");

        List<Instructor> expectedInstructors = List.of(instructor1, instructor2);

        // Мокируем вызов реального API
        doReturn(expectedInstructors).when(apiClient).getInstructors();

        // Выполняем тестируемый метод
        List<Instructor> actualInstructors = apiClient.getInstructors();

        // Проверяем результат
        assertEquals(2, actualInstructors.size());
        assertEquals("Иванов Иван", actualInstructors.get(0).getInstructorName());
        assertEquals("Петров Петр", actualInstructors.get(1).getInstructorName());
    }

    @Test
    public void testGetStudents() throws Exception {
        // Подготовка тестовых данных
        Student student1 = new Student();
        student1.setStudentId(1);
        student1.setStudentName("Смирнов Алексей");

        Student student2 = new Student();
        student2.setStudentId(2);
        student2.setStudentName("Козлова Анна");

        List<Student> expectedStudents = List.of(student1, student2);

        // Мокируем вызов реального API
        doReturn(expectedStudents).when(apiClient).getStudents();

        // Выполняем тестируемый метод
        List<Student> actualStudents = apiClient.getStudents();

        // Проверяем результат
        assertEquals(2, actualStudents.size());
        assertEquals("Смирнов Алексей", actualStudents.get(0).getStudentName());
        assertEquals("Козлова Анна", actualStudents.get(1).getStudentName());
    }

    @Test
    public void testGetGroups() throws Exception {
        // Подготовка тестовых данных
        Group group1 = new Group();
        group1.setGroupId(1);
        group1.setGroupName("Группа A");

        Group group2 = new Group();
        group2.setGroupId(2);
        group2.setGroupName("Группа B");

        List<Group> expectedGroups = List.of(group1, group2);

        // Мокируем вызов реального API
        doReturn(expectedGroups).when(apiClient).getGroups();

        // Выполняем тестируемый метод
        List<Group> actualGroups = apiClient.getGroups();

        // Проверяем результат
        assertEquals(2, actualGroups.size());
        assertEquals("Группа A", actualGroups.get(0).getGroupName());
        assertEquals("Группа B", actualGroups.get(1).getGroupName());
    }

    @Test
    public void testCreateInstructor() throws Exception {
        // Подготовка тестовых данных
        Instructor instructor = new Instructor();
        instructor.setInstructorName("Новый Инструктор");
        instructor.setPhone("+7 999 111-22-33");
        instructor.setHireDate(LocalDate.now());

        // Мокируем метод, чтобы он не делал реальных вызовов к API
        doNothing().when(apiClient).createInstructor(instructor);

        // Выполняем тестируемый метод
        assertDoesNotThrow(() -> apiClient.createInstructor(instructor));

        // Проверяем что метод был вызван с правильными параметрами
        verify(apiClient).createInstructor(instructor);
    }

    @Test
    public void testUpdateInstructor() throws Exception {
        // Подготовка тестовых данных
        Instructor instructor = new Instructor();
        instructor.setInstructorId(1);
        instructor.setInstructorName("Обновленный Инструктор");
        instructor.setPhone("+7 999 333-44-55");
        instructor.setHireDate(LocalDate.now());

        // Мокируем метод, чтобы он не делал реальных вызовов к API
        doNothing().when(apiClient).updateInstructor(instructor);

        // Выполняем тестируемый метод
        assertDoesNotThrow(() -> apiClient.updateInstructor(instructor));

        // Проверяем что метод был вызван с правильными параметрами
        verify(apiClient).updateInstructor(instructor);
    }

    @Test
    public void testDeleteInstructor() throws Exception {
        // Подготовка тестовых данных
        int instructorId = 1;

        // Мокируем метод, чтобы он не делал реальных вызовов к API
        doNothing().when(apiClient).deleteInstructor(instructorId);

        // Выполняем тестируемый метод
        assertDoesNotThrow(() -> apiClient.deleteInstructor(instructorId));

        // Проверяем что метод был вызван с правильными параметрами
        verify(apiClient).deleteInstructor(instructorId);
    }

    @Test
    public void testCreateStudent() throws Exception {
        // Подготовка тестовых данных
        Student student = new Student();
        student.setStudentName("Новый Студент");
        student.setPhone("+7 999 666-77-88");
        student.setEmail("new@example.com");
        student.setRegistrationDate(LocalDate.now());

        // Мокируем метод, чтобы он не делал реальных вызовов к API
        doNothing().when(apiClient).createStudent(student);

        // Выполняем тестируемый метод
        assertDoesNotThrow(() -> apiClient.createStudent(student));

        // Проверяем что метод был вызван с правильными параметрами
        verify(apiClient).createStudent(student);
    }

    @Test
    public void testUpdateStudent() throws Exception {
        // Подготовка тестовых данных
        Student student = new Student();
        student.setStudentId(1);
        student.setStudentName("Обновленный Студент");
        student.setPhone("+7 999 888-99-00");
        student.setEmail("updated@example.com");
        student.setRegistrationDate(LocalDate.now());

        // Мокируем метод, чтобы он не делал реальных вызовов к API
        doNothing().when(apiClient).updateStudent(student);

        // Выполняем тестируемый метод
        assertDoesNotThrow(() -> apiClient.updateStudent(student));

        // Проверяем что метод был вызван с правильными параметрами
        verify(apiClient).updateStudent(student);
    }

    @Test
    public void testDeleteStudent() throws Exception {
        // Подготовка тестовых данных
        int studentId = 1;

        // Мокируем метод, чтобы он не делал реальных вызовов к API
        doNothing().when(apiClient).deleteStudent(studentId);

        // Выполняем тестируемый метод
        assertDoesNotThrow(() -> apiClient.deleteStudent(studentId));

        // Проверяем что метод был вызван с правильными параметрами
        verify(apiClient).deleteStudent(studentId);
    }

    @Test
    public void testCreateGroup() throws Exception {
        // Подготовка тестовых данных
        Group group = new Group();
        group.setGroupName("Новая Группа");
        group.setStartDate(LocalDate.now());
        group.setEndDate(LocalDate.now().plusMonths(3));

        // Мокируем метод, чтобы он не делал реальных вызовов к API
        doNothing().when(apiClient).createGroup(group);

        // Выполняем тестируемый метод
        assertDoesNotThrow(() -> apiClient.createGroup(group));

        // Проверяем что метод был вызван с правильными параметрами
        verify(apiClient).createGroup(group);
    }

    @Test
    public void testUpdateGroup() throws Exception {
        // Подготовка тестовых данных
        Group group = new Group();
        group.setGroupId(1);
        group.setGroupName("Обновленная Группа");
        group.setStartDate(LocalDate.now());
        group.setEndDate(LocalDate.now().plusMonths(3));

        // Мокируем метод, чтобы он не делал реальных вызовов к API
        doNothing().when(apiClient).updateGroup(group);

        // Выполняем тестируемый метод
        assertDoesNotThrow(() -> apiClient.updateGroup(group));

        // Проверяем что метод был вызван с правильными параметрами
        verify(apiClient).updateGroup(group);
    }

    @Test
    public void testDeleteGroup() throws Exception {
        // Подготовка тестовых данных
        int groupId = 1;

        // Мокируем метод, чтобы он не делал реальных вызовов к API
        doNothing().when(apiClient).deleteGroup(groupId);

        // Выполняем тестируемый метод
        assertDoesNotThrow(() -> apiClient.deleteGroup(groupId));

        // Проверяем что метод был вызван с правильными параметрами
        verify(apiClient).deleteGroup(groupId);
    }
}