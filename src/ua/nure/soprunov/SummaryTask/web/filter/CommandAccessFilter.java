package ua.nure.soprunov.SummaryTask.web.filter;

import java.io.IOException;
import java.util.*;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.soprunov.SummaryTask.Path;
import ua.nure.soprunov.SummaryTask.Util.Role;
import ua.nure.soprunov.SummaryTask.exception.Messages;


/**
 * Security filter. Filter which performs authorization of the user to access resources of the
 * application. Use web.xml to make a access permissions.
 *
 @authors Soprunov Igor
 */
public class CommandAccessFilter implements Filter {

    private static final Logger LOG = Logger.getLogger(CommandAccessFilter.class);

    // commands access
    private Map<Role, List<String>> accessMap = new HashMap<Role, List<String>>();
    private List<String> commons = new ArrayList<String>();
    private List<String> outOfControl = new ArrayList<String>();

    public void destroy() {
        LOG.debug("Filter destruction starts");
        // do nothing
        LOG.debug("Filter destruction finished");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LOG.debug("Filter starts");

        if (accessAllowed(request)) {
            chain.doFilter(request, response);
            LOG.debug("Filter finished");
        } else {
            String errorMessasge = "You do not have permission to access the requested resource";
            LOG.debug("Default locale :" + Locale.getDefault());
            if ((Locale.getDefault().toString()).equals("ru")|(Locale.getDefault().toString()).equals("ru_RU")) {
                errorMessasge = Messages.ERR_NO_PERMISSION_RU;
            }
            if ((Locale.getDefault().toString()).equals("en")|(Locale.getDefault().toString()).equals("en_EN")) {
                errorMessasge = "You do not have permission to access the requested resource";
            }
            request.setAttribute("errorMessage", errorMessasge);
            LOG.trace("Set the request attribute: errorMessage --> " + errorMessasge);

            request.getRequestDispatcher(Path.PAGE_ERROR_PAGE)
                    .forward(request, response);
        }
    }

    private boolean accessAllowed(ServletRequest request) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String commandName = request.getParameter("command");
        if (commandName == null || commandName.isEmpty()) {
            return false;
        }

        if (outOfControl.contains(commandName)) {
            return true;
        }

        HttpSession session = httpRequest.getSession(false);
        if (session == null) {
            return false;
        }

        Role userRole = (Role) session.getAttribute("userRole");
        if (userRole == null) {
            return false;
        }

        return accessMap.get(userRole).contains(commandName)
                || commons.contains(commandName);

    }

    public void init(FilterConfig fConfig) throws ServletException {
        LOG.debug("Filter initialization starts");

        // roles
        accessMap.put(Role.ADMIN, asList(fConfig.getInitParameter("admin")));
        accessMap.put(Role.DISPATCHER, asList(fConfig.getInitParameter("dispatcher")));
        accessMap.put(Role.DRIVER, asList(fConfig.getInitParameter("driver")));
        LOG.trace("Access map --> " + accessMap);


        // commons
        commons = asList(fConfig.getInitParameter("common"));
        LOG.trace("Common commands --> " + commons);

        // out of control
        outOfControl = asList(fConfig.getInitParameter("out-of-control"));
        LOG.trace("Out of control commands --> " + outOfControl);

        LOG.debug("Filter initialization finished");
    }

    /**
     * Extracts parameter values from string.
     *
     * @param str parameter values string.
     * @return list of parameter values.
     */
    private List<String> asList(String str) {
        List<String> list = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(str);
        while (st.hasMoreTokens()) {
            list.add(st.nextToken());
        }
        return list;
    }

}