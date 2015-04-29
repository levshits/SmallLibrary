package by.bsuir.library.dao.db;

import by.bsuir.library.dao.DaoException;
import by.bsuir.library.dao.ILibraryDao;
import by.bsuir.library.entity.Author;
import by.bsuir.library.entity.Book;
import by.bsuir.library.entity.BookGenre;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Valentin on 29.04.2015.
 */
public class DbLibraryDao implements ILibraryDao {
    private final static Logger logger = Logger.getRootLogger();
    private final static String selectAllQuery = "SELECT `id`, `title`, `isbn`, `year`, `genre`, `firstname`, `secondname`, `born`, `dead`, `language` FROM `books`";
    private final static String insertQuery = "INSERT INTO `books`(`id`, `title`, `isbn`, `year`, `genre`, `firstname`, `secondname`, `born`, `dead`, `language`) VALUES (?,?,?,?,?,?,?,?,?,?)";
    private ConnectionPool connectionPool;
    public DbLibraryDao() throws DaoException {
        try {
            ConnectionPool.init();
            connectionPool = ConnectionPool.getInstance();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DaoException(e);
        }
    }
    @Override
    public List<Book> getBooks() throws DaoException {
        List<Book> result = new ArrayList<Book>();
        try {
            Connection con = connectionPool.takeConnection();
            Statement st =  con.createStatement();
            ResultSet resultSet = st.executeQuery(selectAllQuery);
            while (resultSet.next()){
                result.add(getBook(resultSet));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DaoException(e);
        }
        return result;
    }

    private Book getBook(ResultSet resultSet) throws SQLException {
        Book book = new Book();
        book.setIsbn(resultSet.getString("isbn"));
        book.setTitle(resultSet.getString("title"));
        book.setYear(resultSet.getInt("year"));
        book.setId(resultSet.getInt("id"));
        book.setGenre(BookGenre.valueOf(resultSet.getString("genre")));
        book.setLanguage(resultSet.getString("language"));
        Author author = new Author();
        author.setFirstName(resultSet.getString("firstname"));
        author.setSecondName(resultSet.getString("secondname"));
        author.setBorn(resultSet.getDate("born").toString());
        author.setDead(resultSet.getDate("dead").toString());
        book.setAuthor(author);
        return book;
    }

    @Override
    public void addBook(Book book) throws DaoException {
        try {
            Connection con = connectionPool.takeConnection();
            Statement st =  con.createStatement();
            String query = createInsertStatement(st, book);
            st.executeUpdate(query);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DaoException(e);
        }
    }

    private String createInsertStatement(Statement st, Book book) {
        StringBuffer buffer = new StringBuffer("INSERT INTO `books`(`id`, `title`, `isbn`, `year`, `genre`, `firstname`, `secondname`, `born`, `dead`, `language`) VALUES (");
        buffer.append(book.getId());
        buffer.append(", '");
        buffer.append(book.getTitle());
        buffer.append("', '");
        buffer.append(book.getIsbn());
        buffer.append("', ");
        buffer.append(book.getYear());
        buffer.append(", '");
        buffer.append(book.getGenre());
        buffer.append("', '");
        buffer.append(book.getAuthor().getFirstName());
        buffer.append("', '");
        buffer.append(book.getAuthor().getSecondName());
        buffer.append("', '");
        buffer.append(book.getAuthor().getBorn());
        buffer.append("', '");
        buffer.append(book.getAuthor().getDead());
        buffer.append("', '");
        buffer.append(book.getLanguage());
        buffer.append("')");
        return buffer.toString();
    }


    @Override
    public void addBooks(List<Book> book) throws DaoException {
        try {
            Connection con = connectionPool.takeConnection();
            PreparedStatement st =  con.prepareStatement(insertQuery);
            for (int i = 0; i < book.size(); i++) {
                st = getFilledStatement(st, book.get(i));
                st.executeUpdate();
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DaoException(e);
        }
    }

    private PreparedStatement getFilledStatement(PreparedStatement st, Book book) throws SQLException {
        st.setInt(1, book.getId());
        st.setString(2, book.getTitle());
        st.setString(3, book.getIsbn());
        st.setInt(4, book.getYear());
        st.setString(5, book.getGenre().toString());
        st.setString(6, book.getAuthor().getFirstName());
        st.setString(7, book.getAuthor().getSecondName());
        st.setString(8, book.getAuthor().getBorn());
        st.setString(9, book.getAuthor().getDead());
        st.setString(10, book.getLanguage());
        return st;
    }

    @Override
    public void removeBook(int bookId) throws DaoException {
        try {
            Connection con = connectionPool.takeConnection();
            Statement st =  con.createStatement();
            String query = createDeleteStatement(st, bookId);
            st.executeUpdate(query);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DaoException(e);
        }
    }

    private String createDeleteStatement(Statement st, int bookId) {
        StringBuffer buffer = new StringBuffer("DELETE FROM `books` WHERE `id`=");
        buffer.append(bookId);
        return  buffer.toString();
    }
}
