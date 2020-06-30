package ua.nure.soprunov.SummaryTask.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.soprunov.SummaryTask.Path;
import ua.nure.soprunov.SummaryTask.Util.ActionType;

/**
 * No command.
 * 
 *  @authors Soprunov Igor
 * 
 */
public class NoCommand extends Command {

	private static final long serialVersionUID = -2785976619686659267L;

	private static final Logger LOG = Logger.getLogger(NoCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, ActionType actionType) {
		LOG.debug("Command starts");
		
		String errorMessage = "No such command";
		request.setAttribute("errorMessage", errorMessage);
		LOG.error("Set the request attribute: errorMessage --> " + errorMessage);

		LOG.debug("Command finished");
		return Path.PAGE_ERROR_PAGE;
	}

}