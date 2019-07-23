package Controllers;

import PropertyClasses.ExecutorPropertyClass;
import Skeletons.ExecutorDatabaseSkeleton;
import com.j256.ormlite.field.ForeignCollectionField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;

public class ExecutorWindowController {

    @FXML
    public TableView<ExecutorPropertyClass> executorTableView;
    @FXML
    public TableColumn<ExecutorPropertyClass,String> nameColumn;
    @FXML
    public TableColumn<ExecutorPropertyClass,String> surnameColumn;
    @FXML
    public TextField surnameTextField;
    @FXML
    public TextField nameTextField;
    @FXML
    public Button addExecutorButton;
    @FXML
    public MenuItem deleteItemButton;

    ExecutorDatabaseSkeleton executorDatabaseSkeleton;


    public void initialize() throws Exception {
        executorDatabaseSkeleton = new ExecutorDatabaseSkeleton();
        executorDatabaseSkeleton.initializeList();
        executorDatabaseSkeleton.objectProperty().get().nameProperty().bind(nameTextField.textProperty());
        executorDatabaseSkeleton.objectProperty().get().surnameProperty().bind(surnameTextField.textProperty());
        executorTableView.setItems(executorDatabaseSkeleton.getExecutorObservableList());
        nameColumn.setCellValueFactory(c->c.getValue().nameProperty());
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        surnameColumn.setCellValueFactory(c->c.getValue().surnameProperty());
        surnameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        bindings();
       // executorDatabaseSkeleton.initializeList();
        executorTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            executorDatabaseSkeleton.setSelectedOjectProperty(newValue);
        });

    }
    public void addExecutor() throws Exception {
        executorDatabaseSkeleton.saveExecutorInDatabase();
        nameTextField.clear();
        surnameTextField.clear();
        executorDatabaseSkeleton.initializeList();
    }

    public void bindings() {

        addExecutorButton.disableProperty().bind(nameTextField.textProperty().isEmpty());
        deleteItemButton.disableProperty().bind(executorTableView.getSelectionModel().selectedItemProperty().isNull());
    }

    public void deleteExecutor() throws Exception {
        this.executorDatabaseSkeleton.deleteExecutorFromDatabase();
        executorDatabaseSkeleton.initializeList();
    }
}
