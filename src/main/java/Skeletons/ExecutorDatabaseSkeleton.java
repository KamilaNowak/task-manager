package Skeletons;

import Database.DaoManagers.ExecutorDao;
import Database.DatabaseManager.DatabaseManager;
import DatabaseModels.Executor;
import PropertyClasses.ExecutorPropertyClass;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class ExecutorDatabaseSkeleton {

    private ObservableList<ExecutorPropertyClass> executorObservableList = FXCollections.observableArrayList();
    private ObjectProperty<ExecutorPropertyClass> objectProperty = new SimpleObjectProperty<>(new ExecutorPropertyClass());
    private ObjectProperty<ExecutorPropertyClass> selectedOjectProperty = new SimpleObjectProperty<>();

    public void initializeList() throws Exception {
        ExecutorDao executorDao = new ExecutorDao(DatabaseManager.getConnectionSource());
        List<Executor> executors = executorDao.queryForAllItems(Executor.class);
        executorObservableList.clear();
        executors.forEach(e->{
            ExecutorPropertyClass executorPropertyClass = new ExecutorPropertyClass();
            executorPropertyClass.setName(e.getName());
            executorPropertyClass.setSurname(e.getSurname());
            executorObservableList.add(executorPropertyClass);
        });
        DatabaseManager.closeConnectionSource();
    }

    public void saveExecutorInDatabase() throws Exception {
        ExecutorDao executorDao = new ExecutorDao(DatabaseManager.getConnectionSource());
        Executor executor = new Executor();
        executor.setName(getObjectProperty().getName());
        executor.setSurname(getObjectProperty().getSurname());
        executorDao.createOrUpdateDatabse(executor);
        DatabaseManager.closeConnectionSource();
    }

    public void deleteExecutorFromDatabase() throws Exception {
        ExecutorDao executorDao = new ExecutorDao(DatabaseManager.getConnectionSource());
        executorDao.deleteByID(Executor.class,selectedOjectProperty.getValue().getId());
       // DatabaseManager.closeConnectionSource();
        initializeList();
    }
    public ObservableList<ExecutorPropertyClass> getExecutorObservableList() {
        return executorObservableList;
    }

    public void setExecutorObservableList(ObservableList<ExecutorPropertyClass> executorObservableList) {
        this.executorObservableList = executorObservableList;
    }

    public ExecutorPropertyClass getObjectProperty() {
        return objectProperty.get();
    }

    public ObjectProperty<ExecutorPropertyClass> objectProperty() {
        return objectProperty;
    }

    public void setObjectProperty(ExecutorPropertyClass objectProperty) {
        this.objectProperty.set(objectProperty);
    }

    public ObjectProperty<ExecutorPropertyClass> objectPropertyProperty() {
        return objectProperty;
    }

    public ExecutorPropertyClass getSelectedOjectProperty() {
        return selectedOjectProperty.get();
    }

    public ObjectProperty<ExecutorPropertyClass> selectedOjectProperty() {
        return selectedOjectProperty;
    }

    public void setSelectedOjectProperty(ExecutorPropertyClass selectedOjectProperty) {
        this.selectedOjectProperty.set(selectedOjectProperty);
    }
}
