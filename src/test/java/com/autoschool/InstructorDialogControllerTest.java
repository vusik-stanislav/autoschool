package com.autoschool;

import com.autoschool.model.Instructor;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testfx.framework.junit5.ApplicationExtension;

import java.lang.reflect.Field;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(ApplicationExtension.class)
public class InstructorDialogControllerTest {

    @Mock
    private TextField nameField;

    @Mock
    private TextField phoneField;

    @Mock
    private DatePicker hireDatePicker;

    @Mock
    private Stage dialogStage;

    @InjectMocks
    private InstructorDialogController controller;

    private Instructor testInstructor;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        // Создаем тестовые данные
        testInstructor = new Instructor();
        testInstructor.setInstructorId(1);
        testInstructor.setInstructorName("Иванов Иван");
        testInstructor.setPhone("+7 999 123-45-67");
        testInstructor.setHireDate(LocalDate.of(2023, 1, 15));

        // Устанавливаем приватные поля с помощью рефлексии
        setPrivateField(controller, "nameField", nameField);
        setPrivateField(controller, "phoneField", phoneField);
        setPrivateField(controller, "hireDatePicker", hireDatePicker);

        controller.setDialogStage(dialogStage);
    }

    // Вспомогательный метод для установки приватных полей
    private void setPrivateField(Object target, String fieldName, Object value) throws Exception {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }

    @Test
    public void testSetInstructor_WithNullInstructor() {
        // Вызываем метод, который тестируем
        controller.setInstructor(null);

        // Проверяем, что создан новый пустой объект инструктора
        Instructor result = controller.getInstructor();
        assertNotNull(result);
        assertEquals(0, result.getInstructorId());
        assertNull(result.getInstructorName());
        assertNull(result.getPhone());
        assertNull(result.getHireDate());
    }

    @Test
    public void testHandleOk() throws Exception {
        // Настраиваем моки
        when(nameField.getText()).thenReturn("Новый Инструктор");
        when(phoneField.getText()).thenReturn("+7 999 987-65-43");
        when(hireDatePicker.getValue()).thenReturn(LocalDate.of(2023, 5, 10));

        // Создаем новый инструктор в контроллере
        controller.setInstructor(null);

        // Вызываем метод, который тестируем
        controller.handleOk();

        // Проверяем, что окно было закрыто
        verify(dialogStage).close();

        // Проверяем, что флаг okClicked установлен
        Field okClickedField = InstructorDialogController.class.getDeclaredField("okClicked");
        okClickedField.setAccessible(true);
        boolean okClicked = (boolean) okClickedField.get(controller);
        assertTrue(okClicked);

        // Проверяем, что данные инструктора были обновлены
        Instructor result = controller.getInstructor();
        assertEquals("Новый Инструктор", result.getInstructorName());
        assertEquals("+7 999 987-65-43", result.getPhone());
        assertEquals(LocalDate.of(2023, 5, 10), result.getHireDate());
    }

    @Test
    public void testHandleCancel() {
        // Вызываем метод, который тестируем
        controller.handleCancel();

        // Проверяем, что окно было закрыто
        verify(dialogStage).close();
    }

    @Test
    public void testIsOkClicked_InitiallyFalse() throws Exception {
        // Проверяем начальное значение флага
        assertFalse(controller.isOkClicked());
    }

    @Test
    public void testIsOkClicked_AfterHandleOk() throws Exception {
        // Настраиваем моки
        when(nameField.getText()).thenReturn("Тестовый Инструктор");
        when(phoneField.getText()).thenReturn("+7 999 111-22-33");
        when(hireDatePicker.getValue()).thenReturn(LocalDate.now());

        // Создаем новый инструктор в контроллере
        controller.setInstructor(null);

        // Вызываем handleOk
        controller.handleOk();

        // Проверяем, что флаг okClicked установлен
        assertTrue(controller.isOkClicked());
    }
}