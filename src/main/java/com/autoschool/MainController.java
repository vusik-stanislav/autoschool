
package com.autoschool;

import com.autoschool.model.Group;
import com.autoschool.model.Instructor;
import com.autoschool.model.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class MainController {
    @FXML private TableView<Instructor> instructorTable;
    @FXML private TableColumn<Instructor, Integer> instructorIdColumn;
    @FXML private TableColumn<Instructor, String> instructorNameColumn;
    @FXML private TableColumn<Instructor, String> instructorPhoneColumn;
    @FXML private TableColumn<Instructor, LocalDate> instructorHireDateColumn;

    @FXML private TableView<Student> studentTable;
    @FXML private TableColumn<Student, Integer> studentIdColumn;
    @FXML private TableColumn<Student, String> studentNameColumn;
    @FXML private TableColumn<Student, String> studentPhoneColumn;
    @FXML private TableColumn<Student, String> studentEmailColumn;
    @FXML private TableColumn<Student, LocalDate> studentRegDateColumn;
    @FXML private TableColumn<Student, String> studentGroupColumn;

    @FXML private TableView<Group> groupTable;
    @FXML private TableColumn<Group, Integer> groupIdColumn;
    @FXML private TableColumn<Group, String> groupNameColumn;
    @FXML private TableColumn<Group, LocalDate> groupStartDateColumn;
    @FXML private TableColumn<Group, LocalDate> groupEndDateColumn;
    @FXML private TableColumn<Group, String> groupInstructorColumn;

    private ApiClient apiClient = new ApiClient();
    private ObservableList<Instructor> instructorData = FXCollections.observableArrayList();
    private ObservableList<Student> studentData = FXCollections.observableArrayList();
    private ObservableList<Group> groupData = FXCollections.observableArrayList();
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @FXML
    private void initialize() {
        setupInstructorTable();
        setupStudentTable();
        setupGroupTable();
        loadData();
    }

    private void setupInstructorTable() {
        instructorIdColumn.setCellValueFactory(new PropertyValueFactory<>("instructorId"));
        instructorNameColumn.setCellValueFactory(new PropertyValueFactory<>("instructorName"));
        instructorPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        instructorHireDateColumn.setCellValueFactory(new PropertyValueFactory<>("hireDate"));
        instructorHireDateColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (empty || date == null) {
                    setText(null);
                } else {
                    setText(DATE_FORMATTER.format(date));
                }
            }
        });
        instructorTable.setItems(instructorData);
    }

    private void setupStudentTable() {
        studentIdColumn.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        studentNameColumn.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        studentPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        studentEmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        studentRegDateColumn.setCellValueFactory(new PropertyValueFactory<>("registrationDate"));
        studentRegDateColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (empty || date == null) {
                    setText(null);
                } else {
                    setText(DATE_FORMATTER.format(date));
                }
            }
        });
        studentGroupColumn.setCellValueFactory(cellData -> {
            Group group = cellData.getValue().getGroup();
            return new javafx.beans.property.SimpleStringProperty(group != null ? group.getGroupName() : "");
        });
        studentTable.setItems(studentData);
    }

    private void setupGroupTable() {
        groupIdColumn.setCellValueFactory(new PropertyValueFactory<>("groupId"));
        groupNameColumn.setCellValueFactory(new PropertyValueFactory<>("groupName"));
        groupStartDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        groupStartDateColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (empty || date == null) {
                    setText(null);
                } else {
                    setText(DATE_FORMATTER.format(date));
                }
            }
        });
        groupEndDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        groupEndDateColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (empty || date == null) {
                    setText(null);
                } else {
                    setText(DATE_FORMATTER.format(date));
                }
            }
        });
        groupInstructorColumn.setCellValueFactory(cellData -> {
            Instructor instructor = cellData.getValue().getInstructor();
            return new javafx.beans.property.SimpleStringProperty(instructor != null ? instructor.getInstructorName() : "");
        });
        groupTable.setItems(groupData);
    }

    void loadData() {
        try {
            instructorData.setAll(apiClient.getInstructors());
            studentData.setAll(apiClient.getStudents());
            groupData.setAll(apiClient.getGroups());
        } catch (Exception e) {
            showError("Ошибка загрузки данных: " + e.getMessage());
        }
    }

    @FXML
    private void handleAddInstructor() {
        showInstructorDialog(null);
    }

    @FXML
    private void handleEditInstructor() {
        Instructor selected = instructorTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            showInstructorDialog(selected);
        } else {
            showError("Выберите инструктора для редактирования.");
        }
    }

    @FXML
    void handleDeleteInstructor() {
        Instructor selected = instructorTable.getSelectionModel().getSelectedItem();
        if (selected != null && confirmDelete("инструктора")) {
            try {
                apiClient.deleteInstructor(selected.getInstructorId());
                instructorData.remove(selected);
            } catch (Exception e) {
                showError("Ошибка удаления: " + e.getMessage());
            }
        }
    }

    @FXML
    private void handleAddStudent() {
        showStudentDialog(null);
    }

    @FXML
    private void handleEditStudent() {
        Student selected = studentTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            showStudentDialog(selected);
        } else {
            showError("Выберите обучающегося для редактирования.");
        }
    }

    @FXML
    void handleDeleteStudent() {
        Student selected = studentTable.getSelectionModel().getSelectedItem();
        if (selected != null && confirmDelete("обучающегося")) {
            try {
                apiClient.deleteStudent(selected.getStudentId());
                studentData.remove(selected);
            } catch (Exception e) {
                showError("Ошибка удаления: " + e.getMessage());
            }
        }
    }

    @FXML
    private void handleAddGroup() {
        showGroupDialog(null);
    }

    @FXML
    private void handleEditGroup() {
        Group selected = groupTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            showGroupDialog(selected);
        } else {
            showError("Выберите группу для редактирования.");
        }
    }

    @FXML
    void handleDeleteGroup() {
        Group selected = groupTable.getSelectionModel().getSelectedItem();
        if (selected != null && confirmDelete("группу")) {
            try {
                apiClient.deleteGroup(selected.getGroupId());
                groupData.remove(selected);
            } catch (Exception e) {
                showError("Ошибка удаления: " + e.getMessage());
            }
        }
    }

    private void showInstructorDialog(Instructor instructor) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/InstructorDialog.fxml"));
            VBox dialogPane = loader.load();
            InstructorDialogController controller = loader.getController();
            controller.setInstructor(instructor);

            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setScene(new Scene(dialogPane));
            dialogStage.setTitle(instructor == null ? "Добавить инструктора" : "Редактировать инструктора");
            controller.setDialogStage(dialogStage);

            dialogStage.showAndWait();

            if (controller.isOkClicked()) {
                Instructor result = controller.getInstructor();
                try {
                    if (instructor == null) {
                        apiClient.createInstructor(result);
                        instructorData.add(result);
                    } else {
                        apiClient.updateInstructor(result);
                        instructorData.set(instructorData.indexOf(instructor), result);
                    }
                } catch (Exception e) {
                    showError("Ошибка сохранения: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            showError("Ошибка открытия диалога: " + e.getMessage());
        }
    }

    private void showStudentDialog(Student student) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/StudentDialog.fxml"));
            VBox dialogPane = loader.load();
            StudentDialogController controller = loader.getController();
            controller.setStudent(student);
            controller.setGroups(groupData);

            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setScene(new Scene(dialogPane));
            dialogStage.setTitle(student == null ? "Добавить обучающегося" : "Редактировать обучающегося");
            controller.setDialogStage(dialogStage);

            dialogStage.showAndWait();

            if (controller.isOkClicked()) {
                Student result = controller.getStudent();
                try {
                    if (student == null) {
                        apiClient.createStudent(result);
                        studentData.add(result);
                    } else {
                        apiClient.updateStudent(result);
                        studentData.set(studentData.indexOf(student), result);
                    }
                } catch (Exception e) {
                    showError("Ошибка сохранения: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            showError("Ошибка открытия диалога: " + e.getMessage());
        }
    }

    private void showGroupDialog(Group group) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GroupDialog.fxml"));
            VBox dialogPane = loader.load();
            GroupDialogController controller = loader.getController();
            controller.setGroup(group);
            controller.setInstructors(instructorData);

            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setScene(new Scene(dialogPane));
            dialogStage.setTitle(group == null ? "Добавить группу" : "Редактировать группу");
            controller.setDialogStage(dialogStage);

            dialogStage.showAndWait();

            if (controller.isOkClicked()) {
                Group result = controller.getGroup();
                try {
                    if (group == null) {
                        apiClient.createGroup(result);
                        groupData.add(result);
                    } else {
                        apiClient.updateGroup(result);
                        groupData.set(groupData.indexOf(group), result);
                    }
                } catch (Exception e) {
                    showError("Ошибка сохранения: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            showError("Ошибка открытия диалога: " + e.getMessage());
        }
    }

    boolean confirmDelete(String entity) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Подтверждение удаления");
        alert.setHeaderText("Вы уверены, что хотите удалить " + entity + "?");
        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    public static void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
