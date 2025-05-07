package com.autoschool;

import com.autoschool.model.Group;
import com.autoschool.model.Instructor;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;

public class GroupDialogController {
    @FXML private TextField nameField;
    @FXML private DatePicker startDatePicker;
    @FXML private DatePicker endDatePicker;
    @FXML private ComboBox<Instructor> instructorComboBox;

    private Stage dialogStage;
    private Group group;
    private boolean okClicked = false;

    public void setGroup(Group group) {
        this.group = group;
        if (group != null) {
            nameField.setText(group.getGroupName());
            startDatePicker.setValue(group.getStartDate());
            endDatePicker.setValue(group.getEndDate());
            instructorComboBox.setValue(group.getInstructor());
        } else {
            nameField.setText("");
            startDatePicker.setValue(LocalDate.now());
            endDatePicker.setValue(LocalDate.now().plusMonths(3));
            instructorComboBox.setValue(null);
        }
    }

    public void setInstructors(ObservableList<Instructor> instructors) {
        instructorComboBox.setItems(instructors);
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    public Group getGroup() {
        return new Group(
                group != null ? group.getGroupId() : 0,
                nameField.getText(),
                startDatePicker.getValue(),
                endDatePicker.getValue(),
                instructorComboBox.getValue()
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
            errorMessage += "Название группы не может быть пустым!\n";
        }
        if (startDatePicker.getValue() == null) {
            errorMessage += "Укажите дату начала!\n";
        }
        if (endDatePicker.getValue() == null) {
            errorMessage += "Укажите дату окончания!\n";
        }
        if (instructorComboBox.getValue() == null) {
            errorMessage += "Выберите инструктора!\n";
        }

        if (errorMessage.isEmpty()) {
            return true;
        } else {
            MainController.showError(errorMessage);
            return false;
        }
    }
}