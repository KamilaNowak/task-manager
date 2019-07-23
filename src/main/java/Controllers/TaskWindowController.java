package Controllers;

import PropertyClasses.CategoryPropertyClass;
import PropertyClasses.ExecutorPropertyClass;
import PropertyClasses.TaskPropertyClass;
import Skeletons.TaskDatabaseSkeleton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class TaskWindowController {
    private TaskDatabaseSkeleton taskDatabaseSkeleton;
    private TaskPropertyClass taskPropertyClass;
    @FXML
    private ComboBox<CategoryPropertyClass> taskCategory;
    @FXML
    private TextField taskTextField;
    @FXML
    private TextArea taskDescriptionField;
    @FXML
    private Slider taskSlider;
    @FXML
    private ComboBox<ExecutorPropertyClass> taskExecutor;
    @FXML
    private DatePicker taskAddedDate;
    @FXML
    private DatePicker taskEndDate;
    @FXML
    private CheckBox taskCheckbox;
    @FXML
    private Button taskAddButton;

    private CategoryPropertyClass categoryPropertyClass;

    public void initialize() throws Exception {
        taskDatabaseSkeleton = new TaskDatabaseSkeleton();
        taskCheckbox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                taskPropertyClass.setTaskCheckboxProperty(newValue);
            }
        });
        bindings();
        fillComboBoxes();
        controlsBindings();
    }

    private void bindings() {
        taskAddedDate.disableProperty().bind(taskCheckbox.selectedProperty());
        taskDescriptionField.disableProperty().bind(taskTextField.textProperty().isEmpty());
        taskSlider.disableProperty().bind(taskDescriptionField.textProperty().isEmpty());
        taskExecutor.disableProperty().bind(taskSlider.valueProperty().isEqualTo(0));
        taskCategory.disableProperty().bind(taskExecutor.valueProperty().isNull());
        taskEndDate.disableProperty().bind(taskCategory.valueProperty().isNull());
        taskAddButton.disableProperty().bind(taskEndDate.valueProperty().isNull());
    }

    private void controlsBindings() {
        taskDatabaseSkeleton.getTaskObjectProperty().taskCategoryCategoryProperty().bind(taskCategory.valueProperty());
        taskDatabaseSkeleton.getTaskObjectProperty().taskExecutorPropertyProperty().bind(taskExecutor.valueProperty());
        taskDatabaseSkeleton.getTaskObjectProperty().taskDescriptionFieldPropertyProperty().bind(taskDescriptionField.textProperty());
        taskDatabaseSkeleton.getTaskObjectProperty().taskTextFieldPropertyProperty().bind(taskTextField.textProperty());
        taskDatabaseSkeleton.getTaskObjectProperty().taskSliderPropertyProperty().bind(taskSlider.valueProperty());
        taskDatabaseSkeleton.getTaskObjectProperty().taskAddedDatePropertyProperty().bind(taskAddedDate.valueProperty());
        taskDatabaseSkeleton.getTaskObjectProperty().taskEndDatePropertyProperty().bind(taskEndDate.valueProperty());

    }

    public void clearFields() {
        taskTextField.clear();
        taskDescriptionField.clear();
        taskCategory.getSelectionModel().clearSelection();
        taskExecutor.getSelectionModel().clearSelection();
        taskEndDate.getEditor().clear();
    }

    public void fillComboBoxes() throws Exception {
        taskDatabaseSkeleton.initialCategoryList();
        taskDatabaseSkeleton.initialExecutorsList();
        taskCategory.setItems(taskDatabaseSkeleton.getCategoryObservableList());
        taskExecutor.setItems(taskDatabaseSkeleton.getExecutorObservableList());
    }

    public void addTask() throws Exception {
        taskDatabaseSkeleton.saveTaskInDatabase();
        System.out.println(getTaskCheckboxValue());
        clearFields();
    }

    public boolean getTaskCheckboxValue() {
        return taskDatabaseSkeleton.getTaskObjectProperty().taskCheckboxPropertyProperty().getValue();
    }
}
