package by.bsuir.library.dao.xml;

import by.bsuir.library.dao.DaoException;
import by.bsuir.library.entity.Library;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Valentin on 30.04.2015.
 */
public class XmlWriter {
    private final static Logger logger = Logger.getRootLogger();
    public void write(String filename, Library library) throws DaoException {
        try {
            FileOutputStream file = new FileOutputStream(filename);
            JAXBContext jaxbContext = JAXBContext.newInstance(Library.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "http://www.bsuir.by/library books.xsd");
            jaxbMarshaller.marshal(library, file);
            file.close();
        } catch (JAXBException e) {
            logger.error(e.getMessage());
            throw new  DaoException(e);
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
            throw new  DaoException(e);
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new  DaoException(e);
        }
    }
}
