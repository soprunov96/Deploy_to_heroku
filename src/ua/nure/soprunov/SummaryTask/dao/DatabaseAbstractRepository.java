package ua.nure.soprunov.SummaryTask.dao;

import java.sql.*;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import ua.nure.soprunov.SummaryTask.exception.DBException;
import ua.nure.soprunov.SummaryTask.exception.Messages;

/**
 * Main parent for all DAO objects that working with relational databases.
 * Declares and implements methods for closing Connections, ResultSets and
 * Statements. Also performs rollbacking of transaction.And common methods.
 *
 * @param <T> type of entity
 *  @authors Soprunov Igor
 */
public abstract class DatabaseAbstractRepository<T> implements Dao <T>{

    private final static Logger LOG = Logger
            .getLogger(DatabaseAbstractRepository.class);
    protected final DataSource ds;

    /**
     * Initializes DataSource object.
     *
     * @param dataSource
     */
    public DatabaseAbstractRepository(DataSource dataSource) {
        ds = dataSource;
    }


    // //////////////////////////////////////////////////////////
    // SQL queries
    // //////////////////////////////////////////////////////////

    private static final String SQL_FIND_LAST_COMMIT_ID = "SELECT LAST_INSERT_ID() ";

    private static final String SQL_FIND_NUMBER_OF_ROWS = "SELECT COUNT(*) FROM  ";


    /**
     * @return Connection object from the pool.
     * @throws SQLException
     */
    protected Connection getConnection() throws SQLException {
        Connection connection = ds.getConnection();
        connection.setAutoCommit(false);
        return connection;
    }

    /**
     * Closes given Connection object.
     *
     * @param con - connection to be closed
     */
    protected void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                LOG.error("Cannot commit transaction and close connection", ex);
            }
        }
    }

    /**
     * Closes given ResultSet object.
     *
     * @param rs - ResultSet to be closed.
     */
    protected void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                LOG.error("Cannot close a result set", ex);
            }
        }
    }

    /**
     * Closes given Statrmrnt object
     *
     * @param stmt - Statement to be closed
     */
    protected void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                LOG.error("Cannot close a statement", ex);
            }
        }
    }

    /**
     * Rollbacks and close the given connection.
     *
     * @param con Connection to be rollbacked and closed.
     */
    protected void rollback(Connection con) {
        if (con != null) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                LOG.error("Cannot rollback transaction", ex);
            }
        }
    }

    /**
     * Closes resources.
     */
    protected void close(Connection con, Statement stmt, ResultSet rs) {
        close(rs);
        close(stmt);
        close(con);
    }


    // //////////////////////////////////////////////////////////
    // DB common methods
    // //////////////////////////////////////////////////////////

    /**
     * Find last committed id
     */


    public String findLastCommitId() throws DBException {
        String id = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        try {

            con = getConnection();
            assert (con != null);
            con.setAutoCommit(false);
            pstm = con.prepareStatement(SQL_FIND_LAST_COMMIT_ID);

            rs = pstm.executeQuery();
            while (rs.next()) {
                id = rs.getString("LAST_INSERT_ID()");

            }
            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            LOG.error(Messages.ERR_CANNOT_FIND_LAST_COMMIT_ID, ex);
            throw new DBException(Messages.ERR_CANNOT_FIND_LAST_COMMIT_ID, ex);
        } finally {
            close(con, pstm, rs);
        }

        return id;
    }

    /**
     * Get number of rows.
     *
     * @param tableName name of the table, which we will find number of rows.
     */

    public String getNumberOfRows(String tableName) throws DBException {
        String id = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        try {

            con = getConnection();
            assert (con != null);
            con.setAutoCommit(false);
            pstm = con.prepareStatement(SQL_FIND_NUMBER_OF_ROWS + "mydb." + tableName);
            rs = pstm.executeQuery();
            while (rs.next()) {
                id = rs.getString("COUNT");

            }
            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            LOG.error(Messages.ERR_CANNOT_FIND_NUMBER_OF_ROWS, ex);
            throw new DBException(Messages.ERR_CANNOT_FIND_NUMBER_OF_ROWS, ex);
        } finally {
            close(con, pstm, rs);
        }

        return id;

    }


}
