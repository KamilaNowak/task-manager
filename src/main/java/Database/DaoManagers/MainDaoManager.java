package Database.DaoManagers;

import Database.DatabaseManager.DatabaseManager;
import DatabaseModels.BasicDaoManager;
import DatabaseModels.Category;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import org.omg.CORBA.portable.ApplicationException;
import org.omg.CORBA.portable.InputStream;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class MainDaoManager {
    protected ConnectionSource connectionSource;
    static ResourceBundle bundle = ResourceBundle.getBundle("bundles.controls");
    InputStream ins;

    public MainDaoManager() throws Exception{
        this.connectionSource = connectionSource;
        this.connectionSource= DatabaseManager.getConnectionSource();
    }

    public MainDaoManager(ConnectionSource connectionSource) throws Exception {
        this.connectionSource = connectionSource;
        this.connectionSource= DatabaseManager.getConnectionSource();
    }

    //generic methods to use in all inherited classes from MainDaoManager
    public <T extends BasicDaoManager, I> Dao<T, I> getDao(Class clazz) throws ApplicationException, SQLException {
        try {
            return DaoManager.createDao(connectionSource, clazz);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApplicationException("err", ins);
        }
    }

    public <T extends BasicDaoManager, I> void createOrUpdateDatabse(BasicDaoManager basicDaoManager) throws ApplicationException, SQLException {
        try {
            Dao<T, I> dao = getDao(basicDaoManager.getClass());
            dao.createOrUpdate((T) basicDaoManager);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApplicationException("err", ins);
        }
    }

    public <T extends BasicDaoManager, I> void closeDatabaseConnection() throws IOException {
            this.connectionSource.close();
    }
    public <T extends  BasicDaoManager,I> void deleteByID(Class<T> clazz, Integer id) throws SQLException, ApplicationException {
        Dao<T,I> dao = getDao(clazz);
        dao.deleteById((I) id);
    }
    public<T extends BasicDaoManager,I> T findByID(Class<T> clazz, Integer id)throws SQLException, ApplicationException {
        Dao<T,I> dao = getDao(clazz);
        return dao.queryForId((I) id);
    }
    public <T extends BasicDaoManager,I> List<T> queryForAllItems(Class<T>clazz) throws SQLException, ApplicationException {
        Dao <T,I>  dao = getDao(clazz);
        return  dao.queryForAll();
    }
}

