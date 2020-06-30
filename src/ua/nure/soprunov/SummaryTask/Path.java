package ua.nure.soprunov.SummaryTask;

/**
 * Path holder (jsp pages, controller commands).
 * 
 * @author Soprunov Igor
 * 
 */

public final class Path {
	
	
	// pages
	public static final String PAGE_LOGIN = "/login.jsp";
	public static final String START_PAGE = "/start_page.jsp";
	public static final String PAGE_ERROR_PAGE = "/WEB-INF/jsp/error_page.jsp";
	public static final String LIST_AUTO_FLIGHTS = "/WEB-INF/jsp/dispatcher/list_auto_flights.jsp";
	public static final String LIST_OF_VECHICLES = "/WEB-INF/jsp/admin/list_vehicles.jsp";
	public static final String LIST_OF_USERS = "/WEB-INF/jsp/admin/list_users.jsp";
	public static final String DRIVER_LIST_AUTO_FLIGHTS = "/WEB-INF/jsp/driver/driver_list_auto_flights.jsp";
	public static final String FREE_LIST_AUTO_FLIGHTS = "/WEB-INF/jsp/driver/free_list_auto_flights.jsp";
	public static final String FIELD_LIST_APPLICATION = "/WEB-INF/jsp/driver/filed_applications.jsp";

	
	public static final String PAGE_APLICATION_FORM = "/WEB-INF/jsp/driver/aplication_form.jsp";
	public static final String PAGE_EDIT_FLIGHTS = "/WEB-INF/jsp/dispatcher/edict_flight.jsp";
	public static final String PAGE_FINISHED_FLIGHTS = "/WEB-INF/jsp/driver/finished_flight.jsp";
	public static final String PAGE_EDIT_VEHICLE = "/WEB-INF/jsp/admin/edit_vehicle.jsp";
	public static final String PAGE_EDIT_USER = "/WEB-INF/jsp/admin/edit_user.jsp";
	public static final String PAGE_CREATE_FLIGHT = "/WEB-INF/jsp/dispatcher/create_flight.jsp";
	public static final String PAGE_CREATE_VEHICLE = "/WEB-INF/jsp/admin/create_vehicle.jsp";
	public static final String PAGE_CREATE_USER = "/WEB-INF/jsp/admin/create_user.jsp";
	
	public static final String LIST_OF_ALLOCATION_REQUEST = "/WEB-INF/jsp/dispatcher/list_of_allocation_request.jsp";
	public static final String PAGE_CONSIDER_REQUEST = "/WEB-INF/jsp/dispatcher/consider_request.jsp";
	




// commands
	
	public static final String COMMAND_CREATE_VECHICAL = "?command=createVehicle";

	public static final String COMMAND_LIST_USERS = "?command=list_of_users";
	public static final String DISPATCHER_COMMAND_LIST_AUTO_FLIGHTS = "?command=list_Auto_Flights";
	public static final String DRIVER_COMMAND_LIST_AUTO_FLIGHTS = "?command=driver_list_auto_flights";
	public static final String COMMAND_FREE_LIST_AUTO_FLIGHTS = "?command=free_list_auto_flights";
	public static final String COMMAND_SHOW_LIST_VEHICLE = "?command=list_of_vehical";
	public static final String COMMAND_SHOW_START_PAGE = "?command=showStartPage";
	
}