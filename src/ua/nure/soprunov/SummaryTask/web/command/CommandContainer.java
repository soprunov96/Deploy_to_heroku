package ua.nure.soprunov.SummaryTask.web.command;

import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import ua.nure.soprunov.SummaryTask.web.command.admin.*;
import ua.nure.soprunov.SummaryTask.web.command.common.*;
import ua.nure.soprunov.SummaryTask.web.command.dispatcherAndAdmin.ConsiderRequestCommand;
import ua.nure.soprunov.SummaryTask.web.command.dispatcherAndAdmin.CreateFlightCommand;
import ua.nure.soprunov.SummaryTask.web.command.dispatcherAndAdmin.DeleteFlightCommand;
import ua.nure.soprunov.SummaryTask.web.command.dispatcherAndAdmin.EditFlightCommand;
import ua.nure.soprunov.SummaryTask.web.command.dispatcherAndAdmin.ListAutoFlightsCommand;
import ua.nure.soprunov.SummaryTask.web.command.driver.*;

/**
 * Holder for all commands.<br/>
 *
 *  @authors Soprunov Igor
 */
public class CommandContainer {

    private static final Logger LOG = Logger.getLogger(CommandContainer.class);

    private static Map<String, Command> commands = new TreeMap<String, Command>();

    static {
        // common commands
        commands.put("login", new LoginCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("noCommand", new NoCommand());
        commands.put("setLocale", new SetLocaleCommand());
        commands.put("showStartPage", new ShowStartPageCommand());
        commands.put("getPdf", new GetPdfCommand());
        commands.put("showLoginForm", new ShowLoginFormCommand());
        commands.put("changeAvatar", new ChangeAvatarCommand());

        // driver commands
        commands.put("free_list_auto_flights", new FreeListAutoFlightsCommand());
        commands.put("prepareApplication", new PrepareApplicationCommand());
        commands.put("finishedFlight", new FinishedFlightCommand());
        commands.put("driver_list_auto_flights", new DriverListAutoFlightCommand());
        commands.put("field_list_applications", new FieldListApplications());
        commands.put("cancelApplication", new CancelApplication());


        // admin commands
        commands.put("list_of_vehical", new ShowListVechicalCommand());
        commands.put("deleteVehicle", new DeleteVehicleCommand());
        commands.put("createVehicle", new CreateVehicleCommand());
        commands.put("editVehicle", new EditVehicleCommand());
        commands.put("list_of_users", new ListUsersCommand());
        commands.put("deleteUser", new DeleteUsersCommand());
        commands.put("createUser", new CreateUsersCommand());
        commands.put("editUser", new EditUsersCommand());


        // dispatcher commands
        commands.put("list_Auto_Flights", new ListAutoFlightsCommand());
        commands.put("editFlight", new EditFlightCommand());
        commands.put("deleteFlight", new DeleteFlightCommand());
        commands.put("createFlight", new CreateFlightCommand());
        commands.put("list_allocation_request", new ListOfAllocationRequestCommand());
        commands.put("considerRequest", new ConsiderRequestCommand());


        LOG.debug("Command container was successfully initialized");
        LOG.trace("Number of commands --> " + commands.size());
    }

    /**
     * Returns command object with the given name.
     *
     * @param commandName Name of the command.
     * @return Command object if container contains such command, otherwise
     * noCommand object will be returned.
     */
    public static Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            LOG.trace("Command not found, name --> " + commandName);
            return commands.get("noCommand");
        }

        return commands.get(commandName);
    }

}