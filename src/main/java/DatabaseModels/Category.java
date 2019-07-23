package DatabaseModels;


import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "CATEGORIES")
public class Category implements BasicDaoManager {

    @DatabaseField(generatedId = true, unique = true)
    private int ID;

    @DatabaseField(columnName = "NAME",unique = true,canBeNull = false)
    private String categoryName;

    @ForeignCollectionField(columnName = "TASK",eager = true)
    public ForeignCollection<Task> task;

    public ForeignCollection<Task> getTask() {
        return task;
    }

    public void setTask(ForeignCollection<Task> task) {
        this.task = task;
    }

    public Category() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
