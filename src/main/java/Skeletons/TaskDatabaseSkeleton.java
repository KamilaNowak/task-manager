package Skeletons;

import Database.DaoManagers.CategoryDao;
import Database.DaoManagers.ExecutorDao;
import Database.DaoManagers.TaskDao;
import Database.DatabaseManager.DatabaseManager;
import DatabaseModels.Category;
import DatabaseModels.Executor;
import DatabaseModels.Task;
import PropertyClasses.CategoryPropertyClass;
import PropertyClasses.ExecutorPropertyClass;
import PropertyClasses.TaskPropertyClass;
import javafx.beans.InvalidationListener;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class TaskDatabaseSkeleton {

    private ObservableList<CategoryPropertyClass> categoryObservableList = FXCollections.observableArrayList();
    private ObservableList<ExecutorPropertyClass> executorObservableList = FXCollections.observableArrayList();
    private ObjectProperty<TaskPropertyClass> taskObjectProperty = new SimpleObjectProperty<>(new TaskPropertyClass());

    public void saveTaskInDatabase() throws Exception {
        TaskDao taskDao = new TaskDao(DatabaseManager.getConnectionSource());
        Task task = new Task();
        task.setNameOfTask(getTaskObjectProperty().getTaskTextFieldProperty());
        task.setDescription(getTaskObjectProperty().getTaskDescriptionFieldProperty());
        task.setPriority((getTaskObjectProperty().getTaskSliderProperty()));
        task.setAddedDate(convertDate(getTaskObjectProperty().getTaskAddedDateProperty()));
        task.setReleaseDate(convertDate(getTaskObjectProperty().getTaskEndDateProperty()));

        CategoryDao categoryDao = new CategoryDao(DatabaseManager.getConnectionSource());
        ExecutorDao executorDao = new ExecutorDao(DatabaseManager.getConnectionSource());

        Category category = categoryDao.findByID(Category.class,getTaskObjectProperty().getTaskCategoryCategory().getID());
        Executor executor = executorDao.findByID(Executor.class,getTaskObjectProperty().getTaskExecutorProperty().getId());
        task.setExecutor(executor);
        task.setCategory(category);
        taskDao.createOrUpdateDatabse(task);
        DatabaseManager.closeConnectionSource();
    }
    public void initialExecutorsList() throws Exception {
        ExecutorDao executorDao = new ExecutorDao(DatabaseManager.getConnectionSource());
        List<Executor> executors = executorDao.queryForAllItems(Executor.class);
        executorObservableList.clear();
        executors.forEach(e->{
            ExecutorPropertyClass  executorPropertyClass = new ExecutorPropertyClass();
            executorPropertyClass.setName(e.getName());
            executorPropertyClass.setSurname(e.getSurname());
            executorPropertyClass.setId(e.getID());
            executorObservableList.add(executorPropertyClass);
        });
        DatabaseManager.closeConnectionSource();
    }
    public void initialCategoryList() throws Exception {
        CategoryDao categoryDao = new CategoryDao(DatabaseManager.getConnectionSource());
        List<Category> categories = categoryDao.queryForAllItems(Category.class);
        categoryObservableList.clear();
        categories.forEach(c->{
            CategoryPropertyClass categoryPropertyClass = new CategoryPropertyClass();
            categoryPropertyClass.setName(c.getCategoryName());
            categoryPropertyClass.setID(c.getID());
            categoryObservableList.add(categoryPropertyClass);
        });
        DatabaseManager.closeConnectionSource();

    }
    public Date convertDate(LocalDate localDate){
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
    public LocalDate convertToLocalDate(Date date){
        LocalDate Localdate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return Localdate;
    }
    public ObservableList<CategoryPropertyClass> getCategoryObservableList() {
        return categoryObservableList;
    }

    public void setCategoryObservableList(ObservableList<CategoryPropertyClass> categoryObservableList) {
        this.categoryObservableList = categoryObservableList;
    }

    public ObservableList<ExecutorPropertyClass> getExecutorObservableList() {
        return executorObservableList;
    }

    public void setExecutorObservableList(ObservableList<ExecutorPropertyClass> executorObservableList) {
        this.executorObservableList = executorObservableList;
    }

    public TaskPropertyClass getTaskObjectProperty() {
        return taskObjectProperty.get();
    }

    public ObjectProperty<TaskPropertyClass> taskObjectPropertyProperty() {
        return taskObjectProperty;
    }

    public void setTaskObjectProperty(TaskPropertyClass taskObjectProperty) {
        this.taskObjectProperty.set(taskObjectProperty);
    }
}
