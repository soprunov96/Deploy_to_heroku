package ua.nure.soprunov.SummaryTask.web.command.admin;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.soprunov.SummaryTask.Path;
import ua.nure.soprunov.SummaryTask.Util.ActionType;
import ua.nure.soprunov.SummaryTask.Util.SecureHashing;
import ua.nure.soprunov.SummaryTask.dao.datasource.DataSourceFactory;
import ua.nure.soprunov.SummaryTask.dao.datasource.DataSourceType;
import ua.nure.soprunov.SummaryTask.Util.Fields;
import ua.nure.soprunov.SummaryTask.dao.implementation.UserDaoImpl;
import ua.nure.soprunov.SummaryTask.dao.entity.User;
import ua.nure.soprunov.SummaryTask.exception.AppException;
import ua.nure.soprunov.SummaryTask.exception.DBException;
import ua.nure.soprunov.SummaryTask.web.command.Command;

/**
 * Command that edit users. Command allowed only for admins.
 *
 *  @authors Soprunov Igor
 *
 */

public class EditUsersCommand extends Command {

    private static final Logger LOG = Logger.getLogger(EditUsersCommand.class);

    private static final long serialVersionUID = 813054064148539356L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, ActionType actionType)
            throws IOException, ServletException, AppException {
        LOG.debug("Command execution starts");

        String result = null;

        if (actionType == ActionType.GET) {
            result = doGet(request, response);
        } else if (actionType == ActionType.POST) {
            result = doPost(request, response);
        }

        LOG.debug("Finished executing Command");
        return result;
    }

    private String doGet(HttpServletRequest request, HttpServletResponse response) throws DBException {
        LOG.debug("Commands starts");

        String login = request.getParameter(Fields.USER_LOGIN);

        User user = new UserDaoImpl(DataSourceFactory
                .getDataSource(DataSourceType.MY_SQL_DATASOURCE)).findUserByLogin(login);

        LOG.trace("Found in DB: user --> " + user);

        request.setAttribute(Fields.ENTITY_ID, user.getId());
        LOG.trace("Set attribute 'id': " + user.getId());

        request.setAttribute(Fields.USER_LOGIN, login);
        request.setAttribute(Fields.USER_FIRST_NAME, user.getFirstName());
        request.setAttribute(Fields.USER_LAST_NAME, user.getLastName());
        request.setAttribute(Fields.USER_ROLE_ID, user.getRoleId());

        LOG.debug("Commands finished");
        return Path.PAGE_EDIT_USER;
    }

    /**
     * Updates user info.
     *
     * @return path to the view of edited user if all fields were properly
     * filled, otherwise redisplay edit page.
     * @throws DBException
     */
    private String doPost(HttpServletRequest request, HttpServletResponse response) throws DBException {
        // get parameters from page
        LOG.debug("Commands starts");

        String id = request.getParameter(Fields.USER_ID);
        LOG.trace("Fetch request parameter: 'id' = " + id);

        String login = request.getParameter(Fields.USER_LOGIN);
        String password = request.getParameter(Fields.USER_PASSWORD);
        String firstName = request.getParameter(Fields.USER_FIRST_NAME);
        String lastName = request.getParameter(Fields.USER_LAST_NAME);
        String roleId = request.getParameter(Fields.USER_ROLE_ID);
        String strongPassword = null;
        try {
            strongPassword =  SecureHashing.generateStrongPasswordHash(password);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        User user = new UserDaoImpl(DataSourceFactory
                .getDataSource(DataSourceType.MY_SQL_DATASOURCE)).find(Long.parseLong(id));
        user.setLogin(login);
        user.setPassword(strongPassword);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRoleId(Integer.parseInt(roleId));
        LOG.trace("Update  user --> " + user);
        new UserDaoImpl(DataSourceFactory
                .getDataSource(DataSourceType.MY_SQL_DATASOURCE)).update(user);

        LOG.debug("Commands finished");
        return Path.COMMAND_LIST_USERS;
    }

}
