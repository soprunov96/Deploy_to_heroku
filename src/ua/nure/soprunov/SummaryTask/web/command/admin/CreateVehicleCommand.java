package ua.nure.soprunov.SummaryTask.web.command.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.soprunov.SummaryTask.Path;
import ua.nure.soprunov.SummaryTask.Util.ActionType;

import ua.nure.soprunov.SummaryTask.dao.datasource.DataSourceFactory;
import ua.nure.soprunov.SummaryTask.dao.datasource.DataSourceType;
import ua.nure.soprunov.SummaryTask.Util.Fields;
import ua.nure.soprunov.SummaryTask.dao.implementation.VehicleDaoImpl;

import ua.nure.soprunov.SummaryTask.dao.entity.Vehicle;
import ua.nure.soprunov.SummaryTask.exception.AppException;
import ua.nure.soprunov.SummaryTask.exception.DBException;
import ua.nure.soprunov.SummaryTask.web.command.Command;

/**
 * Command that create new vehicles. Command allowed only for admins.
 *
 *  @authors Soprunov Igor
 *
 */

public class CreateVehicleCommand extends Command {

    private static final Logger LOG = Logger.getLogger(CreateVehicleCommand.class);


    private static final long serialVersionUID = -8296370157684739215L;

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

        LOG.debug("return" + Path.PAGE_CREATE_VEHICLE);
        LOG.debug("Commands finished");
        return Path.PAGE_CREATE_VEHICLE;
    }

    /**
     * Create new vehicle.
     *
     * @return path to the view all vehicles.
     * @throws DBException
     */
    private String doPost(HttpServletRequest request, HttpServletResponse response) throws DBException {
        // get parameters from page
        LOG.debug("Commands starts");

        String model = request.getParameter(Fields.LIST_VEHICLE_MODEL);
        LOG.trace("Set attribute 'model': " + model);

        String range = request.getParameter(Fields.LIST_VEHICLE_RANGE);
        LOG.trace("Set attribute 'range': " + range);

        String type = request.getParameter(Fields.LIST_VEHICLE_TYPE);
        LOG.trace("Set attribute 'type': " + type);

        String status = request.getParameter(Fields.LIST_VEHICLE_STATUS);
        LOG.trace("Set attribute 'status': " + status);

        Vehicle vehicle = new Vehicle(model, Integer.parseInt(range), type, status);
        new VehicleDaoImpl(DataSourceFactory
                .getDataSource(DataSourceType.MY_SQL_DATASOURCE)).create(vehicle);
        LOG.trace("Create vehicle: " + vehicle );

        LOG.debug("Commands finished");

        LOG.debug("return " + Path.COMMAND_SHOW_LIST_VEHICLE);
        return Path.COMMAND_SHOW_LIST_VEHICLE;

    }

}
