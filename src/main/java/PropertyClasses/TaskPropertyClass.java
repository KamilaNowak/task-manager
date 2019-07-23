package PropertyClasses;

import Controllers.TaskWindowController;
import Database.DaoManagers.CategoryDao;
import Database.DatabaseManager.DatabaseManager;
import DatabaseModels.Category;
import DatabaseModels.Executor;
import Skeletons.TaskDatabaseSkeleton;
import javafx.beans.property.*;

import java.time.LocalDate;
import java.util.Date;

public class TaskPropertyClass {
    StringProperty taskTextFieldProperty = new SimpleStringProperty();
    StringProperty taskDescriptionFieldProperty = new SimpleStringProperty();
    IntegerProperty taskSliderProperty = new SimpleIntegerProperty();
    BooleanProperty taskCheckboxProperty = new SimpleBooleanProperty();
    ObjectProperty<ExecutorPropertyClass> taskExecutorProperty = new SimpleObjectProperty<>();
    ObjectProperty<CategoryPropertyClass> taskCategoryCategory = new SimpleObjectProperty<>();
    ObjectProperty<LocalDate> taskEndDateProperty = new SimpleObjectProperty<>();
    ObjectProperty<LocalDate> taskAddedDateProperty = new SimpleObjectProperty<>(LocalDate.now());

    public CategoryPropertyClass convertyToProperty(Category category) throws Exception {
        CategoryPropertyClass categoryPropertyClass = new CategoryPropertyClass();
        categoryPropertyClass.setName(category.getCategoryName());
        categoryPropertyClass.setID(category.getID());
        return categoryPropertyClass;
    }
    public ExecutorPropertyClass convertToProperty(Executor executor){
        ExecutorPropertyClass executorPropertyClass = new ExecutorPropertyClass();
        executorPropertyClass.setName(executor.getName());
        executorPropertyClass.setSurname(executor.getSurname());
        executorPropertyClass.setId(executor.getID());
        return executorPropertyClass;
    }

    public CategoryPropertyClass getTaskCategoryCategory() {
        return taskCategoryCategory.get();
    }

    public ObjectProperty<CategoryPropertyClass> taskCategoryCategoryProperty() {
        return taskCategoryCategory;
    }

    public void setTaskCategoryCategory(CategoryPropertyClass taskCategoryCategory) {
        this.taskCategoryCategory.set(taskCategoryCategory);
    }

    public LocalDate getTaskAddedDateProperty() {
        return taskAddedDateProperty.get();
    }

    public ObjectProperty<LocalDate> taskAddedDatePropertyProperty() {
        return taskAddedDateProperty;
    }

    public void setTaskAddedDateProperty(LocalDate taskAddedDateProperty) {
        this.taskAddedDateProperty.set(taskAddedDateProperty);
    }

    public String getTaskTextFieldProperty() {
        return taskTextFieldProperty.get();
    }

    public StringProperty taskTextFieldPropertyProperty() {
        return taskTextFieldProperty;
    }

    public void setTaskTextFieldProperty(String taskTextFieldProperty) {
        this.taskTextFieldProperty.set(taskTextFieldProperty);
    }

    public String getTaskDescriptionFieldProperty() {
        return taskDescriptionFieldProperty.get();
    }

    public StringProperty taskDescriptionFieldPropertyProperty() {
        return taskDescriptionFieldProperty;
    }

    public void setTaskDescriptionFieldProperty(String taskDescriptionFieldProperty) {
        this.taskDescriptionFieldProperty.set(taskDescriptionFieldProperty);
    }

    public int getTaskSliderProperty() {
        return taskSliderProperty.get();
    }

    public IntegerProperty taskSliderPropertyProperty() {
        return taskSliderProperty;
    }

    public void setTaskSliderProperty(int taskSliderProperty) {
        this.taskSliderProperty.set(taskSliderProperty);
    }

    public boolean isTaskCheckboxProperty() {
        return taskCheckboxProperty.get();
    }

    public BooleanProperty taskCheckboxPropertyProperty() {
        return taskCheckboxProperty;
    }

    public void setTaskCheckboxProperty(boolean taskCheckboxProperty) {
        this.taskCheckboxProperty.set(taskCheckboxProperty);
    }

    public ExecutorPropertyClass getTaskExecutorProperty() {
        return taskExecutorProperty.get();
    }

    public ObjectProperty<ExecutorPropertyClass> taskExecutorPropertyProperty() {
        return taskExecutorProperty;
    }

    public void setTaskExecutorProperty(ExecutorPropertyClass taskExecutorProperty) {
        this.taskExecutorProperty.set(taskExecutorProperty);
    }

    public LocalDate getTaskEndDateProperty() {
        return taskEndDateProperty.get();
    }

    public ObjectProperty<LocalDate> taskEndDatePropertyProperty() {
        return taskEndDateProperty;
    }

    public void setTaskEndDateProperty(LocalDate taskEndDateProperty) {
        this.taskEndDateProperty.set(taskEndDateProperty);
    }
}
