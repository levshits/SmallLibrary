package by.bsuir.library.dao.xml;

import by.bsuir.library.dao.DaoException;
import by.bsuir.library.entity.Book;
import org.apache.log4j.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;

/**
 * Created by Valentin on 29.04.2015.
 */
public class XmlSaxParser {
    private final static Logger logger = Logger.getRootLogger();
    private final XMLReader reader;
    private final LibrarySaxHandler handler;

    public XmlSaxParser() throws DaoException {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setNamespaceAware(true);
        spf.setValidating(true);
        handler = new LibrarySaxHandler();
        try {
            SAXParser sp = spf.newSAXParser();
            sp.setProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://www.w3.org/2001/XMLSchema");
            reader = sp.getXMLReader();
            reader.setContentHandler(handler);
            reader.setErrorHandler(handler);
        } catch (SAXException e) {
            logger.error(e.getMessage());
            throw new  DaoException(e);
        } catch (ParserConfigurationException e) {
            logger.error(e.getMessage());
            throw new  DaoException(e);
        }
    }

    public List<Book> parse(String filename) throws DaoException {
        try {
            reader.parse(new InputSource(filename));
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new DaoException(e);
        } catch (SAXException e) {
            logger.error(e.getMessage());
            throw new DaoException(e);
        }
        return handler.getBooks();
    }
}
