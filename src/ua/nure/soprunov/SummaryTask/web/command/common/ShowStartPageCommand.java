package ua.nure.soprunov.SummaryTask.web.command.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.log4j.Logger;

import ua.nure.soprunov.SummaryTask.Path;
import ua.nure.soprunov.SummaryTask.Util.ActionType;
import ua.nure.soprunov.SummaryTask.exception.AppException;
import ua.nure.soprunov.SummaryTask.web.command.Command;

/**
 * Show start page before user login. Command allowed  for all users.
 *
 *  @authors Soprunov Igor
 */

public class ShowStartPageCommand extends Command {

	private static final long serialVersionUID = -5105297255991909258L;

	private static final Logger LOG = Logger.getLogger(ShowStartPageCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, ActionType actionType)
			throws IOException, ServletException, AppException {
		LOG.debug("Command execution starts");

		LOG.debug("Command execution finished");
		return Path.START_PAGE;
	}
}
