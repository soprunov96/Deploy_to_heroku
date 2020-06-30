package ua.nure.soprunov.SummaryTask.exception;

/**
 * Holder for messages of exceptions.
 *
 *  @authors Soprunov Igor
 */
public class Messages {

    private Messages() {
        // no op
    }


    public static final String NULL_POINTER_EXEPTION = "Null pointer exception";

    public static final String ERR_CANNOT_OBTAIN_USER_ORDER_BEANS = "Cannot obtain user order beans";

    public static final String ERR_CANNOT_OBTAIN_CONNECTION = "Cannot obtain a connection from the pool";

    public static final String ERR_CANNOT_OBTAIN_USER_BY_ID = "Cannot obtain a user by its id";

    public static final String ERR_CANNOT_OBTAIN_USER_BY_LOGIN = "Cannot obtain a user by its login";

    public static final String ERR_CANNOT_UPDATE_USER = "Cannot update a user";

    public static final String ERR_CANNOT_CLOSE_CONNECTION = "Cannot close a connection";

    public static final String ERR_CANNOT_CLOSE_RESULTSET = "Cannot close a result set";

    public static final String ERR_CANNOT_CLOSE_STATEMENT = "Cannot close a statement";

    public static final String ERR_CANNOT_OBTAIN_DATA_SOURCE = "Cannot obtain the data source";

    // no op

    public static final String ERR_CANNOT_FIND_FLIGHT_BY_ID = "Cannot find flight by id";

    public static final String ERR_CANNOT_FIND_FLIGHT_LIST_BY_ORDER = "Cannot find flight list by order";

    public static final String ERR_CANNOT_FIND_FLIGHT_LIST = "Cannot find flight list ";

    public static final String ERR_CANNOT_UPDATE_FLIGHTS = "Cannot update flight ";

    public static final String ERR_CANNOT_CREATE_FLIGHT = "Cannot create flight ";

    public static final String ERR_CANNOT_INSERT_CAR_AND_DRIVER = "Cannot insert car and driver ";

    public static final String ERR_CANNOT_INTO_FLIGHTS_REQUEST_ID = "Cannot insert into flights request id ";

    public static final String ERR_CANNOT_UPDATE_FLIGHT_CHANGE_STATUS = "Cannot update flight change status ";

    public static final String ERR_CANNOT_FIND_FLIGHT_BY_REQUEST_ID = "Cannot find flight by request id  ";

    public static final String ERR_CANNOT_DELETE_FLIGHT_BY_ID = "Cannot delete flight by id ";

    public static final String ERR_CANNOT_FIND_DRIVERS_LIST = "Cannot find driver list ";

    public static final String ERR_CANNOT_FIND_LAST_COMMIT_ID = "Cannot find last commit id ";

    public static final String ERR_CANNOT_FIND_NUMBER_OF_ROWS = "Cannot find number of rows ";

    // no op

    public static final String ERR_CANNOT_CONSIDER_REQUEST = "Cannot consider request ";

    public static final String ERR_CANNOT_FIND_ALL_REQUEST_LIST = "Cannot find request list ";

    public static final String ERR_CANNOT_FIND_REQUEST_BY_ID = "Cannot consider request ";

    public static final String ERR_CANNOT_CREATE_REQUEST = "Cannot create request ";

    public static final String ERR_CANNOT_DELETE_REQUEST_BY_ID = "Cannot delete request ";


    // no op

    public static final String ERR_CANNOT_FIND_LIST_OF_USERS = "Cannot find list of users ";

    public static final String ERR_CANNOT_DELETE_USER_BY_ID = "Cannot delete user by id ";

    public static final String ERR_CANNOT_CREATE_USER = "Cannot create user";

    public static final String ERR_CANNOT_EDIT_USER = "Cannot edit user ";

    // no op

    public static final String ERR_CANNOT_FIND_ALL_VECHICLE_LIST = "Cannot find all vechicle list ";

    public static final String ERR_CANNOT_DELETE_VEHICAL_BY_ID = "Cannot delete vehical by id ";

    public static final String ERR_CANNOT_CREATE_VEHICLE = "Cannot create vehicle ";

    public static final String ERR_CANNOT_FIND_VEHICLE_BY_ID = "Cannot find vechicle by id ";

    public static final String ERR_CANNOT_UPDATE_VECHICLE = "Cannot update vehicle ";

    public static final String ERR_CANNOT_UPDATE_VECHICAL_CHANGE_STATUS = "Cannot update vehicle change status ";


    public static final String ERR_NO_PERMISSION_RU = "\u041a\u0020\u0441\u043e\u0436\u0430\u043b" +
            "\u0435\u043d\u0438\u044e\u002c\u0020\u0443\u0020\u0432\u0430\u0441\u0020" +
            "\u043d\u0435\u0442\u0020\u0440\u0430\u0437\u0440\u0435\u0448\u0435\u043d\u0438\u0435\u0020\u043d\u0430" +
            "\u0020\u0434\u043e\u0441\u0442\u0443\u043f\u0020\u043a\u0020\u0437\u0430\u043f\u0440\u043e\u0448\u0435" +
            "\u043d\u043d\u043e\u043c\u0443\u0020\u0440\u0435\u0441\u0443\u0440\u0441\u0443";

    public static final String EXCEPTION_NO_USER_WITH_SUCH_LOGIN_OR_PASSWORD_RU = "\u041d\u0435\u0432\u043e\u0437\u043c" +
            "\u043e\u0436\u043d\u043e\u0020\u043d\u0430\u0439\u0442\u0438\u0020\u043f\u043e\u043b\u044c\u0437\u043e" +
            "\u0432\u0430\u0442\u0435\u043b\u044f\u0020\u0441\u0020\u0443\u043a\u0430\u0437\u0430\u043d\u044b\u043c" +
            "\u0020\u043b\u043e\u0433\u0438\u043d\u043e\u043c\u0020\u002f\u0020\u043f\u0430\u0440\u043e\u043b\u0435" +
            "\u043c";
    public static final String EXCEPTION_LOGIN_AND_PASSWORD_CANNOT_BE_EMPTY = "\u041b\u043e\u0433\u0438\u043d\u002f" +
            "\u043f\u0430\u0440\u043e\u043b\u044c\u0020\u043d\u0435\u0020\u043c\u043e\u0433\u0443\u0442\u0020\u0431" +
            "\u044b\u0442\u044c\u0020\u043f\u0443\u0441\u0442\u044b\u043c\u0438";

}
