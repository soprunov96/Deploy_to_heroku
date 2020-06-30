package ua.nure.soprunov.SummaryTask.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.soprunov.SummaryTask.Path;
import ua.nure.soprunov.SummaryTask.Util.ActionType;
import ua.nure.soprunov.SummaryTask.exception.AppException;
import ua.nure.soprunov.SummaryTask.web.command.Command;
import ua.nure.soprunov.SummaryTask.web.command.CommandContainer;

/**
 * Main servlet controller.This servlet handles all
 * requests by the client and then processes them according to specified command
 * name.
 * 
 *  @authors Soprunov Igor
 * 
 */
public class Controller extends HttpServlet {

	private static final long serialVersionUID = 2423353715955164816L;

	private static final Logger LOG = Logger.getLogger(Controller.class);


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response, ActionType.GET);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response, ActionType.POST);
	}

	/**
	 * Handles all requests coming from the client by executing the specified
	 * command name in a request. Implements PRG pattern by checking action type
	 * specified by the invoked method.
	 *
	 * @param request
	 * @param response
	 * @param actionType
	 * @throws IOException
	 * @throws ServletException
	 * @see ActionType
	 */
	private void process(HttpServletRequest request, HttpServletResponse response, ActionType actionType)
			throws IOException, ServletException {

		LOG.debug("Controller starts");

		// extract command name from the request
		String commandName = request.getParameter("command");
		LOG.trace("Request parameter: command --> " + commandName);
		request.setAttribute("commandLink", commandName);
		// obtain command object by its name
		Command command = CommandContainer.get(commandName);
		LOG.trace("Obtained command --> " + command);

		String path = null;
		// execute command and get forward address
		try {
			path = command.execute(request, response, actionType);

			if (path == null) {
				LOG.trace("Redirect to address = " + path);
				LOG.debug("Controller proccessing finished");
				response.sendRedirect(Path.PAGE_LOGIN);
			} else {
				if (actionType == ActionType.GET) {
					LOG.trace("Forward to address = " + path);
					LOG.debug("Controller proccessing finished");
					request.getRequestDispatcher(path).forward(request, response);
				} else if (actionType == ActionType.POST) {
					LOG.trace("Redirect to address = " + request.getRequestURI() + path);
					LOG.debug("Controller proccessing finished");
					response.sendRedirect(request.getRequestURI() + path);
				}
			}
		} catch (AppException ex) {
			System.out.println(ex.getMessage());
			request.setAttribute("errorMessage", ex.getMessage());
			request.getRequestDispatcher(Path.PAGE_ERROR_PAGE)
					.forward(request, response);
		}
	}
}