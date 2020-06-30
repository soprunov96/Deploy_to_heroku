package ua.nure.soprunov.SummaryTask.web.command.driver;

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
import ua.nure.soprunov.SummaryTask.dao.implementation.FlightDaoImpl;
import ua.nure.soprunov.SummaryTask.dao.implementation.VehicleDaoImpl;
import ua.nure.soprunov.SummaryTask.dao.entity.Flight;
import ua.nure.soprunov.SummaryTask.dao.entity.Vehicle;
import ua.nure.soprunov.SummaryTask.exception.AppException;
import ua.nure.soprunov.SummaryTask.exception.DBException;
import ua.nure.soprunov.SummaryTask.web.command.Command;


/**
 * Command that finished  flight.
 * Update info about vehicle and flight.
 * Command allowed only for drivers.
 *
 *  @authors Soprunov Igor
 */

public class FinishedFlightCommand extends Command {

    private static final long serialVersionUID = -5724955958031816159L;

    private static final Logger LOG = Logger.getLogger(FinishedFlightCommand.class);

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

        String id =  request.getParameter(Fields.ENTITY_ID);

        Flight flight = new  FlightDaoImpl(datasource).find(Long.parseLong(id));

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
        LOG.trace("Set attribute 'driver name': " + flight.getDriverName());

        request.setAttribute(Fields.LIST_FLIGHT_VEHICLE_MODEL, flight.getCarModel());
        LOG.trace("Set attribute 'vechicle model': " + flight.getCarModel());

        request.setAttribute(Fields.LIST_FLIGHT_STATUS, flight.getStatus());
        LOG.trace("Set attribute 'status': " + flight.getStatus());

        request.setAttribute(Fields.LIST_FLIGHT_VEHICLE_ID, flight.getVehicleId());
        LOG.trace("Set attribute 'vechicle id': " + flight.getVehicleId());

        request.setAttribute(Fields.LIST_FLIGHT_DRIVER_ID, flight.getDriverId());
        LOG.trace("Set attribute 'driver id': " + flight.getDriverId());

        Vehicle vehicle = new VehicleDaoImpl(DataSourceFactory
                .getDataSource(DataSourceType.MY_SQL_DATASOURCE)).find(flight.getVehicleId());

        request.setAttribute(Fields.LIST_VEHICLE_RANGE, vehicle.getRange());
        LOG.trace("Set attribute 'range': " + vehicle.getRange());

        request.setAttribute(Fields.LIST_VEHICLE_TYPE, vehicle.getType());
        LOG.trace("Set attribute 'type': " + vehicle.getType());

        LOG.debug("Commands finished");
        return Path.PAGE_FINISHED_FLIGHTS;
    }

    /**
     * Updates flight and vehicle info.
     *
     * @return path to the view of drivers list of flights if all fields were properly
     * filled.
     * @throws DBException
     */
    private String doPost(HttpServletRequest request, HttpServletResponse response) throws DBException {
        LOG.debug("Commands starts");

        String id = request.getParameter(Fields.LIST_FLIGHT_ID);
        LOG.trace("Get request parameter: 'flight id' = " + id);

        String status = request.getParameter(Fields.LIST_FLIGHT_STATUS);
        LOG.trace("Get request parameter: 'status' = " + status);

        Flight flight = new FlightDaoImpl(datasource).find(Long.parseLong(id));

        flight.setStatus(status);
        new FlightDaoImpl(datasource).update(flight);

        String carId = request.getParameter(Fields.LIST_FLIGHT_VEHICLE_ID);
        LOG.trace("Get request parameter: 'carId' = " + carId);

        String carStatus = request.getParameter("carStatus");
        LOG.trace("Get request parameter: 'carStatus' = " + carStatus);

        Vehicle vehicle = new VehicleDaoImpl(DataSourceFactory
                .getDataSource(DataSourceType.MY_SQL_DATASOURCE)).find(Long.parseLong(carId));

        vehicle.setStatus(carStatus);
        new VehicleDaoImpl(DataSourceFactory
                .getDataSource(DataSourceType.MY_SQL_DATASOURCE)).update(vehicle);

        // get flights list
        List<Flight> listFlights = new FlightDaoImpl(datasource).findAll();
        LOG.trace("Found in DB: listFlights --> " + listFlights);

        request.setAttribute("listFlight", listFlights);
        LOG.trace("Set the request attribute: listFlights --> " + listFlights);

        return Path.DRIVER_COMMAND_LIST_AUTO_FLIGHTS;
    }

}
