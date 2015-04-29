package by.bsuir.library.logic.command;

import by.bsuir.library.bean.ParameterType;
import by.bsuir.library.bean.Request;
import by.bsuir.library.bean.Response;
import by.bsuir.library.dao.DaoException;
import by.bsuir.library.dao.DaoFactory;
import by.bsuir.library.dao.ILibraryDao;
import by.bsuir.library.dao.SourceType;
import by.bsuir.library.logic.ICommand;
import by.bsuir.library.logic.LogicException;
import org.apache.log4j.Logger;

/**
 * Created by Valentin on 29.04.2015.
 */
public class RemoveBookCommand implements ICommand {
    private final static Logger logger = Logger.getRootLogger();
    @Override
    public Response execute(Request request) throws LogicException {
        try {
            ILibraryDao dao = null;
            DaoFactory factory = null;
            if(request.getParam(ParameterType.SOURCE_TYPE) == SourceType.DB)
            {
                factory = DaoFactory.getInstance(SourceType.DB);
            }else
            {
                factory = DaoFactory.getInstance(SourceType.XML);
            }
            dao = factory.getLibraryDao();
            dao.removeBook((Integer)request.getParam(ParameterType.ID));
        } catch (DaoException e) {
            logger.error(e.getMessage());
            throw new LogicException(e);
        }
        Response response = new Response();
        response.setStatus(true);
        return response;
    }
}
