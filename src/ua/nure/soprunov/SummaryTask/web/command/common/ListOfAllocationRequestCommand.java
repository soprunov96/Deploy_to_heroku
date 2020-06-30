package ua.nure.soprunov.SummaryTask.web.command.common;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import ua.nure.soprunov.SummaryTask.Path;
import ua.nure.soprunov.SummaryTask.Util.ActionType;
import ua.nure.soprunov.SummaryTask.dao.datasource.DataSourceFactory;
import ua.nure.soprunov.SummaryTask.dao.datasource.DataSourceType;
import ua.nure.soprunov.SummaryTask.dao.implementation.UserDaoImpl;
import ua.nure.soprunov.SummaryTask.dao.implementation.RequestDaoImpl;
import ua.nure.soprunov.SummaryTask.dao.entity.Request;
import ua.nure.soprunov.SummaryTask.dao.entity.User;
import ua.nure.soprunov.SummaryTask.exception.AppException;
import ua.nure.soprunov.SummaryTask.exception.DBException;
import ua.nure.soprunov.SummaryTask.web.command.Command;

/**
 * Command that show all list of requests. Command allowed  for all  logged in users.
 *
 *  @authors Soprunov Igor
 */

public class ListOfAllocationRequestCommand extends Command {

    private static final long serialVersionUID = -2667531056337216678L;
    private static final Logger LOG = Logger.getLogger(ListOfAllocationRequestCommand.class);

    DataSource datasource = DataSourceFactory
            .getDataSource(DataSourceType.MY_SQL_DATASOURCE);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, ActionType actionType)
            throws IOException, ServletException, AppException {
        LOG.debug("Command execution starts");

        String result = null;

        if (actionType == ActionType.GET) {
            result = doGet(request, response);
        }

        LOG.debug("Finished executing Command");
        return result;
    }

    private String doGet(HttpServletRequest request, HttpServletResponse response) throws DBException {
        LOG.debug("Commands starts");
        List<Request> listOfRequest = new RequestDaoImpl(datasource).findAll();
        LOG.trace("Found in DB: listOfRequest --> " + listOfRequest);

        request.setAttribute("listRequest", listOfRequest);

        List<User> listUsers = new UserDaoImpl(datasource).findAll();
        LOG.trace("Found in DB: listUsers --> " + listUsers);


        request.setAttribute("listUsers", listUsers);
        LOG.trace("Set the request attribute: listUsers --> " + listUsers);


        LOG.debug("return " + Path.LIST_OF_ALLOCATION_REQUEST);
        LOG.debug("Commands finished");
        return Path.LIST_OF_ALLOCATION_REQUEST;
    }

}
