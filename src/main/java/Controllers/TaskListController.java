package Controllers;

import PropertyClasses.CategoryPropertyClass;
import PropertyClasses.ExecutorPropertyClass;
import PropertyClasses.TaskPropertyClass;
import Skeletons.CategoryDatabaseSkeleton;
import Skeletons.ExecutorDatabaseSkeleton;
import Skeletons.TaskListSkeleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.time.LocalDate;

public class TaskListController {

    TaskListSkeleton taskListSkeleton;

    @FXML
    public ComboBox<CategoryPropertyClass> categoryComboBox;
    @FXML
    public ComboBox<ExecutorPropertyClass> executorComboBox;

    @FXML
    public TableView taskTableView;
    @FXML
    public TableColumn<TaskPropertyClass, String> taskName;
    @FXML
    public TableColumn<TaskPropertyClass, String> taskDescription;
    @FXML
    public TableColumn<TaskPropertyClass, Number> taskPriority;
    @FXML
    public TableColumn<TaskPropertyClass, CategoryPropertyClass> taskCategory;
    @FXML
    public TableColumn<TaskPropertyClass, LocalDate> taskAddedDate;
    @FXML
    public TableColumn<TaskPropertyClass, LocalDate> taskEndDate;
    @FXML
    public TableColumn<TaskPropertyClass, ExecutorPropertyClass> taskExecutor;

    public void initialize() throws Exception {
        taskListSkeleton = new TaskListSkeleton();
        taskListSkeleton.initialTaskList();
        columnBindings();
        fillComboBoxes();

    }

    public void columnBindings() {
        taskTableView.setItems(taskListSkeleton.getTaskObservableList());
        taskName.setCellValueFactory(v -> v.getValue().taskTextFieldPropertyProperty());
        taskDescription.setCellValueFactory(v -> v.getValue().taskDescriptionFieldPropertyProperty());
        taskPriority.setCellValueFactory(v -> v.getValue().taskSliderPropertyProperty());
        taskAddedDate.setCellValueFactory(v -> v.getValue().taskAddedDatePropertyProperty());
        taskEndDate.setCellValueFactory(v -> v.getValue().taskEndDatePropertyProperty());
        taskCategory.setCellValueFactory(v -> v.getValue().taskCategoryCategoryProperty());
        taskExecutor.setCellValueFactory(v -> v.getValue().taskExecutorPropertyProperty());
    }

    public void fillComboBoxes() throws Exception {
        categoryComboBox.setItems(taskListSkeleton.getCategoryObservableList());
        executorComboBox.setItems(taskListSkeleton.getExecutoryObservableList());
        taskListSkeleton.fillCategoryComboBox();
        taskListSkeleton.fillExecutorComboBox();
        taskListSkeleton.categoryObjectProperty().bind(categoryComboBox.valueProperty());
        taskListSkeleton.executorObjectProperty().bind(executorComboBox.valueProperty());

    }

    public void filterTasks() {
        taskListSkeleton.filterTasks();
    }

    public void clearAllFilters( ) {
        categoryComboBox.getSelectionModel().clearSelection();
        executorComboBox.getSelectionModel().clearSelection();
        taskListSkeleton.showAllItems();
    }
}
