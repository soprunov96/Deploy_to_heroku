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
import ua.nure.soprunov.SummaryTask.dao.implementation.RequestDaoImpl;
import ua.nure.soprunov.SummaryTask.dao.entity.Flight;
import ua.nure.soprunov.SummaryTask.dao.entity.Request;
import ua.nure.soprunov.SummaryTask.exception.AppException;
import ua.nure.soprunov.SummaryTask.exception.DBException;
import ua.nure.soprunov.SummaryTask.web.command.Command;

/**
 * Command that application to flight. Command allowed only for drivers.
 *
 *  @authors Soprunov Igor
 */

public class PrepareApplicationCommand extends Command {

	private static final Logger LOG = Logger.getLogger(PrepareApplicationCommand.class);

	private static final long serialVersionUID = -6855511380807152963L;

	DataSource datasource = DataSourceFactory
			.getDataSource(DataSourceType.MY_SQL_DATASOURCE);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, ActionType actionType)
			throws IOException, ServletException, AppException {
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

		String id =  request.getParameter("id");
		LOG.trace("Get attribute flight 'id': " + id);

		Flight considerFlight = new FlightDaoImpl(datasource).find(Long.parseLong(id));

		request.setAttribute(Fields.LIST_FLIGHT_ID, considerFlight.getId());
		LOG.trace("Set attribute flight 'id': " + considerFlight.getId());

		request.setAttribute(Fields.LIST_FLIGHT_NAME, considerFlight.getName());
		LOG.trace("Set attribute 'name': " + considerFlight.getName());

		request.setAttribute(Fields.LIST_FLIGHT_DATE, considerFlight.getDate());
		LOG.trace("Set attribute 'date': " + considerFlight.getDate());

		request.setAttribute(Fields.LIST_FLIGHT_DEPART, considerFlight.getDepart());
		LOG.trace("Set attribute 'depart': " + considerFlight.getDepart());

		request.setAttribute(Fields.LIST_FLIGHT_ARRIVAL, considerFlight.getArrival());
		LOG.trace("Set attribute 'arrival': " + considerFlight.getArrival());

		request.setAttribute(Fields.LIST_FLIGHT_STATUS, considerFlight.getStatus());
		LOG.trace("Set attribute 'status': " + considerFlight.getStatus());

		LOG.debug("return" + Path.PAGE_APLICATION_FORM);
		LOG.debug("Commands finished");
		return Path.PAGE_APLICATION_FORM;
	}

	/**
	 * Updates flight and request info.
	 *
	 * @return path to the view of free list of flights if all fields were properly
	 *         filled.
	 * @throws DBException
	 */
	private String doPost(HttpServletRequest request, HttpServletResponse response) throws DBException {
		// get parameters from page
		LOG.debug("Commands starts");

		String driverId = request.getParameter("user_id");
		LOG.trace("Get request parameter: 'driverId' = " + driverId);

		String id = request.getParameter(Fields.LIST_FLIGHT_ID);
		LOG.trace("Get request parameter: 'flight id' = " + id);

		Integer range = Integer.parseInt(request.getParameter("range"));
		LOG.trace("Get request parameter: 'range' = " + range);

		String type =  request.getParameter("type");
		LOG.trace("Get request parameter: 'type' = " + type );

		Request newRequest = new Request(range,type,Long.parseLong(driverId));

		new RequestDaoImpl(datasource).create(newRequest);
		Flight flight = new FlightDaoImpl(datasource).find(Long.parseLong(id));

		flight.setRequestId(newRequest.getId());
		 new FlightDaoImpl(datasource).insertIdRequestIntoFlights(Long.parseLong(id), newRequest.getId());

		List<Flight> listFlights = new FlightDaoImpl(datasource).findAll();
		LOG.trace("Found in DB: listFlights --> " + listFlights);

		request.setAttribute("listFlight", listFlights);
		LOG.trace("Set the request attribute: listFlights --> " + listFlights);

		return Path.COMMAND_FREE_LIST_AUTO_FLIGHTS;
	}
}
