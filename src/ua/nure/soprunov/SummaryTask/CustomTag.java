package ua.nure.soprunov.SummaryTask;

import org.apache.log4j.Logger;
import ua.nure.soprunov.SummaryTask.dao.datasource.DataSourceFactory;
import ua.nure.soprunov.SummaryTask.dao.datasource.DataSourceType;
import ua.nure.soprunov.SummaryTask.dao.implementation.UserDaoImpl;
import ua.nure.soprunov.SummaryTask.dao.entity.User;
import ua.nure.soprunov.SummaryTask.exception.DBException;


import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Custom tag class which needed to check if user login is unique
 *
 *
 *  @authors Soprunov Igor
 */

public class CustomTag extends SimpleTagSupport {

    private static final Logger LOG = Logger.getLogger(CustomTag.class);

    private String login;
    private User User;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public void doTag() throws IOException {
        LOG.debug("Command doTag starts");
        JspWriter out = getJspContext().getOut();
        LOG.info("Login to check --> " + login);

        try {
            User = new UserDaoImpl(DataSourceFactory
                    .getDataSource(DataSourceType.MY_SQL_DATASOURCE)).findUserByLogin(login);
            LOG.trace("Find user by login: user --> " + User);
            if (User == null) {
                out.print("User name is valid");
            } else {
                out.print("User already exists");
            }
        } catch (DBException e) {
            e.printStackTrace();
        }
        LOG.debug("Commands doTag finished");
    }
}
