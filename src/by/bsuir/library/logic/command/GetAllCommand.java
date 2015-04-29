package by.bsuir.library.logic.command;

import by.bsuir.library.bean.ParameterType;
import by.bsuir.library.bean.Request;
import by.bsuir.library.bean.Response;
import by.bsuir.library.dao.DaoException;
import by.bsuir.library.dao.DaoFactory;
import by.bsuir.library.dao.ILibraryDao;
import by.bsuir.library.dao.SourceType;
import by.bsuir.library.entity.Book;
import by.bsuir.library.logic.ICommand;
import by.bsuir.library.logic.LogicException;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by Valentin on 29.04.2015.
 */
public class GetAllCommand implements ICommand {
    private final static Logger logger = Logger.getRootLogger();

    @Override
    public Response execute(Request request) throws LogicException {
        List<Book> books = null;
        try {
            ILibraryDao dao = null;
            DaoFactory factory = null;
            if((SourceType)request.getParam(ParameterType.SOURCE_TYPE) == SourceType.DB)
            {
                factory = DaoFactory.getInstance(SourceType.DB);
            }else
            {
                factory = DaoFactory.getInstance(SourceType.XML);
            }
            dao = factory.getLibraryDao();
            books = dao.getBooks();
        } catch (DaoException e) {
            logger.error(e.getMessage());
            throw new LogicException(e);
        }
        Response response = new Response();
        response.setStatus(true);
        response.setResult(books);
        return response;
    }
}
