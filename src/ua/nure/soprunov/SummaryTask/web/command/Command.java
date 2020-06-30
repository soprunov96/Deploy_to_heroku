package ua.nure.soprunov.SummaryTask.web.command;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.soprunov.SummaryTask.Util.ActionType;
import ua.nure.soprunov.SummaryTask.exception.AppException;


/**
 * Main interface for the Command pattern implementation.
 * 
 *  @authors Soprunov Igor
 * 
 */
public abstract class Command implements Serializable {
	private static final long serialVersionUID = 8879403039689311780L;

	/**
	 * Execution method for command. Returns path to go to based on the client
	 * request. If Command is specific to some user role, then subclasses in
	 * this method should perform validation and grant or not permissions to
	 * proceed.
	 *
	 * @param request
	 *            - client request
	 * @param response
	 *            - server response
	 * @param actionType
	 *            - client HTTP method
	 * @return Address to go once the command is executed.
	 * @throws IOException
	 * @throws ServletException
	 * @see ActionType
	 */

	public abstract String execute(HttpServletRequest request,
			HttpServletResponse response, ActionType actionType) throws IOException, ServletException,
			AppException;

	@Override
	public final String toString() {
		return getClass().getSimpleName();
	}
}