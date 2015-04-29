package by.bsuir.library.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Valentin on 30.04.2015.
 */
@XmlRootElement(name = "library", namespace ="http://www.bsuir.by/library")
public class Library {

    private List<Book> book;

    public List<Book> getBooks() {
        return book;
    }
    @XmlElement(required = true, name = "book")
    public void setBooks(List<Book> books) {
        this.book = books;
    }
}
