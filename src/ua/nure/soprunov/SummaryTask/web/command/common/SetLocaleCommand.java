package ua.nure.soprunov.SummaryTask.web.command.common;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.soprunov.SummaryTask.Path;
import ua.nure.soprunov.SummaryTask.Util.ActionType;

import ua.nure.soprunov.SummaryTask.Util.Fields;
import ua.nure.soprunov.SummaryTask.exception.AppException;
import ua.nure.soprunov.SummaryTask.exception.Messages;
import ua.nure.soprunov.SummaryTask.web.command.Command;


/**
 * Change locale command. Command allowed  for all users.
 *
 *  @authors Soprunov Igor
 */

public class SetLocaleCommand extends Command {


    private static final long serialVersionUID = -5105297255991909258L;

    private static final Logger LOG = Logger.getLogger(SetLocaleCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, ActionType actionType)
            throws IOException, ServletException, AppException {
        LOG.debug("Command execution starts");

        HttpSession session = request.getSession();

        String locale = request.getParameter(Fields.LOCALE);
        LOG.trace("Get attribute 'locale': " + locale);

        session.setAttribute("locale", locale);
        LOG.trace("Set attribute 'locale': " + locale);
        Locale.setDefault(new Locale(locale));
        LOG.trace("Default locale: " + Locale.getDefault());

//        session.setAttribute("currentLocale", locale);

        String command = request.getParameter(Fields.PREVIOUS_COMMAND);
        LOG.trace("Get attribute 'previousCommand': " + command);

        try {
            if (command == null ) {

                LOG.trace("Return " + Path.COMMAND_SHOW_START_PAGE);
                LOG.debug("Finished executing Command");
                return Path.COMMAND_SHOW_START_PAGE;
            }else
            if (command.equals("")){
                LOG.trace("Return " + Path.COMMAND_SHOW_START_PAGE);
                LOG.debug("Finished executing Command");
                return Path.COMMAND_SHOW_START_PAGE;
            }
        } catch
        (Exception ex) {
            LOG.error(Messages.NULL_POINTER_EXEPTION, ex);
            LOG.trace("Return " + Path.COMMAND_SHOW_START_PAGE);
            LOG.debug("Finished executing Command");
            return Path.COMMAND_SHOW_START_PAGE;
        }
        LOG.debug("Finished executing Command");
//        System.out.println(request.getRequestURL().toString());

        return "?" + command;
    }
}
