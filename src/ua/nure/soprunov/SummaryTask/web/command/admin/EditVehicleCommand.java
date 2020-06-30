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
 * Command that edit vehicle. Command allowed only for admins.
 *
 *  @authors Soprunov Igor
 *
 */

public class EditVehicleCommand extends Command {

    private static final long serialVersionUID = 315427077438995378L;

    private static final Logger LOG = Logger.getLogger(EditVehicleCommand.class);



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

        String id = request.getParameter(Fields.ENTITY_ID);
        Vehicle vehicle = new VehicleDaoImpl(DataSourceFactory
                .getDataSource(DataSourceType.MY_SQL_DATASOURCE)).find(Long.parseLong(id));

        LOG.trace("Found in DB: Station --> " + vehicle);

        request.setAttribute(Fields.LIST_VEHICLE_ID, vehicle.getId());
        LOG.trace("Set attribute 'id': " + vehicle.getId());

        request.setAttribute(Fields.LIST_VEHICLE_MODEL, vehicle.getModel());
        LOG.trace("Set attribute 'model': " + vehicle.getModel());

        request.setAttribute(Fields.LIST_VEHICLE_RANGE, vehicle.getRange());
        LOG.trace("Set attribute 'range': " + vehicle.getRange());

        request.setAttribute(Fields.LIST_VEHICLE_TYPE, vehicle.getType());
        LOG.trace("Set attribute 'type': " + vehicle.getType());

        request.setAttribute(Fields.LIST_VEHICLE_STATUS, vehicle.getStatus());
        LOG.trace("Set attribute 'status': " + vehicle.getStatus());

        LOG.debug("Commands finished");
        return Path.PAGE_EDIT_VEHICLE;
    }

    /**
     * Updates vehicle info.
     *
     * @return path to the view of vehicle list if all fields were properly
     * filled, otherwise redisplay edit page.
     * @throws DBException
     */
    private String doPost(HttpServletRequest request, HttpServletResponse response) throws DBException {
        // get parameters from page
        LOG.debug("Commands starts");

        String id = request.getParameter(Fields.LIST_VEHICLE_ID);
        LOG.trace("Get attribute: 'id' = " + id);

        String model = request.getParameter(Fields.LIST_VEHICLE_MODEL);
        LOG.trace("Get attribute 'model': " + model);

        String range = request.getParameter(Fields.LIST_VEHICLE_RANGE);
        LOG.trace("Get attribute 'range': " + range);

        String type = request.getParameter(Fields.LIST_VEHICLE_TYPE);
        LOG.trace("Get attribute 'type': " + type);

        String status = request.getParameter(Fields.LIST_VEHICLE_STATUS);
        LOG.trace("Get attribute 'status': " + status);

        Vehicle vehicle = new VehicleDaoImpl(DataSourceFactory
                .getDataSource(DataSourceType.MY_SQL_DATASOURCE)).find(Long.parseLong(id));
        vehicle.setModel(model);
        vehicle.setRange(Integer.parseInt(range));
        vehicle.setType(type);
        vehicle.setStatus(status);

        LOG.trace("Update  vehicle --> " + vehicle);
        new VehicleDaoImpl(DataSourceFactory
                .getDataSource(DataSourceType.MY_SQL_DATASOURCE)).update(vehicle);

        LOG.debug("Commands finished");
        return Path.COMMAND_SHOW_LIST_VEHICLE;
    }

}
