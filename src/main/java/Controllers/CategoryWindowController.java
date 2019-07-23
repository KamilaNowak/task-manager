package Controllers;

import PropertyClasses.CategoryPropertyClass;
import Dialogs.DialogWindows;
import Skeletons.CategoryDatabaseSkeleton;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CategoryWindowController {
    @FXML
    private TextField categoryNameTextField;
    @FXML
    private ComboBox<CategoryPropertyClass> categoryComboBox;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button editButton;

    @FXML
    public TableView categoryTableView;
    @FXML
    public TableColumn<CategoryPropertyClass,String> categoryColumn;

    private CategoryDatabaseSkeleton categoryDatabaseSkeleton;

    public void initialize() throws Exception {
        categoryDatabaseSkeleton = new CategoryDatabaseSkeleton();
        categoryDatabaseSkeleton.initialDatabase();
        categoryComboBox.setItems(categoryDatabaseSkeleton.getObservableList());

        fillCategoryTableView();
        buttonDependencies();
    }
    public void fillCategoryTableView(){
        categoryTableView.setItems(categoryDatabaseSkeleton.getObservableList());
        categoryColumn.setCellValueFactory(v->v.getValue().nameProperty());

    }
    public void buttonDependencies() {
        addButton.disableProperty().bind(categoryNameTextField.textProperty().isEmpty());
        deleteButton.disableProperty().bind(categoryDatabaseSkeleton.selectedObservableItemProperty().isNull()); // need info if any of all object is selected
        editButton.disableProperty().bind(categoryDatabaseSkeleton.selectedObservableItemProperty().isNull());
    }

    public void addCategory() throws Exception {
        categoryDatabaseSkeleton.saveCategoryInDatabase(categoryNameTextField.getText());
        categoryNameTextField.clear();
    }

    public void deleteCategory() throws Exception {
        categoryDatabaseSkeleton.deleteCategoryInDatabase();
    }

    public void editCategory() throws Exception {
        String newCategoryName = DialogWindows.editCategoryWindow(categoryDatabaseSkeleton.getSelectedObservableItem().getName());
        if (newCategoryName != null) {
            categoryDatabaseSkeleton.getSelectedObservableItem().setName(newCategoryName);
        }
        categoryDatabaseSkeleton.updateCategoryINdatabase();
    }

    public void getSelectedComboboxItem() {
        categoryDatabaseSkeleton.setSelectedObservableItem(categoryComboBox.getSelectionModel().getSelectedItem());
    }
}
