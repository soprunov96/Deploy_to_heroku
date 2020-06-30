package ua.nure.soprunov.SummaryTask.web.command.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.soprunov.SummaryTask.Path;
import ua.nure.soprunov.SummaryTask.Util.ActionType;
import ua.nure.soprunov.SummaryTask.dao.datasource.DataSourceFactory;
import ua.nure.soprunov.SummaryTask.dao.datasource.DataSourceType;
import ua.nure.soprunov.SummaryTask.dao.implementation.UserDaoImpl;
import ua.nure.soprunov.SummaryTask.dao.entity.User;
import ua.nure.soprunov.SummaryTask.exception.AppException;
import ua.nure.soprunov.SummaryTask.web.command.Command;

/**
 * Command that show all list of users. Command allowed only for admins.
 *
 *  @authors Soprunov Igor
 *
 */

public class ListUsersCommand extends Command {

	private static final Logger LOG = Logger.getLogger(ListUsersCommand.class);

	private static final long serialVersionUID = 2178696499792941799L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, ActionType actionType)
			throws IOException, ServletException, AppException {

		List<User> listUsers = new UserDaoImpl(DataSourceFactory
				.getDataSource(DataSourceType.MY_SQL_DATASOURCE)).findAll();
		LOG.trace("Found in DB: listUsers --> " + listUsers);

		// put user list to the request
		request.setAttribute("listUsers", listUsers);
		LOG.trace("Set the request attribute: listUsers --> " + listUsers);

		LOG.debug("Command finished");
		return Path.LIST_OF_USERS;
	}

}
