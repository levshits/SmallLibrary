package by.bsuir.library.dao.db;

import by.bsuir.library.dao.DaoException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Valentin on 29.04.2015.
 */
public class ConnectionPool {
    public static final String PROPERTIES_FILE = "db";
    public static final int DEFAULT_POOL_SIZE = 10;
    private static final Logger logger = Logger.getRootLogger();
    private static ConnectionPool instance;
    private BlockingQueue<Connection> connections;

    public static void init() throws SQLException {
        if (instance == null) {
            ResourceBundle rb = ResourceBundle.getBundle(PROPERTIES_FILE);

            String driver = rb.getString("db.driver");
            String url = rb.getString("db.url");
            String user = rb.getString("db.user");
            String password = rb.getString("db.password");
            String poolSizeStr = rb.getString("db.poolsize");

            int poolSize = (poolSizeStr != null) ?
                    Integer.parseInt(poolSizeStr) : DEFAULT_POOL_SIZE;

            try {
                logger.debug("Trying to create pool of connections...");
                instance = new ConnectionPool(driver, url, user, password, poolSize);
                logger.debug("Connection pool succesfully initialized");
            } catch (ClassNotFoundException e) {
                logger.fatal("Driver " + driver + " not found");
                throw new RuntimeException(e);
            }
        }
    }

    public static ConnectionPool getInstance() {
        return instance;
    }

    public Connection takeConnection() throws DaoException {
        Connection connection = null;
        try {
            connection = connections.take();
        } catch (InterruptedException e) {
            logger.warn("Free connection waiting interrupted. Returned `null` connection", e);
            throw new DaoException(e);
        }
        return connection;
    }

    public void returnConnection(Connection connection) throws DaoException {
        try {
            if (!connection.isClosed()) {
                if(!connections.offer(connection)){
                    logger.warn("Connection didn't added. Possible 'leakage' of connections");
                }
            } else {
                logger.warn("Connection already closed. Possible 'leakage' of connections");
            }
        } catch (SQLException e) {
            logger.error("Connection didn't added. Possible `leakage` of connections");
            throw new DaoException(e);
        }
    }

    private ConnectionPool(String driver, String url, String user,
                           String password, int poolSize) throws ClassNotFoundException, SQLException {

        Class.forName(driver);
        connections = new ArrayBlockingQueue<Connection>(poolSize);
        for (int i = 0; i < poolSize; i++) {
            Connection connection = DriverManager.getConnection(url, user, password);
            connections.offer(connection);
        }
    }
}
