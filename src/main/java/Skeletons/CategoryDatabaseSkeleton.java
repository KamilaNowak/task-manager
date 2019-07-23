package Skeletons;

import Database.DaoManagers.CategoryDao;
import Database.DatabaseManager.DatabaseManager;
import DatabaseModels.Category;
import PropertyClasses.CategoryPropertyClass;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class CategoryDatabaseSkeleton {
    private ObservableList<CategoryPropertyClass> observableList = FXCollections.observableArrayList();
    private ObjectProperty<CategoryPropertyClass> selectedObservableItem = new SimpleObjectProperty<>(); //returns selected property
    //contains id of each category in database / from database

    public void saveCategoryInDatabase(String name) throws Exception {
        CategoryDao categoryDao = new CategoryDao(DatabaseManager.getConnectionSource());
        Category category = new Category();
        category.setCategoryName(name);
        categoryDao.createOrUpdateDatabse(category);
        DatabaseManager.closeConnectionSource();
        initialDatabase();
    }

    public void updateCategoryINdatabase( ) throws Exception{
        CategoryDao categoryDao = new CategoryDao(DatabaseManager.getConnectionSource());
        Category category = categoryDao.findByID(Category.class,selectedObservableItem.getValue().getID());
        category.setCategoryName(getSelectedObservableItem().getName());
        categoryDao.createOrUpdateDatabse(category);
        DatabaseManager.closeConnectionSource();
        initialDatabase();
    }

    public void deleteCategoryInDatabase() throws Exception{
        CategoryDao categoryDao = new CategoryDao(DatabaseManager.getConnectionSource());
        categoryDao.deleteByID(Category.class,selectedObservableItem.getValue().getID());
        DatabaseManager.closeConnectionSource();
        initialDatabase();
    }

    public void initialDatabase() throws Exception {
        CategoryDao categoryDao = new CategoryDao(DatabaseManager.getConnectionSource());
        List<Category> list = categoryDao.queryForAllItems(Category.class);
        observableList.clear();
        list.forEach(e->{
            CategoryPropertyClass categoryPropertyClass = new CategoryPropertyClass();
            categoryPropertyClass.setID(e.getID());
            categoryPropertyClass.setName((e.getCategoryName()));
            observableList.add(categoryPropertyClass);
        });
        DatabaseManager.closeConnectionSource();
    }

    public ObservableList<CategoryPropertyClass> getObservableList() {
        return observableList;
    }

    public void setObservableList(ObservableList<CategoryPropertyClass> observableList) {
        this.observableList = observableList;
    }

    public CategoryPropertyClass getSelectedObservableItem() {
        return selectedObservableItem.get();
    }

    public ObjectProperty<CategoryPropertyClass> selectedObservableItemProperty() {
        return selectedObservableItem;
    }

    public void setSelectedObservableItem(CategoryPropertyClass selectedObservableItem) {
        this.selectedObservableItem.set(selectedObservableItem);
    }

}
