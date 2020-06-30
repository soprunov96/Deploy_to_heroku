package ua.nure.soprunov.SummaryTask.web.command.dispatcherAndAdmin;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import ua.nure.soprunov.SummaryTask.Path;
import ua.nure.soprunov.SummaryTask.Util.ActionType;
import ua.nure.soprunov.SummaryTask.Util.Date;
import ua.nure.soprunov.SummaryTask.dao.datasource.DataSourceFactory;
import ua.nure.soprunov.SummaryTask.dao.datasource.DataSourceType;
import ua.nure.soprunov.SummaryTask.Util.Fields;
import ua.nure.soprunov.SummaryTask.dao.implementation.FlightDaoImpl;
import ua.nure.soprunov.SummaryTask.dao.entity.Flight;
import ua.nure.soprunov.SummaryTask.exception.AppException;
import ua.nure.soprunov.SummaryTask.exception.DBException;
import ua.nure.soprunov.SummaryTask.web.command.Command;

/**
 * Command that create flight. Command allowed  for admins and dispatchers.
 *
 *  @authors Soprunov Igor
 */

public class CreateFlightCommand extends Command {

    private static final long serialVersionUID = 1863978254669586513L;

    private static final Logger LOG = Logger.getLogger(CreateFlightCommand.class);

    DataSource datasource = DataSourceFactory
            .getDataSource(DataSourceType.MY_SQL_DATASOURCE);

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

        String recordsPerPage = request.getParameter(Fields.RECORDS_PER_PAGE);
        LOG.trace("Get attribute 'recordsPerPage': " + recordsPerPage);


        request.setAttribute("localDate", new Date().getLocalDate());
        LOG.trace("Set attribute 'localDate': " + new Date().getLocalDate());

        String currentPage = request.getParameter(Fields.CURRENT_PAGE);
        LOG.trace("Get attribute 'currentPage': " + currentPage);
        LOG.debug("return" + Path.PAGE_CREATE_FLIGHT);
        LOG.debug("Commands finished");
        return Path.PAGE_CREATE_FLIGHT;
    }

    /**
     * Create new flight.
     *
     * @return path to the view of list of flights if all fields were properly
     * filled.
     * @throws DBException
     */
    private String doPost(HttpServletRequest request, HttpServletResponse response) throws DBException {
        // get parameters from page
        LOG.debug("Commands starts");

        String name = request.getParameter(Fields.LIST_FLIGHT_NAME);
        LOG.trace("Get request parameter: 'name' = " + name);

        String date = request.getParameter(Fields.LIST_FLIGHT_DATE);
        LOG.trace("Get request parameter: 'date' = " + date);

        String depart = request.getParameter(Fields.LIST_FLIGHT_DEPART);
        LOG.trace("Get request parameter: 'depart' = " + depart);

        String arrival = request.getParameter(Fields.LIST_FLIGHT_ARRIVAL);
        LOG.trace("Get request parameter: 'arrival' = " + arrival);

        Flight flight = new Flight(name, date, depart, arrival);
        new FlightDaoImpl(datasource).create(flight);


        String recordsPerPage = request.getParameter(Fields.RECORDS_PER_PAGE);
        LOG.trace("Get attribute 'recordsPerPage': " + recordsPerPage);

        String currentPage = request.getParameter(Fields.CURRENT_PAGE);
        LOG.trace("Get attribute 'currentPage': " + currentPage);

        String sortBy = request.getParameter(Fields.SORT_BY);
        LOG.trace("Set attribute 'sortBy': " + sortBy);

        request.setAttribute(Fields.CURRENT_PAGE, currentPage);
        request.setAttribute(Fields.RECORDS_PER_PAGE, recordsPerPage);
        request.setAttribute(Fields.SORT_BY, sortBy);

        return Path.DISPATCHER_COMMAND_LIST_AUTO_FLIGHTS + "&recordsPerPage=" + recordsPerPage + "&currentPage="
                + currentPage + "&sortBy=" + sortBy;

    }
}
