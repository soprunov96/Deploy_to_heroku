package ua.nure.soprunov.SummaryTask.dao.datasource;


import org.apache.log4j.Logger;
import org.postgresql.ds.PGPoolingDataSource;
import org.postgresql.jdbc3.Jdbc3ConnectionPool;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * DataSourceFactory use to  get datasource from DB with or without JNDI
 * If use JNDI configure context.xml or server.xml
 *
 @authors Soprunov Igor
 *
 */

public abstract class DataSourceFactory {

    private final static Logger LOG = Logger.getLogger(DataSourceFactory.class);

    /**
     * Get dataSource from db with JNDI or without
     *
     * @param type  type of dataSource

     *
     * @return dataSource
     */

    public static DataSource getDataSource(DataSourceType type) {

        switch (type) {

            case MY_SQL_DATASOURCE:
                Context initContext;
                try {
                    initContext = new InitialContext();
                    return (DataSource) initContext
                            .lookup("java:/comp/env/jdbc/mydb");
                } catch (NamingException e) {
                    LOG.error("Cannot get JNDI DataSource", e);
                }
//            case MY_SQL_DATASOURCE_WITH_OUT_JNDI:
//                try {
//                    Class.forName("org.postgresql.Driver");
//                    Jdbc3ConnectionPool dataSource = new Jdbc3ConnectionPool();
//                    dataSource
//                            .setURL("jdbc:mysql://localhost/mydb");
//                    dataSource.setUser("root");
//                    dataSource.setPassword("root");
//                    dataSource.setServerTimezone("UTC");
//                    return dataSource;
//                } catch (ClassNotFoundException | SQLException e) {
//                    LOG.error("Cannot get DataSource without JNDI", e);
//                }

            default:
                throw new UnsupportedOperationException("No such DataSource: "
                        + type);
        }
    }
}
