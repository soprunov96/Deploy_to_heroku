package ua.nure.soprunov.SummaryTask.web.command.dispatcherAndAdmin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.soprunov.SummaryTask.Path;
import ua.nure.soprunov.SummaryTask.Util.ActionType;
import ua.nure.soprunov.SummaryTask.dao.datasource.DataSourceFactory;
import ua.nure.soprunov.SummaryTask.dao.datasource.DataSourceType;
import ua.nure.soprunov.SummaryTask.Util.Fields;
import ua.nure.soprunov.SummaryTask.dao.implementation.FlightDaoImpl;
import ua.nure.soprunov.SummaryTask.dao.entity.Flight;
import ua.nure.soprunov.SummaryTask.exception.AppException;
import ua.nure.soprunov.SummaryTask.web.command.Command;


/**
 * Command that show all list of flights. Command allowed only for admins and dispatcher.
 *
 *  @authors Soprunov Igor
 *
 */

public class ListAutoFlightsCommand extends Command {

    private static final long serialVersionUID = 7732286214029478505L;

    private static final Logger LOG = Logger.getLogger(ListAutoFlightsCommand.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, ActionType actionType)
            throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        String commandName = request.getParameter("command");
        LOG.trace("Request parameter: command --> " + commandName);
        request.setAttribute("commandLink", commandName);

        String recordsPerPage =  request.getParameter(Fields.RECORDS_PER_PAGE);
        LOG.trace("Get attribute 'recordsPerPage': " + recordsPerPage);

        String currentPage = request.getParameter(Fields.CURRENT_PAGE);
        LOG.trace("Get attribute 'currentPage': " + currentPage);

        String sortBy =  request.getParameter(Fields.SORT_BY);
        LOG.trace("Get attribute 'sortBy': " + sortBy);


        if (sortBy == null) {
            sortBy = "id";
        }
        if (recordsPerPage == null) {
            recordsPerPage = "5";
        }
        if (currentPage == null) {
            currentPage = "1";
        }
        int currentPageInt = Integer.parseInt(currentPage) - 1;
        int recordsPerPageInt = Integer.parseInt(recordsPerPage);

        int rows = Integer.parseInt(new FlightDaoImpl(DataSourceFactory
                .getDataSource(DataSourceType.MY_SQL_DATASOURCE)).getNumberOfRows(Fields.TABLE_FLIGHTS)) - 1;
        LOG.trace("number of rows --> " + rows);
        int nOfPages = (rows / recordsPerPageInt);

        if (nOfPages % recordsPerPageInt > 0 | (nOfPages == recordsPerPageInt)) {
            nOfPages++;
        }
        request.setAttribute(Fields.NO_OF_PAGES, nOfPages);
        request.setAttribute(Fields.CURRENT_PAGE, currentPage);
        request.setAttribute(Fields.RECORDS_PER_PAGE, recordsPerPage);
        request.setAttribute(Fields.SORT_BY, sortBy);


        List<Flight> listFlights = new FlightDaoImpl(DataSourceFactory
                .getDataSource(DataSourceType.MY_SQL_DATASOURCE)).findFlightsListSortBy(currentPageInt, recordsPerPageInt, sortBy);
        LOG.trace("Found in DB: listFlights --> " + listFlights);


        request.setAttribute("listFlight", listFlights);
        LOG.trace("Set the request attribute: listFlights --> " + listFlights);

        LOG.debug("Command finished");
        return Path.LIST_AUTO_FLIGHTS;
    }

}
