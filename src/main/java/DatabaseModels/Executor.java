package DatabaseModels;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "EXECUTORS")
public class Executor implements BasicDaoManager {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "NAME")
    private String name;

    @DatabaseField(columnName = "SURNAME",unique = true)
    private String surname;

    @ForeignCollectionField(columnName = "TASK", eager = true)
    public ForeignCollection<Task> task;

    public Executor() {

    }
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return id;
    }

    public void setID(int ID) {
        this.id = ID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ForeignCollection<Task> getTask() {
        return task;
    }

    public void setTask(ForeignCollection<Task> task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }

}


