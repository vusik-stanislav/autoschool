package com.autoschool;

import com.autoschool.model.Group;
import com.autoschool.model.Student;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;

public class StudentDialogController {
    @FXML private TextField nameField;
    @FXML private TextField phoneField;
    @FXML private TextField emailField;
    @FXML private DatePicker regDatePicker;
    @FXML private ComboBox<Group> groupComboBox;

    private Stage dialogStage;
    private Student student;
    private boolean okClicked = false;

    public void setStudent(Student student) {
        this.student = student;
        if (student != null) {
            nameField.setText(student.getStudentName());
            phoneField.setText(student.getPhone());
            emailField.setText(student.getEmail());
            regDatePicker.setValue(student.getRegistrationDate());
            groupComboBox.setValue(student.getGroup());
        } else {
            nameField.setText("");
            phoneField.setText("");
            emailField.setText("");
            regDatePicker.setValue(LocalDate.now());
            groupComboBox.setValue(null);
        }
    }

    public void setGroups(ObservableList<Group> groups) {
        groupComboBox.setItems(groups);
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    public Student getStudent() {
        return new Student(
                student != null ? student.getStudentId() : 0,
                nameField.getText(),
                phoneField.getText(),
                emailField.getText(),
                regDatePicker.getValue(),
                groupComboBox.getValue()
        );
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";
        if (nameField.getText() == null || nameField.getText().trim().isEmpty()) {
            errorMessage += "Имя не может быть пустым!\n";
        }
        if (phoneField.getText() == null || phoneField.getText().trim().isEmpty()) {
            errorMessage += "Телефон не может быть пустым!\n";
        }
        if (emailField.getText() == null || emailField.getText().trim().isEmpty()) {
            errorMessage += "Email не может быть пустым!\n";
        }
        if (regDatePicker.getValue() == null) {
            errorMessage += "Укажите дату регистрации!\n";
        }
        if (groupComboBox.getValue() == null) {
            errorMessage += "Выберите группу!\n";
        }

        if (errorMessage.isEmpty()) {
            return true;
        } else {
            MainController.showError(errorMessage);
            return false;
        }
    }
}