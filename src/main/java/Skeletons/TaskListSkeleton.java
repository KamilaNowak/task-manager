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
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TaskListSkeleton {
    private TaskDatabaseSkeleton taskDatabaseSkeleton = new TaskDatabaseSkeleton();
    private TaskPropertyClass taskPropertyClass = new TaskPropertyClass();
    private ObservableList<TaskPropertyClass> taskObservableList = FXCollections.observableArrayList();
    private ObservableList<CategoryPropertyClass> categoryObservableList = FXCollections.observableArrayList();
    private ObservableList<ExecutorPropertyClass> executoryObservableList = FXCollections.observableArrayList();
    private ObjectProperty<CategoryPropertyClass> categoryObject = new SimpleObjectProperty<>();
    private ObjectProperty<ExecutorPropertyClass> executorObject = new SimpleObjectProperty<>();
    private List<TaskPropertyClass> filterList = new ArrayList<>();

    public void initialTaskList() throws Exception {
        TaskDao taskDao = new TaskDao(DatabaseManager.getConnectionSource());
        List<Task> taskList = taskDao.queryForAllItems(Task.class);
        taskObservableList.clear();

        taskList.forEach(t -> {
            TaskPropertyClass taskPropertyClass = new TaskPropertyClass();
            taskPropertyClass.setTaskTextFieldProperty(t.getNameOfTask());
            taskPropertyClass.setTaskDescriptionFieldProperty(t.getDescription());
            taskPropertyClass.setTaskSliderProperty(t.getPriority());
            taskPropertyClass.setTaskEndDateProperty(taskDatabaseSkeleton.convertToLocalDate(t.getAddedDate()));
            taskPropertyClass.setTaskAddedDateProperty(taskDatabaseSkeleton.convertToLocalDate(t.getReleaseDate()));
            try {
                taskPropertyClass.setTaskCategoryCategory(taskPropertyClass.convertyToProperty(t.getCategory()));
                taskPropertyClass.setTaskExecutorProperty(taskPropertyClass.convertToProperty(t.getExecutor()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            filterList.add(taskPropertyClass);
        });
        taskObservableList.setAll(filterList);
        DatabaseManager.closeConnectionSource();
    }

    public void fillCategoryComboBox() throws Exception {
        CategoryDao categoryDao = new CategoryDao(DatabaseManager.getConnectionSource());
        List<Category> cats = categoryDao.queryForAllItems(Category.class);
        cats.forEach(c -> {
            CategoryPropertyClass categoryPropertyClass = new CategoryPropertyClass();
            categoryPropertyClass.setName(c.getCategoryName());
            categoryPropertyClass.setID(c.getID());
            categoryObservableList.add(categoryPropertyClass);
        });
        DatabaseManager.closeConnectionSource();
    }

    public void fillExecutorComboBox() throws Exception {
        ExecutorDao executorDao = new ExecutorDao(DatabaseManager.getConnectionSource());
        List<Executor> exes = executorDao.queryForAllItems(Executor.class);
        exes.forEach(c -> {
            ExecutorPropertyClass executorPropertyClass = new ExecutorPropertyClass();
            executorPropertyClass.setName(c.getName());
            executorPropertyClass.setSurname(c.getSurname());
            executorPropertyClass.setId(c.getID());
            executoryObservableList.add(executorPropertyClass);
        });
        DatabaseManager.closeConnectionSource();
    }

    private Predicate<TaskPropertyClass> categoryPredicate() {
        Predicate<TaskPropertyClass> predicate = task -> (task.getTaskCategoryCategory().getID()) == (getCategoryObject().getID());
        return predicate;
    }

    private Predicate<TaskPropertyClass> executorPredicate() {
        Predicate<TaskPropertyClass> predicate = task -> (task.getTaskExecutorProperty().getId()) == (getExecutorObject().getId());
        return predicate;
    }
    public void filterTasks() {
        if(getCategoryObject()!=null && getExecutorObject()!=null){
            filterColumn(categoryPredicate().and(executorPredicate()));
            if(getCategoryObject()!=null && getExecutorObject()==null)
                filterColumn(categoryPredicate());
            if(getCategoryObject()==null && getExecutorObject()!=null)
                filterColumn(executorPredicate());
            if(getCategoryObject()==null && getExecutorObject()==null)
                taskObservableList.setAll(filterList);
        }
    }
    public void filterColumn(Predicate<TaskPropertyClass>predicate){
        List<TaskPropertyClass> list = filterList.stream().filter(predicate).collect(Collectors.toList());
        taskObservableList.setAll(list);
    }
    public void showAllItems(){
        taskObservableList.setAll(filterList);
    }

    public TaskPropertyClass getTaskPropertyClass() {
        return taskPropertyClass;
    }

    public void setTaskPropertyClass(TaskPropertyClass taskPropertyClass) {
        this.taskPropertyClass = taskPropertyClass;
    }

    public ObservableList<CategoryPropertyClass> getCategoryObservableList() {
        return categoryObservableList;
    }

    public void setCategoryObservableList(ObservableList<CategoryPropertyClass> categoryObservableList) {
        this.categoryObservableList = categoryObservableList;
    }

    public ObservableList<ExecutorPropertyClass> getExecutoryObservableList() {
        return executoryObservableList;
    }

    public void setExecutoryObservableList(ObservableList<ExecutorPropertyClass> executoryObservableList) {
        this.executoryObservableList = executoryObservableList;
    }

    public TaskDatabaseSkeleton getTaskDatabaseSkeleton() {
        return taskDatabaseSkeleton;
    }

    public void setTaskDatabaseSkeleton(TaskDatabaseSkeleton taskDatabaseSkeleton) {
        this.taskDatabaseSkeleton = taskDatabaseSkeleton;
    }

    public ObservableList<TaskPropertyClass> getTaskObservableList() {
        return taskObservableList;
    }

    public void setTaskObservableList(ObservableList<TaskPropertyClass> taskObservableList) {
        this.taskObservableList = taskObservableList;
    }

    public CategoryPropertyClass getCategoryObject() {
        return categoryObject.get();
    }

    public ObjectProperty<CategoryPropertyClass> categoryObjectProperty() {
        return categoryObject;
    }

    public void setCategoryObject(CategoryPropertyClass categoryObject) {
        this.categoryObject.set(categoryObject);
    }

    public ExecutorPropertyClass getExecutorObject() {
        return executorObject.get();
    }

    public ObjectProperty<ExecutorPropertyClass> executorObjectProperty() {
        return executorObject;
    }

    public void setExecutorObject(ExecutorPropertyClass executorObject) {
        this.executorObject.set(executorObject);
    }
}
