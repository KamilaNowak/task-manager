package Database.DatabaseManager;

import DatabaseModels.Category;
import DatabaseModels.Executor;
import DatabaseModels.Task;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;


import java.io.IOException;
import java.sql.SQLException;

public class DatabaseManager {
    public static final String JDBC_H2_TO_DO_APP_DATABASE_DRIVER = "jdbc:h2:./Database;MODE=MYSQL;DB_CLOSE_DELAY=60;IGNORECASE=TRUE";
    public static final String LOGIN = "admin";
    public static final String PASSWORD = "admin";
    private static ConnectionSource connectionSource;

public static void initializeDatabase() throws Exception {
    createConnectionSource();
    //dropTable(); -----> use when a change occurs
    createDatabaseTable();
    closeConnectionSource();
}

    private static void createConnectionSource() throws Exception {
        try {
            connectionSource = new JdbcConnectionSource(JDBC_H2_TO_DO_APP_DATABASE_DRIVER, LOGIN, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException();
        }
    }

    public static ConnectionSource getConnectionSource() throws Exception {
        if (connectionSource == null) {
            createConnectionSource();
        }
        return connectionSource;
    }
    public static void closeConnectionSource(){
        if(connectionSource!=null){
            try {
                connectionSource.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void createDatabaseTable(){
        try {
            TableUtils.createTableIfNotExists(connectionSource, Category.class);
            TableUtils.createTableIfNotExists(connectionSource, Executor.class);
            TableUtils.createTableIfNotExists(connectionSource, Task.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void dropTable(){
        try {
            TableUtils.dropTable(connectionSource,Category.class,true);
            TableUtils.dropTable(connectionSource,Executor.class,true);
            TableUtils.dropTable(connectionSource,Task.class,true);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}

