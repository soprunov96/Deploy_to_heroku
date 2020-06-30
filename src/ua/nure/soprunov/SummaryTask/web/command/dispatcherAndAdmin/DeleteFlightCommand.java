package ua.nure.soprunov.SummaryTask.web.command.dispatcherAndAdmin;

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
import ua.nure.soprunov.SummaryTask.Util.Fields;
import ua.nure.soprunov.SummaryTask.dao.entity.Request;
import ua.nure.soprunov.SummaryTask.dao.implementation.FlightDaoImpl;
import ua.nure.soprunov.SummaryTask.dao.entity.Flight;
import ua.nure.soprunov.SummaryTask.dao.implementation.RequestDaoImpl;
import ua.nure.soprunov.SummaryTask.exception.AppException;
import ua.nure.soprunov.SummaryTask.web.command.Command;

/**
 * Command that delete flight. Command allowed  for admins and dispatchers.
 *
 *  @authors Soprunov Igor
 */

public class DeleteFlightCommand extends Command {
    private static final Logger LOG = Logger.getLogger(DeleteFlightCommand.class);

    private static final long serialVersionUID = 1L;
    DataSource datasource = DataSourceFactory
            .getDataSource(DataSourceType.MY_SQL_DATASOURCE);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, ActionType actionType)
            throws IOException, ServletException, AppException {
        LOG.debug("Commands starts");


        String id = request.getParameter(Fields.LIST_FLIGHT_ID);
        LOG.trace("Fetch request parapeter: 'id' = " + id);

        Flight flight = new FlightDaoImpl(datasource).find(Long.parseLong(id));
        new FlightDaoImpl(datasource).delete(flight);

        Request requestId = new RequestDaoImpl(datasource).find(flight.getRequestId());
        if (requestId != null) {
            new RequestDaoImpl(datasource).delete(requestId);
        }
        String recordsPerPage = request.getParameter(Fields.RECORDS_PER_PAGE);
        LOG.trace("Get attribute 'recordsPerPage': " + recordsPerPage);

        String currentPage = request.getParameter(Fields.CURRENT_PAGE);
        LOG.trace("Get attribute 'currentPage': " + currentPage);

        String sortBy = request.getParameter(Fields.SORT_BY);
        LOG.trace("Set attribute 'sortBy': " + sortBy);

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

        int rows = Integer.parseInt(new FlightDaoImpl(datasource).getNumberOfRows(Fields.TABLE_FLIGHTS)) - 1;
        int nOfPages = rows / recordsPerPageInt;

        if (nOfPages % recordsPerPageInt > 0) {
            nOfPages++;
        }
        request.setAttribute(Fields.NO_OF_PAGES, nOfPages);
        request.setAttribute(Fields.CURRENT_PAGE, currentPage);
        request.setAttribute(Fields.RECORDS_PER_PAGE, recordsPerPage);
        request.setAttribute(Fields.SORT_BY, sortBy);

        // get menu items list
        List<Flight> listFlights = new FlightDaoImpl(datasource).findFlightsListSortBy(currentPageInt, recordsPerPageInt, sortBy);
        LOG.trace("Found in DB: listFlights --> " + listFlights);

        // put menu items list to the request
        request.setAttribute("listFlight", listFlights);
        LOG.trace("Set the request attribute: listFlights --> " + listFlights);

        LOG.debug("Commands finished");
        return Path.LIST_AUTO_FLIGHTS;
    }

}
