package by.bsuir.library.dao.xml;

import by.bsuir.library.dao.DaoException;
import by.bsuir.library.dao.DaoFactory;
import by.bsuir.library.dao.ILibraryDao;

/**
 * Created by Valentin on 29.04.2015.
 */
public class XmlDaoFactory extends DaoFactory {
    private static DaoFactory instance;
    private ILibraryDao dao;

    private XmlDaoFactory() throws DaoException {
        dao = XmlLibraryDao.getInstance();
    }
    @Override
    public ILibraryDao getLibraryDao() {
        return dao;
    }

    public static DaoFactory getInstance() throws DaoException {
        if(instance==null){
            instance = new XmlDaoFactory();
        }
        return instance;
    }
}
