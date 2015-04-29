package by.bsuir.library.bean;

/**
 * Created by Valentin on 29.04.2015.
 */
public class Response {
    private boolean status;
    private String exceptionMessage;
    private Object result;


    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public boolean getsStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }
}
