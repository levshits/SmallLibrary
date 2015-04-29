package by.bsuir.library.logic;

/**
 * Created by Valentin on 29.04.2015.
 */
public class LogicException extends Exception {
    public LogicException(String msg, Exception ex){
        super(msg, ex);
    }
    public LogicException(String msg){
        super(msg);
    }
    public LogicException(Exception ex){
        super(ex);
    }
}
