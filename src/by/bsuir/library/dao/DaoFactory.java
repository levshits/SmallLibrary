package by.bsuir.library.dao;

import by.bsuir.library.dao.db.DbDaoFactory;
import by.bsuir.library.dao.xml.XmlDaoFactory;

/**
 * Created by Valentin on 29.04.2015.
 */
public abstract class DaoFactory {
    protected DaoFactory(){}
    public abstract ILibraryDao getLibraryDao();
    public static DaoFactory getInstance(SourceType type) throws DaoException {
        DaoFactory result = null;
        switch (type){
            case XML:
               result = XmlDaoFactory.getInstance();
                break;
            case DB:
                result = DbDaoFactory.getInstance();
        }
        return result;
    }
}
