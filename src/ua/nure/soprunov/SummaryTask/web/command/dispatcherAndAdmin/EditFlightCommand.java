package ua.nure.soprunov.SummaryTask.web.command.dispatcherAndAdmin;

import java.io.IOException;

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
import ua.nure.soprunov.SummaryTask.dao.implementation.VehicleDaoImpl;
import ua.nure.soprunov.SummaryTask.dao.entity.Flight;
import ua.nure.soprunov.SummaryTask.dao.entity.Vehicle;
import ua.nure.soprunov.SummaryTask.exception.AppException;
import ua.nure.soprunov.SummaryTask.exception.DBException;
import ua.nure.soprunov.SummaryTask.web.command.Command;

/**
 * Command that edit flight. Command allowed only for admins and dispatcher.
 *
 *  @authors Soprunov Igor
 */

public class EditFlightCommand extends Command {
    private static final long serialVersionUID = 1863978254669586513L;

    private static final Logger LOG = Logger.getLogger(EditFlightCommand.class);

    private DataSource datasource = DataSourceFactory
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

        String id = request.getParameter(Fields.LIST_FLIGHT_ID);

        Flight flight = new FlightDaoImpl(datasource).find(Long.parseLong(id));

        LOG.trace("Found in DB: Station --> " + flight);

        request.setAttribute(Fields.LIST_FLIGHT_ID, flight.getId());
        LOG.trace("Set attribute 'id': " + flight.getId());

        request.setAttribute(Fields.LIST_FLIGHT_NAME, flight.getName());
        LOG.trace("Set attribute 'name': " + flight.getName());

        request.setAttribute(Fields.LIST_FLIGHT_DATE, flight.getDate());
        LOG.trace("Set attribute 'date': " + flight.getDate());

        request.setAttribute(Fields.LIST_FLIGHT_DEPART, flight.getDepart());
        LOG.trace("Set attribute 'depart': " + flight.getDepart());

        request.setAttribute(Fields.LIST_FLIGHT_ARRIVAL, flight.getArrival());
        LOG.trace("Set attribute 'arival': " + flight.getArrival());

        request.setAttribute(Fields.LIST_FLIGHT_DRIVER_NAME, flight.getDriverName());
        LOG.trace("Set attribute 'driverName': " + flight.getDriverName());

        request.setAttribute(Fields.LIST_FLIGHT_VEHICLE_MODEL, flight.getCarModel());
        LOG.trace("Set attribute 'model': " + flight.getCarModel());

        request.setAttribute(Fields.LIST_FLIGHT_VEHICLE_MODEL, flight.getCarModel());
        LOG.trace("Set attribute 'model': " + flight.getCarModel());

        request.setAttribute(Fields.LIST_FLIGHT_STATUS, flight.getStatus());
        LOG.trace("Set attribute 'status': " + flight.getStatus());

        request.setAttribute("localDate", new Date().getLocalDate());
        LOG.trace("Set attribute 'localDate': " + new Date().getLocalDate());

        if (flight.getVehicleId() != 0) {
            Vehicle car = new VehicleDaoImpl(datasource).find(flight.getVehicleId());

            request.setAttribute(Fields.LIST_VEHICLE_RANGE, car.getRange());
            LOG.trace("Set attribute 'range': " + car.getRange());
        }

        LOG.debug("Commands finished");
        return Path.PAGE_EDIT_FLIGHTS;
    }

    /**
     * Updates flight.
     *
     * @return path to the view of flights list if all fields were properly
     * filled.
     * @throws DBException
     */
    private String doPost(HttpServletRequest request, HttpServletResponse response) throws DBException {
        // get parameters from page
        LOG.debug("Commands starts");

        String id = request.getParameter(Fields.LIST_FLIGHT_ID);
        LOG.trace("Get request parameter: 'id' = " + id);

        String name = request.getParameter(Fields.LIST_FLIGHT_NAME);
        LOG.trace("Get request parameter: 'name' = " + name);

        String date = request.getParameter(Fields.LIST_FLIGHT_DATE);
        LOG.trace("Get request parameter: 'date' = " + date);

        String depart = request.getParameter(Fields.LIST_FLIGHT_DEPART);
        LOG.trace("Get request parameter: 'depart' = " + depart);

        String arrival = request.getParameter(Fields.LIST_FLIGHT_ARRIVAL);
        LOG.trace("Get request parameter: 'arrival' = " + arrival);

        String status = request.getParameter(Fields.LIST_FLIGHT_STATUS);
        LOG.trace("Get request parameter: 'status' = " + status);

        Flight flight = new FlightDaoImpl(datasource).find(Long.parseLong(id));
        flight.setName(name);
        flight.setDate(date);
        flight.setArrival(arrival);
        flight.setDepart(depart);
        flight.setStatus(status);

        new FlightDaoImpl(datasource).update(flight);
        LOG.trace("Update in DB: flight --> " + flight);

        LOG.debug("Commands finished");
        return Path.DISPATCHER_COMMAND_LIST_AUTO_FLIGHTS;
    }

}