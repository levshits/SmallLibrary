package by.bsuir.library.dao.xml;

import by.bsuir.library.entity.Author;
import by.bsuir.library.entity.Book;
import by.bsuir.library.entity.BookGenre;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Valentin on 29.04.2015.
 */
public class LibrarySaxHandler extends DefaultHandler {
    private List<Book> books = new ArrayList<Book>();
    private Book book;
    private Author author;
    private StringBuilder stringBuilder;

    private final static Logger logger = Logger.getRootLogger();

    public List<Book> getBooks() {
        return books;
    }

    @Override
    public void startDocument() throws SAXException {
        logger.debug("Parsing started");
    }

    @Override
    public void endDocument() throws SAXException {
        logger.debug("Parsing ended");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        stringBuilder = new StringBuilder();
        switch (LibraryTagName.valueOf(qName.toUpperCase())){
            case BOOK:
                book = new Book();
                book.setId(Integer.parseInt(attributes.getValue("id")));
                break;
            case AUTHOR:
                author = new Author();
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        stringBuilder.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (LibraryTagName.valueOf(qName.toUpperCase())){
            case TITLE:
                book.setTitle(stringBuilder.toString());
                break;
            case AUTHOR:
                book.setAuthor(author);
                author = null;
                break;
            case ISBN:
                book.setIsbn(stringBuilder.toString());
                break;
            case LANGUAGE:
                book.setLanguage(stringBuilder.toString());
                break;
            case GENRE:
                book.setGenre(BookGenre.valueOf(stringBuilder.toString()));
                break;
            case FIRSTNAME:
                author.setFirstName(stringBuilder.toString());
                break;
            case SECONDNAME:
                author.setSecondName(stringBuilder.toString());
                break;
            case BORN:
                author.setBorn(stringBuilder.toString());
                break;
            case DEAD:
                author.setDead(stringBuilder.toString());
                break;
            case BOOK:
                books.add(book);
                book = null;
                break;
        }
    }

    @Override
    public void warning(SAXParseException e) throws SAXException {
        logger.warn(e.getMessage());
    }

    @Override
    public void error(SAXParseException e) throws SAXException {
        logger.error(e.getMessage());
        throw e;
    }

    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        logger.fatal(e.getMessage());
        throw e;
    }
}
