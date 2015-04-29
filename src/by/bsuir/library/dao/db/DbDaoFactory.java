package by.bsuir.library.dao.db;

import by.bsuir.library.dao.DaoException;
import by.bsuir.library.dao.DaoFactory;
import by.bsuir.library.dao.ILibraryDao;

/**
 * Created by Valentin on 29.04.2015.
 */
public class DbDaoFactory extends DaoFactory {
    private static DaoFactory instance;
    private ILibraryDao dao;

    private DbDaoFactory() throws DaoException {
        dao = new DbLibraryDao();
    }
    @Override
    public ILibraryDao getLibraryDao() {
        return dao;
    }

    public static DaoFactory getInstance() throws DaoException {
        if(instance == null){
            instance = new DbDaoFactory();
        }
        return instance;
    }
}
