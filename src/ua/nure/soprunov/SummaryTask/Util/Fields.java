package ua.nure.soprunov.SummaryTask.Util;

/**
 * Holder for fields names of DB tables.
 * 
 @authors Soprunov Igor
 * 
 */
public final class Fields {


	public static final String LOCALE = "locale";
	public static final String PREVIOUS_COMMAND = "previousCommand";

	public static final String RECORDS_PER_PAGE = "recordsPerPage";
	public static final String CURRENT_PAGE = "currentPage";
	public static final String SORT_BY = "sortBy";
	public static final String NO_OF_PAGES = "noOfPages";

	// entities
	public static final String ENTITY_ID = "id";
	
	public static final String TABLE_FLIGHTS =  "flights";
	public static final String TABLE_FLIGHTS_FREE_FLIGHTS =  "flights WHERE flights.request_id IS NULL ";
	public static final String TABLE_VEHICLES =  "vehicles";

	public static final String USER_ID = "id";
	public static final String USER_LOGIN = "login";
	public static final String USER_PASSWORD = "password";
	public static final String USER_FIRST_NAME = "first_name";
	public static final String USER_LAST_NAME = "last_name";
	public static final String USER_ROLE_ID = "role_id";
	public static final String USER_AVATAR= "user_avatar";
	
	public static final String LIST_FLIGHT_ID = "id";
	public static final String LIST_FLIGHT_NAME = "name";
	public static final String LIST_FLIGHT_DATE = "date";
	public static final String LIST_FLIGHT_DEPART = "departure_point";
	public static final String LIST_FLIGHT_ARRIVAL = "arrival_point";
	public static final String LIST_FLIGHT_DRIVER_ID = "driver_id";
	public static final String LIST_FLIGHT_DRIVER_NAME = "driver_name";
	public static final String LIST_FLIGHT_VEHICLE_ID = "vehicle_id";
	public static final String LIST_FLIGHT_STATUS = "status";
	public static final String LIST_FLIGHT_VEHICLE_MODEL = "model";
	public static final String LIST_FLIGHT_REQUEST_ID = "request_id";
	
	
	
	public static final String LIST_REQUEST_ID = "request_id";
	public static final String LIST_REQUEST_VEHICLE_RANGE = "range";
	public static final String LIST_REQUEST_VEHICLE_MODEL = "model";
	public static final String LIST_REQUEST_VEHICLE_TYPE = "type";
	public static final String LIST_REQUEST_STATUS = "status";
	public static final String LIST_REQUEST_VEHICLE_ID = "vehicle_id";
	public static final String LIST_REQUEST_DRIVER_ID = "driver_id";
	
	public static final String LIST_VEHICLE_ID = "id";
	public static final String LIST_VEHICLE_MODEL = "model";
	public static final String LIST_VEHICLE_RANGE = "range";
	public static final String LIST_VEHICLE_TYPE = "type";
	public static final String LIST_VEHICLE_STATUS = "status";


    public static final String GENERATED_KEY = "GENERATED_KEY";
}