package by.bsuir.library.dao;

/**
 * Created by Valentin on 29.04.2015.
 */
public class DaoException extends Exception {
    public DaoException(String msg, Exception ex){
        super(msg, ex);
    }
    public DaoException(String msg){
        super(msg);
    }
    public DaoException(Exception ex){
        super(ex);
    }
}
