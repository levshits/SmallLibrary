package by.bsuir.library.dao;

import by.bsuir.library.entity.Book;

import java.util.List;

/**
 * Created by Valentin on 29.04.2015.
 */
public interface ILibraryDao {
    List<Book> getBooks() throws DaoException;
    void addBook(Book book) throws DaoException;
    void addBooks(List<Book> book) throws DaoException;
    void removeBook(int bookId) throws DaoException;
}
