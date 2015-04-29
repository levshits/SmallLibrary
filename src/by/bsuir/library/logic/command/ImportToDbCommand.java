package by.bsuir.library.logic.command;

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
public class ImportToDbCommand implements ICommand {
    private final static Logger logger = Logger.getRootLogger();
    @Override
    public Response execute(Request request) throws LogicException {
        try {
            ILibraryDao xmlDao = null;
            ILibraryDao dbDao = null;
            DaoFactory factory = DaoFactory.getInstance(SourceType.DB);
            dbDao = factory.getLibraryDao();
            factory = DaoFactory.getInstance(SourceType.XML);
            xmlDao = factory.getLibraryDao();
            List<Book> books = xmlDao.getBooks();
            dbDao.addBooks(books);
        } catch (DaoException e) {
            logger.error(e.getMessage());
            throw new LogicException(e);
        }
        Response response = new Response();
        response.setStatus(true);
        return response;
    }
}
