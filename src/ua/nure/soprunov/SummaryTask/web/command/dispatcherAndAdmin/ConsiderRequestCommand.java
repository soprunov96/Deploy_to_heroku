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
import ua.nure.soprunov.SummaryTask.Util.Date;
import ua.nure.soprunov.SummaryTask.dao.datasource.DataSourceFactory;
import ua.nure.soprunov.SummaryTask.dao.datasource.DataSourceType;
import ua.nure.soprunov.SummaryTask.Util.Fields;
import ua.nure.soprunov.SummaryTask.dao.implementation.FlightDaoImpl;
import ua.nure.soprunov.SummaryTask.dao.implementation.RequestDaoImpl;
import ua.nure.soprunov.SummaryTask.dao.implementation.UserDaoImpl;
import ua.nure.soprunov.SummaryTask.dao.implementation.VehicleDaoImpl;
import ua.nure.soprunov.SummaryTask.dao.entity.Flight;
import ua.nure.soprunov.SummaryTask.dao.entity.Request;
import ua.nure.soprunov.SummaryTask.dao.entity.User;
import ua.nure.soprunov.SummaryTask.dao.entity.Vehicle;
import ua.nure.soprunov.SummaryTask.exception.AppException;
import ua.nure.soprunov.SummaryTask.exception.DBException;
import ua.nure.soprunov.SummaryTask.web.command.Command;

/**
 * Command that consider request. Command allowed  for admins and dispatchers.
 *
 *  @authors Soprunov Igor
 *
 */

public class ConsiderRequestCommand extends Command {

    private static final long serialVersionUID = 1062591396251474529L;

    private static final Logger LOG = Logger.getLogger(ConsiderRequestCommand.class);

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

        String id = request.getParameter(Fields.LIST_REQUEST_ID);

        Request considerRequest = new RequestDaoImpl(datasource).find(Long.parseLong(id));
        LOG.trace("Found in DB: Station --> " + considerRequest);


        request.setAttribute(Fields.LIST_REQUEST_ID, considerRequest.getId());
        LOG.trace("Set attribute 'id': " + considerRequest.getId());

        request.setAttribute(Fields.LIST_REQUEST_VEHICLE_RANGE, considerRequest.getRange());
        LOG.trace("Set attribute 'range': " + considerRequest.getRange());

        request.setAttribute(Fields.LIST_REQUEST_DRIVER_ID, considerRequest.getDriverId());
        LOG.trace("Set attribute 'driver_id': " + considerRequest.getDriverId());

        request.setAttribute(Fields.LIST_REQUEST_VEHICLE_TYPE, considerRequest.getCarType());
        LOG.trace("Set attribute 'carType': " + considerRequest.getCarType());

        request.setAttribute(Fields.LIST_REQUEST_VEHICLE_ID, considerRequest.getId());
        LOG.trace("Set attribute 'VECHICLE_ID': " + considerRequest.getId());

        User driver = new UserDaoImpl(DataSourceFactory
                .getDataSource(DataSourceType.MY_SQL_DATASOURCE)).find(considerRequest.getDriverId());
        LOG.trace("Found in DB: driver --> " + driver);

        request.setAttribute(Fields.LIST_FLIGHT_DRIVER_NAME, driver.getFirstName() + " " + driver.getLastName());
        LOG.trace("Set attribute 'driverName': " + driver.getFirstName() + " " + driver.getLastName());

        Flight considerFlight = new FlightDaoImpl(datasource).findFlightByRequestId(Long.parseLong(id));
        LOG.trace("Found in DB: Station --> " + considerFlight);

        request.setAttribute(Fields.LIST_FLIGHT_ID, considerFlight.getId());
        LOG.trace("Set attribute 'id': " + considerFlight.getId());

        request.setAttribute(Fields.LIST_FLIGHT_NAME, considerFlight.getName());
        LOG.trace("Set attribute 'name': " + considerFlight.getName());

        request.setAttribute(Fields.LIST_FLIGHT_DATE, considerFlight.getDate());
        LOG.trace("Set attribute 'date': " + considerFlight.getDate());

        request.setAttribute("checkVehicle", considerFlight.getVehicleId());
        LOG.trace("Set attribute 'checkVehicle': " + considerFlight.getVehicleId());

