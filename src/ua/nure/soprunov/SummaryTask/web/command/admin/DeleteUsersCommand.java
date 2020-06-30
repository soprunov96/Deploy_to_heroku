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
 * Command that delete users. Command allowed only for admins.
 *
 *  @authors Soprunov Igor
 *
 */

public class DeleteUsersCommand extends Command {

	private static final Logger LOG = Logger.getLogger(DeleteUsersCommand.class);

	private static final long serialVersionUID = -2089577843600361515L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, ActionType actionType)
			throws IOException, ServletException, AppException {
		LOG.debug("Commands starts");

		String id = request.getParameter("id");
		LOG.trace("Get request parameter: 'id' = " + id);

		User user = new  UserDaoImpl(DataSourceFactory
				.getDataSource(DataSourceType.MY_SQL_DATASOURCE)).find(Long.parseLong(id));

		new UserDaoImpl(DataSourceFactory
				.getDataSource(DataSourceType.MY_SQL_DATASOURCE)).delete(user);

		List<User> listUsers = new UserDaoImpl(DataSourceFactory
				.getDataSource(DataSourceType.MY_SQL_DATASOURCE)).findAll();
		LOG.trace("Found in DB: listUsers --> " + listUsers);

		// put menu items list to the request
		request.setAttribute("listUsers", listUsers);
		LOG.trace("Set the request attribute: listUsers --> " + listUsers);

		return Path.LIST_OF_USERS;
	}

}
