package ua.nure.soprunov.SummaryTask.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.soprunov.SummaryTask.dao.UserDao;
import ua.nure.soprunov.SummaryTask.Util.Fields;
import ua.nure.soprunov.SummaryTask.dao.entity.User;
import ua.nure.soprunov.SummaryTask.exception.DBException;
import ua.nure.soprunov.SummaryTask.exception.Messages;
import ua.nure.soprunov.SummaryTask.dao.DatabaseAbstractRepository;

import javax.sql.DataSource;

/**
 * User DAO implementation. Performs basic read/write operations on User data.
 *
 *  @authors Soprunov Igor
 */

public class UserDaoImpl extends DatabaseAbstractRepository<User> implements UserDao {

    private static final Logger LOG = Logger.getLogger(UserDaoImpl.class);

    // //////////////////////////////////////////////////////////
    // SQL queries
    // //////////////////////////////////////////////////////////

    private static final String SQL_FIND_LIST_OF_USERS = "SELECT * FROM mydb.users ";

    private static final String SQL_FIND_USER_BY_ID = "SELECT * FROM mydb.users WHERE mydb.users.id=?";

    private static final String SQL_DELETE_USER_BY_ID = "DELETE FROM mydb.users WHERE mydb.users.id=?";

    private static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM mydb.users WHERE mydb.users.login =?";

    private static final String SQL_UPDATE_USER = "UPDATE mydb.users SET mydb.users.password=?, mydb.users.first_name=?, mydb.users.last_name=?, mydb.users.user_avatar=?, mydb.users.role_id=?"
            + "	WHERE users.id=? ";

    private static final String SQL_CREATE_USER = "INSERT INTO mydb.users VALUES (DEFAULT,?,?,?,?,?,?)";



    /**
     * Initializes DataSource object.
     *
     * @param dataSource
     */
    public UserDaoImpl(DataSource dataSource) {
        super(dataSource);
    }


    /**
     * Returns a user with the given identifier.
     *
     * @param id User identifier.
     * @return User entity.
     * @throws DBException
     */
    @Override
    public User find(long id) throws DBException {
        User user = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_FIND_USER_BY_ID);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = extractUser(rs);
            }
            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_ID, ex);
        } finally {
            close(con, pstmt, rs);
        }
        return user;
    }


    /**
     * Update user.
     *
     * @param user user to update.
     * @throws DBException
     */
    @Override
    public void update(User user) throws DBException {
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = getConnection();
            pstmt = connection.prepareStatement(SQL_UPDATE_USER);
            int k = 1;
            pstmt.setString(k++, user.getPassword());
            pstmt.setString(k++, user.getFirstName());
            pstmt.setString(k++, user.getLastName());
            pstmt.setString(k++, user.getUserAvatar());
            pstmt.setLong(k++, user.getRoleId());

            pstmt.setLong(k, user.getId());

            pstmt.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            LOG.error("Can not update a user", e);
        } finally {
            close(connection);
            close(pstmt);
        }
    }

    @Override
    public void delete(User user) throws DBException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_DELETE_USER_BY_ID);
            pstmt.setLong(1, user.getId());
            pstmt.execute();
            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            LOG.error(Messages.ERR_CANNOT_DELETE_USER_BY_ID, ex);
            throw new DBException(Messages.ERR_CANNOT_DELETE_USER_BY_ID, ex);
        } finally {
            close(con, pstmt, null);
        }

    }



    /**
     * Extracts a user entity from the result set.
     *
     * @param rs Result set from which a user entity will be extracted.
     * @return User entity
     */
    static User extractUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong(Fields.ENTITY_ID));
        user.setLogin(rs.getString(Fields.USER_LOGIN));
        user.setPassword(rs.getString(Fields.USER_PASSWORD));
        user.setFirstName(rs.getString(Fields.USER_FIRST_NAME));
        user.setLastName(rs.getString(Fields.USER_LAST_NAME));
        user.setUserAvatar(rs.getString(Fields.USER_AVATAR));
        user.setRoleId(rs.getInt(Fields.USER_ROLE_ID));
        return user;
    }

    /**
     * Returns a user with the given login.
     *
     * @param login User login.
     * @return User entity.
     * @throws DBException
     */

    @Override
    public User findUserByLogin(String login) throws DBException {
        User user = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_FIND_USER_BY_LOGIN);
            pstmt.setString(1, login);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = extractUser(rs);
            }
            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_USER_BY_LOGIN, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_LOGIN, ex);
        } finally {
            close(con, pstmt, rs);
        }
        return user;
    }

    /**
     * Returns a user list.
     *
     * @return User entity.
     * @throws DBException
     */


    @Override
    public List<User> findAll() throws DBException {
        List<User> usersList = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL_FIND_LIST_OF_USERS);
            while (rs.next()) {
                usersList.add(extractUser(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            LOG.error(Messages.ERR_CANNOT_FIND_LIST_OF_USERS, ex);
            throw new DBException(Messages.ERR_CANNOT_FIND_LIST_OF_USERS, ex);
        } finally {
            close(con, stmt, rs);
        }
        return usersList;
    }


    /**
     * Change a  new user .
     *
     * @throws DBException
     */

    @Override
    public void create(User user) throws DBException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_CREATE_USER, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, user.getLogin());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getFirstName());
            pstmt.setString(4, user.getLastName());
            pstmt.setString(5, user.getUserAvatar());
            pstmt.setInt(6, user.getRoleId());
            pstmt.execute();

            con.commit();
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                user.setId(rs.getLong(Fields.GENERATED_KEY));
            }
        } catch (SQLException ex) {
            rollback(con);
            LOG.error(Messages.ERR_CANNOT_CREATE_USER, ex);
            throw new DBException(Messages.ERR_CANNOT_CREATE_USER, ex);
        } finally {
            close(con, pstmt, rs);
        }
    }


}