        request.setAttribute(Fields.LIST_FLIGHT_DEPART, considerFlight.getDepart());
        LOG.trace("Set attribute 'depart': " + considerFlight.getDepart());

        request.setAttribute(Fields.LIST_FLIGHT_ARRIVAL, considerFlight.getArrival());
        LOG.trace("Set attribute 'arrival': " + considerFlight.getArrival());


        request.setAttribute(Fields.LIST_FLIGHT_STATUS, considerFlight.getStatus());
        LOG.trace("Set attribute 'status': " + considerFlight.getStatus());

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

        int rows = Integer.parseInt(new FlightDaoImpl(datasource).getNumberOfRows(Fields.TABLE_VEHICLES)) - 1;
        int nOfPages = rows / recordsPerPageInt;

        if (nOfPages % recordsPerPageInt > 0) {
            nOfPages++;
        }
        request.setAttribute(Fields.NO_OF_PAGES, nOfPages);
        request.setAttribute(Fields.CURRENT_PAGE, currentPage);
        request.setAttribute(Fields.RECORDS_PER_PAGE, recordsPerPage);
        request.setAttribute(Fields.SORT_BY, sortBy);

        request.setAttribute("localDate", new Date().getLocalDate());
        LOG.trace("Set attribute 'localDate': " + new Date().getLocalDate());

        List<Vehicle> listVechicles = new VehicleDaoImpl(DataSourceFactory
                .getDataSource(DataSourceType.MY_SQL_DATASOURCE)).findVechicleListSortBy(currentPageInt, recordsPerPageInt, sortBy);
        LOG.trace("Found in DB: listVechicles --> " + listVechicles);

        // put menu items list to the request
        request.setAttribute("listVechicles", listVechicles);
        LOG.trace("Set the request attribute: listFlights --> " + listVechicles);


        LOG.debug("Commands finished");
        return Path.PAGE_CONSIDER_REQUEST;
    }

    /**
     * Add to flight info about car and driver.
     *
     * @return path to the view of list flights if all fields were properly
     * filled, otherwise redisplays list auto flights page.
     * @throws DBException
     */
    private String doPost(HttpServletRequest request, HttpServletResponse response) throws DBException {
        // get parameters from page
        LOG.debug("Commands starts");

        String id = request.getParameter(Fields.LIST_FLIGHT_ID);
        LOG.trace("Get request parameter: 'id' = " + id);

        String carId = request.getParameter(Fields.LIST_FLIGHT_VEHICLE_ID);
        LOG.trace("Get request parameter: 'carId' = " + carId);

        String driverId = request.getParameter(Fields.LIST_FLIGHT_DRIVER_ID);
        LOG.trace("Get request parameter: 'driverId' = " + driverId);


        String name =  request.getParameter(Fields.LIST_FLIGHT_NAME);
        LOG.trace("Get request parameter: 'name' = " + name);

        String date = request.getParameter(Fields.LIST_FLIGHT_DATE);
        LOG.trace("Get request parameter: 'date' = " + date);

        String depart = request.getParameter(Fields.LIST_FLIGHT_DEPART);
        LOG.trace("Get request parameter: 'depart' = " + depart );

        String arrival = request.getParameter(Fields.LIST_FLIGHT_ARRIVAL);
        LOG.trace("Get request parameter: 'arrival' = " + arrival);

        String status = request.getParameter(Fields.LIST_FLIGHT_STATUS);
        LOG.trace("Get request parameter: 'status' = " + status);

        Flight flight =  new FlightDaoImpl(datasource).find(Long.parseLong(id));
        flight.setName(name);
        flight.setDate(date);
        flight.setArrival(arrival);
        flight.setDepart(depart);
        flight.setStatus(status);

        new FlightDaoImpl(datasource).update(flight);
        LOG.trace("Update in DB: flight --> " + flight);


        new FlightDaoImpl(datasource).appointDriverAndCar(id, Long.parseLong(driverId), Long.parseLong(carId));

        List<Flight> listFlights = new FlightDaoImpl(datasource).findAll();
        LOG.trace("Found in DB: listFlights --> " + listFlights);

        request.setAttribute("listFlight", listFlights);
        LOG.trace("Set the request attribute: listFlights --> " + listFlights);

		LOG.debug("Commands finished");
        return Path.DISPATCHER_COMMAND_LIST_AUTO_FLIGHTS;
    }

}
