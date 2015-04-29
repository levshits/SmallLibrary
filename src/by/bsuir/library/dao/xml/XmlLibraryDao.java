package by.bsuir.library.dao.xml;

import by.bsuir.library.dao.DaoException;
import by.bsuir.library.dao.ILibraryDao;
import by.bsuir.library.entity.Book;
import by.bsuir.library.entity.Library;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by Valentin on 29.04.2015.
 */
public class XmlLibraryDao implements ILibraryDao {
    private final static Logger logger = Logger.getRootLogger();
    private final static String filename = "src/books.xml";
    private static XmlLibraryDao instance;
    private XmlSaxParser parser;
    private XmlWriter writer;

    private XmlLibraryDao() throws DaoException {
        parser = new XmlSaxParser();
        writer = new XmlWriter();
    }

    public static XmlLibraryDao getInstance() throws DaoException {
        synchronized (filename) {
            if (instance == null) {
                instance = new XmlLibraryDao();
            }
        }
        return instance;
    }

    @Override
    public List<Book> getBooks() throws DaoException {
        return parser.parse(filename);
    }

    @Override
    public void addBook(Book book) throws DaoException {
        List<Book> bookList = parser.parse(filename);
        bookList.add(book);
        Library library = new Library();
        library.setBooks(bookList);
        writer.write(filename, library);
    }

    @Override
    public void addBooks(List<Book> books) throws DaoException {
        List<Book> bookList = parser.parse(filename);
        bookList.addAll(books);
        Library library = new Library();
        library.setBooks(bookList);
        writer.write(filename, library);
    }

    @Override
    public void removeBook(int bookId) throws DaoException {
        List<Book> bookList = parser.parse(filename);
        int bookIndex = -1;
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getId() == bookId) {
                bookIndex = i;
                break;
            }
        }
        if (bookIndex != -1) {
            bookList.remove(bookIndex);
            Library library = new Library();
            library.setBooks(bookList);
            writer.write(filename, library);
        }
    }
}
