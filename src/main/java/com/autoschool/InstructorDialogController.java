// InstructorDialogController.java
package com.autoschool;

import com.autoschool.model.Instructor;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;

public class InstructorDialogController {
    @FXML private TextField nameField;
    @FXML private TextField phoneField;
    @FXML private DatePicker hireDatePicker;

    private Stage dialogStage;
    private Instructor instructor;
    private boolean okClicked = false;

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
        if (instructor != null) {
            nameField.setText(instructor.getInstructorName());
            phoneField.setText(instructor.getPhone());
            hireDatePicker.setValue(instructor.getHireDate());
        } else {
            nameField.setText("");
            phoneField.setText("");
            hireDatePicker.setValue(LocalDate.now());
        }
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    public Instructor getInstructor() {
        return new Instructor(
                instructor != null ? instructor.getInstructorId() : 0,
                nameField.getText(),
                phoneField.getText(),
                hireDatePicker.getValue()
        );
    }

    @FXML
    void handleOk() {
        if (isInputValid()) {
            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    void handleCancel() {
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
        if (hireDatePicker.getValue() == null) {
            errorMessage += "Укажите дату приема на работу!\n";
        }

        if (errorMessage.isEmpty()) {
            return true;
        } else {
            MainController.showError(errorMessage);
            return false;
        }
    }
}
