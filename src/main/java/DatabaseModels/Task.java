package DatabaseModels;


import Controllers.TaskWindowController;
import PropertyClasses.TaskPropertyClass;
import Skeletons.TaskDatabaseSkeleton;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.time.LocalDate;
import java.util.Date;

@DatabaseTable(tableName = "TASKS")
public class Task implements BasicDaoManager {

    @DatabaseField(columnName = "ID", generatedId = true, unique = true)
    private int id;

    @DatabaseField(columnName = "TASK")
    private String nameOfTask;

    @DatabaseField(columnName = "DESCRIPTION", unique = true)
    private String description;

    @DatabaseField(columnName = "PRIORITY", width = 1)
    private int priority;

    @DatabaseField(columnName = "ADDED_DATE")
    private Date addedDate;

    @DatabaseField(columnName = "END_DATE", dataType = DataType.DATE_STRING, format = "YYYY-MM-DD")
    private Date releaseDate;

    @DatabaseField(columnName = "CATEGORY",foreign = true,foreignAutoRefresh = true)
    private Category category;

    @DatabaseField(columnName = "EXECUTOR ID",foreign = true,foreignAutoRefresh = true)
    private Executor executor;

    public Task() {
    }

    public int getID() {
        return id;
    }

    public void setID(int ID) {
        this.id = ID;
    }

    public String getNameOfTask() {
        return nameOfTask;
    }

    public void setNameOfTask(String nameOfTask) {
        this.nameOfTask = nameOfTask;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
     //   boolean value = taskPropertyClass.taskCheckboxPropertyProperty().get();
       // if(value)
        //    this.addedDate = taskDatabaseSkeleton.convertDate(LocalDate.now());
       //if(value==false)
           this.addedDate = addedDate;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Executor getExecutor() {
        return executor;
    }

    public void setExecutor(Executor executor) {
        this.executor = executor;
    }
}
