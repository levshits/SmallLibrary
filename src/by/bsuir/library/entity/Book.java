package by.bsuir.library.entity;

import by.bsuir.library.bean.ParameterType;
import by.bsuir.library.bean.Request;
import by.bsuir.library.logic.LogicException;

import javax.xml.bind.annotation.*;

/**
 * Created by Valentin on 29.04.2015.
 */
@XmlRootElement(name = "book")
@XmlType(propOrder = {"title", "author", "year", "genre", "isbn", "language"})
public class Book {
    private int id;
    private String title = new String();
    private Author author = new Author();
    private int year;
    private BookGenre genre = BookGenre.COMEDY;
    private String isbn = new String();
    private String language = new String();

    public String getTitle() {
        return title;
    }
    @XmlElement(required = true, name = "title")
    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }
    @XmlElement(required = true, name = "author")
    public void setAuthor(Author author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }
    @XmlElement(required = true, name = "year")
    public void setYear(int year) {
        this.year = year;
    }

    public BookGenre getGenre() {
        return genre;
    }
    @XmlElement(required = true, name = "genre")
    public void setGenre(BookGenre genre) {
        this.genre = genre;
    }

    public String getIsbn() {
        return isbn;
    }
    @XmlElement(required = true, name = "isbn")
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getLanguage() {
        return language;
    }
    @XmlElement(required = true, name = "language")
    public void setLanguage(String language) {
        this.language = language;
    }

    public int getId() {
        return id;
    }
    @XmlAttribute(required = true, name = "id")
    public void setId(int id) {
        this.id = id;
    }

    public StringBuffer getFullDescription() {
        StringBuffer result = new StringBuffer();
        result.append(title);
        result.append(" ");
        result.append(year);
        result.append(" ");
        result.append(genre);
        return result;
    }
    public static Book CreateBookFromRequest(Request request) throws LogicException {
        Book book = new Book();
        try {
            book.setId((Integer) request.getParam(ParameterType.ID));
            book.setTitle((String) request.getParam(ParameterType.TITLE));
            book.setGenre((BookGenre) request.getParam(ParameterType.GENRE));
            book.setLanguage((String) request.getParam(ParameterType.LANGUAGE));
            book.setIsbn((String) request.getParam(ParameterType.ISBN));
            book.setYear((Integer) request.getParam(ParameterType.YEAR));
            Author author = new Author();
            author.setFirstName((String) request.getParam(ParameterType.FIRST_NAME));
            author.setSecondName((String) request.getParam(ParameterType.SECOND_NAME));
            author.setBorn((String) request.getParam(ParameterType.BORN));
            author.setDead((String) request.getParam(ParameterType.DEAD));
            book.setAuthor(author);
        }catch (Exception e){
            throw new LogicException(e);
        }
        return book;
    }
}
