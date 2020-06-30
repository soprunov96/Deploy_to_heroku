package ua.nure.soprunov.SummaryTask.web.command.common;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.soprunov.SummaryTask.Util.SecureHashing;
import ua.nure.soprunov.SummaryTask.dao.datasource.DataSourceFactory;
import ua.nure.soprunov.SummaryTask.dao.datasource.DataSourceType;
import ua.nure.soprunov.SummaryTask.Util.Role;
import ua.nure.soprunov.SummaryTask.dao.implementation.UserDaoImpl;
import ua.nure.soprunov.SummaryTask.dao.entity.User;
import ua.nure.soprunov.SummaryTask.exception.AppException;
import ua.nure.soprunov.SummaryTask.Path;
import ua.nure.soprunov.SummaryTask.Util.ActionType;
import ua.nure.soprunov.SummaryTask.exception.Messages;
import ua.nure.soprunov.SummaryTask.web.command.Command;

/**
 * Login command. Command allowed  for all users.
 *
 *  @authors Soprunov Igor
 */

public class LoginCommand extends Command {

    private static final long serialVersionUID = -3071536593627692473L;

    private static final Logger LOG = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, ActionType actionType)
            throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        HttpSession session = request.getSession();

        String login = request.getParameter("login");
        LOG.trace("Request parameter: login --> " + login);

        // obtain login and password from a request
        String password = request.getParameter("password");
        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            String exeptionMessasge = "Login/password cannot be empty";
            LOG.debug("Default locale :" + Locale.getDefault());
            if ((Locale.getDefault().toString()).equals("ru") | (Locale.getDefault().toString()).equals("ru_RU")) {
                exeptionMessasge = Messages.EXCEPTION_LOGIN_AND_PASSWORD_CANNOT_BE_EMPTY;
            }
            if ((Locale.getDefault().toString()).equals("en") | (Locale.getDefault().toString()).equals("en_EN")) {
                exeptionMessasge = "Login/password cannot be empty";
            }
            throw new AppException(exeptionMessasge);
        }
        LOG.trace("Request parameter: login --> " + password);

        User user = new UserDaoImpl(DataSourceFactory
                .getDataSource(DataSourceType.MY_SQL_DATASOURCE)).findUserByLogin(login);
        LOG.trace("Found in DB: user --> " + user);
        boolean validationPassword = false;
        if (user != null) {

            try {

                validationPassword = SecureHashing.validatePassword(password, user.getPassword());
                LOG.trace("Password valid --> " + validationPassword);
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                e.printStackTrace();
            }
        }

        if (user == null || !validationPassword) {
            String exeptionMessasge = "Cannot find user with such login/password";
            LOG.debug("Default locale :" + Locale.getDefault());
            if ((Locale.getDefault().toString()).equals("ru") | (Locale.getDefault().toString()).equals("ru_RU")) {
                exeptionMessasge = Messages.EXCEPTION_NO_USER_WITH_SUCH_LOGIN_OR_PASSWORD_RU;
            }
            if ((Locale.getDefault().toString()).equals("en") | (Locale.getDefault().toString()).equals("en_EN")) {
                exeptionMessasge = "Cannot find user with such login/password";
            }
            throw new AppException(exeptionMessasge);
        }

        Role userRole = Role.getRole(user);
        LOG.trace("userRole --> " + userRole);

        String forward = Path.PAGE_ERROR_PAGE;

        if (userRole == Role.ADMIN) {
            forward = Path.DISPATCHER_COMMAND_LIST_AUTO_FLIGHTS;
        }
        if (userRole == Role.DISPATCHER) {
            forward = Path.DISPATCHER_COMMAND_LIST_AUTO_FLIGHTS;
        }
        if (userRole == Role.DRIVER) {
            forward = Path.DRIVER_COMMAND_LIST_AUTO_FLIGHTS;
        }



        session.setAttribute("user", user);
        LOG.trace("Set the session attribute: user --> " + user);

        session.setAttribute("userRole", userRole);
        LOG.trace("Set the session attribute: userRole --> " + userRole);

        LOG.info("User " + user + " logged as " + userRole.toString().toLowerCase());

        LOG.debug("Command finished");
        return forward;
    }

}