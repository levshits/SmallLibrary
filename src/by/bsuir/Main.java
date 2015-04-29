package by.bsuir;

import by.bsuir.library.controller.Controller;
import by.bsuir.library.view.View;
import org.apache.log4j.Logger;

import java.io.IOException;

public class Main {

    private static final Logger logger = Logger.getRootLogger();
    public static void main(String[] args) throws IOException {
        logger.debug("Start");

        View view = new View();
        Controller controller = new Controller();
        view.setController(controller);
        view.getAllFromXml();

        //view.addBookToDb(8, "Book", "978-1-56619-909-4", BookGenre.DETECTIVE, 2000, "ru", "Steve", "Conrad", new Date(1953, 10, 5), new Date(2001, 10, 30));
        view.removeFromDb(5);

        //view.importListFromXmlToDb();
        view.writeCommonListToXml("output.xml");

    }
}
