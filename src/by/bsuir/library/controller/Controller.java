package by.bsuir.library.controller;


import by.bsuir.library.bean.Request;
import by.bsuir.library.bean.Response;
import by.bsuir.library.logic.ICommand;
import by.bsuir.library.logic.LogicException;
import org.apache.log4j.Logger;

/**
 * Created by Valentin on 29.04.2015.
 */
public class Controller {
    private final static CommandHelper helper = CommandHelper.getInstance();
    private final static Logger logger = Logger.getRootLogger();


    public Response execute(Request request){
        ICommand command = helper.getCommand(request.getActionType());
        Response response = new Response();
        try{
            response = command.execute(request);
        }
        catch (LogicException e){
            response.setStatus(false);
            logger.error(e.getMessage(), e);
        }
        return response;
    }
}
