package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class TopButtonsWindowController {

    public static final String SHOW_TASKS_WINDOW_FXML = "/fxml/ShowTasksWindow.fxml";
    public static final String ADD_TASK_WINDOW_FXML = "/fxml/AddTaskWindow.fxml";
    public static final String ADDCATEGORY_WINDOW_FXML = "/fxml/AddcategoryWindow.fxml";
    public static final String ADD_EXECUTOR_WINDOW_FXML = "/fxml/AddExecutorWindow.fxml";
    @FXML
    private MainWindowController mainWindowController;

    public void setMainWindowController(MainWindowController mainWindowController) {
        this.mainWindowController = mainWindowController;
    }

    public void showTasks() {
        mainWindowController.setMainStageFXML(SHOW_TASKS_WINDOW_FXML);
    }

    public void AddTask() {
        mainWindowController.setMainStageFXML(ADD_TASK_WINDOW_FXML);
    }

    public void AddCathegory() {
        mainWindowController.setMainStageFXML(ADDCATEGORY_WINDOW_FXML);
    }

    public void AddUser(ActionEvent actionEvent) {
        mainWindowController.setMainStageFXML(ADD_EXECUTOR_WINDOW_FXML);
    }
}
