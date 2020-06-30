package ua.nure.soprunov.SummaryTask.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.soprunov.SummaryTask.dao.DatabaseAbstractRepository;
import ua.nure.soprunov.SummaryTask.dao.FlightDao;
import ua.nure.soprunov.SummaryTask.Util.Fields;
import ua.nure.soprunov.SummaryTask.dao.entity.Flight;
import ua.nure.soprunov.SummaryTask.exception.DBException;
import ua.nure.soprunov.SummaryTask.exception.Messages;

import javax.sql.DataSource;

/**
 * Flight DAO implementation. Performs basic read/write operations on Flight entity.
 *
 *  @authors Soprunov Igor
 *
 */

public class FlightDaoImpl extends DatabaseAbstractRepository<Flight> implements FlightDao {

    private static final Logger LOG = Logger.getLogger(FlightDaoImpl.class);

    // //////////////////////////////////////////////////////////
    // SQL queries
    // //////////////////////////////////////////////////////////

    private static final String SQL_FIND_ALL_FLIGHTS_LIST = "  SELECT mydb.flights.id, mydb.flights.name, mydb.flights.date,mydb.flights.departure_point,mydb.flights.arrival_point, "
            + " mydb.users.first_name,mydb.vehicles.model,mydb.flights.status,mydb.flights.request_id,mydb.flights.driver_id,mydb.flights.vehicle_id "
            + " FROM ((mydb.flights " + "LEFT JOIN mydb.users ON mydb.flights.driver_id=mydb.users.id  "
            + "LEFT JOIN mydb.vehicles ON mydb.flights.vehicle_id=vehicles.id))";

    private static final String SQL_FIND_FLIGHT_BY_ID = SQL_FIND_ALL_FLIGHTS_LIST + " WHERE mydb.flights.id=?";

    private static final String SQL_UPDATE_FLIGHT = "UPDATE mydb.flights SET mydb.flights.name=?, mydb.flights.date=?, mydb.flights.departure_point=?,"
            + " mydb.flights.arrival_point=?, mydb.flights.status=?" + "	WHERE mydb.flights.id=?";

    private static final String SQL_CREATE_FLIGHT = "INSERT INTO mydb.flights (id, name, date,departure_point,arrival_point,status)"
            + " VALUES (default,?,?,?,?,'open')";

    private static final String SQL_FLIGHTS_INSERT_CAR_AND_DRIVER = " UPDATE mydb.flights SET  mydb.flights.driver_id =?, mydb.flights.vehicle_id =? "
            + "	WHERE mydb.flights.id=?";

    private static final String SQL_INSERT_INTO_FLIGHTS_REQUEST_ID = "UPDATE mydb.flights SET  mydb.flights.request_id=? "
            + " WHERE mydb.flights.id=?";

    private static final String SQL_UPDATE_FLIGHT_CHANGE_STATUS = "UPDATE flights SET  status=? " + " WHERE id=?";

    private static final String SQL_FIND_FLIGHT_BY_REQUEST_ID = SQL_FIND_ALL_FLIGHTS_LIST
            + " WHERE mydb.flights.request_id=?";

	private static final String SQL_DELETE_FLIGHT_BY_ID = "DELETE FROM mydb.flights WHERE mydb.flights.id=?";




    /**
     * Initializes DataSource object.
     *
     * @param dataSource
     */
    public FlightDaoImpl(DataSource dataSource) {
        super(dataSource);
    }


    // //////////////////////////////////////////////////////////
    // Entity access methods
    // //////////////////////////////////////////////////////////

    /**
     * Returns limited sorted flight list .
     *
     * @param recordsPerPage
     * @param start
	 * @param orderBy
     * @return List of flights.
     */
    public  List<Flight> findFlightsListSortBy(int start, int recordsPerPage, String orderBy) throws DBException {
        List<Flight> flightsList = new ArrayList<>();
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        try {
            con = getConnection();
            assert (con != null);
            pstm = con.prepareStatement(SQL_FIND_ALL_FLIGHTS_LIST + "order by " + orderBy + " limit ? "+" OFFSET ?");
            pstm.setInt(1, start * recordsPerPage);
            pstm.setInt(2, recordsPerPage);
            rs = pstm.executeQuery();
            while (rs.next()) {
                flightsList.add(extractFlight(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            LOG.error(Messages.ERR_CANNOT_FIND_FLIGHT_LIST_BY_ORDER, ex);
            throw new DBException(Messages.ERR_CANNOT_FIND_FLIGHT_LIST_BY_ORDER, ex);
        } finally {
            close(con, pstm, rs);
        }
        return flightsList;
    }


    /**
     * Returns limited sorted flight list  where request_id IS NULL
     *
     * @param recordsPerPage
     * @param start
     * @param orderBy
     * @return List of flights.
     */
    public  List<Flight> findFlightsListWhereSortBy(int start, int recordsPerPage, String orderBy) throws DBException {
        List<Flight> flightsList = new ArrayList<>();
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        try {
            con = getConnection();
            assert (con != null);
            pstm = con.prepareStatement(SQL_FIND_ALL_FLIGHTS_LIST + " WHERE flights.request_id IS NULL order by " + orderBy + " limit ?,? ");
            pstm.setInt(1, start * recordsPerPage);
            pstm.setInt(2, recordsPerPage);
            rs = pstm.executeQuery();
            while (rs.next()) {
                flightsList.add(extractFlight(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            LOG.error(Messages.ERR_CANNOT_FIND_FLIGHT_LIST_BY_ORDER, ex);
            throw new DBException(Messages.ERR_CANNOT_FIND_FLIGHT_LIST_BY_ORDER, ex);
        } finally {
            close(con, pstm, rs);
        }
        return flightsList;
    }





    /**
     * Extracts a flights list from the result set.
     *
     * @param rs Result set from which a flights list will be extracted.
     * @return Menu item entity.
     */
    private static Flight extractFlight(ResultSet rs) throws SQLException {
        Flight flight = new Flight();
        flight.setId(rs.getLong(Fields.ENTITY_ID));
        flight.setName(rs.getString(Fields.LIST_FLIGHT_NAME));
        flight.setDate(rs.getString(Fields.LIST_FLIGHT_DATE));
        flight.setArrival(rs.getString(Fields.LIST_FLIGHT_ARRIVAL));
        flight.setDepart(rs.getString(Fields.LIST_FLIGHT_DEPART));
        flight.setDriverName(rs.getString(Fields.USER_FIRST_NAME));
        flight.setCarModel(rs.getString(Fields.LIST_FLIGHT_VEHICLE_MODEL));
        flight.setRequestId(rs.getLong(Fields.LIST_FLIGHT_REQUEST_ID));
        flight.setDriverId(rs.getLong(Fields.LIST_FLIGHT_DRIVER_ID));
        flight.setVehicleId(rs.getLong(Fields.LIST_FLIGHT_VEHICLE_ID));
        flight.setStatus(rs.getString(Fields.LIST_FLIGHT_STATUS));
        return flight;
    }



    /**
     * appoint driver and car
     *
     * @param id
     * @param driverId
     * @param carId
     */

    public  void appointDriverAndCar(String id, long driverId, long carId) throws DBException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_FLIGHTS_INSERT_CAR_AND_DRIVER);
            pstmt.setLong(1, driverId);
            pstmt.setLong(2, carId);
            pstmt.setString(3, id);
            pstmt.executeUpdate();
            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            LOG.error(Messages.ERR_CANNOT_INSERT_CAR_AND_DRIVER, ex);
            throw new DBException(Messages.ERR_CANNOT_INSERT_CAR_AND_DRIVER, ex);
        } finally {
           close(con, pstmt, rs);
        }

    }

    /**
     * Insert request into flights
     *
     * @param flightId
     * @param requestId
     */


    public  void insertIdRequestIntoFlights(long flightId, long requestId) throws DBException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_INSERT_INTO_FLIGHTS_REQUEST_ID);
            pstmt.setLong(1, requestId);
            pstmt.setLong(2, flightId);
            pstmt.executeUpdate();
            con.commit();
        } catch (SQLException ex) {
            LOG.error(Messages.ERR_CANNOT_INTO_FLIGHTS_REQUEST_ID, ex);
            throw new DBException(Messages.ERR_CANNOT_INTO_FLIGHTS_REQUEST_ID, ex);
        } finally {
           close(con, pstmt, rs);
        }
    }


    /**
     * Change flight status
     *
     * @param id
     * @param status
     */

//    public static void changeStatusFlights(String id, String status) throws DBException {
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        Connection con = null;
//        try {
//            con = DBManager.getInstance().getConnection();
//            pstmt = con.prepareStatement(SQL_UPDATE_FLIGHT_CHANGE_STATUS);
//            pstmt.setString(1, status);
//            pstmt.setString(2, id);
//            pstmt.executeUpdate();
//            con.commit();
//        } catch (SQLException ex) {
//            DBManager.getInstance().rollback(con);
//            LOG.error(Messages.ERR_CANNOT_UPDATE_FLIGHT_CHANGE_STATUS, ex);
//            throw new DBException(Messages.ERR_CANNOT_UPDATE_FLIGHT_CHANGE_STATUS, ex);
//        } finally {
//            DBManager.getInstance().close(con, pstmt, rs);
//        }
//
//    }

    /**
     * Find flight by request id
     *
     * @param id
     * @return flight
     */

    public  Flight findFlightByRequestId(long id) throws DBException {
        Flight flight = new Flight();
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        try {

            con = getConnection();
            assert (con != null);
            con.setAutoCommit(false);
            pstm = con.prepareStatement(SQL_FIND_FLIGHT_BY_REQUEST_ID);
            pstm.setLong(1, id);
            rs = pstm.executeQuery();
            while (rs.next()) {
                flight = extractFlight(rs);

            }
            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            LOG.error(Messages.ERR_CANNOT_FIND_FLIGHT_BY_REQUEST_ID, ex);
            throw new DBException(Messages.ERR_CANNOT_FIND_FLIGHT_BY_REQUEST_ID, ex);
        } finally {
            close(con, pstm, rs);
        }

        return flight;
    }

    @Override
    public void create(Flight flight) throws DBException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_CREATE_FLIGHT,PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, flight.getName());
            pstmt.setString(2, flight.getDate());
            pstmt.setString(3, flight.getDepart());
            pstmt.setString(4, flight.getArrival());
            pstmt.executeUpdate();
            con.commit();
			rs = pstmt.getGeneratedKeys();

			if (rs.next()) {
				flight.setId(rs.getLong(Fields.GENERATED_KEY));
			}
        } catch (SQLException ex) {
            rollback(con);
            LOG.error(Messages.ERR_CANNOT_CREATE_FLIGHT, ex);
            throw new DBException(Messages.ERR_CANNOT_CREATE_FLIGHT, ex);
        } finally {
            close(con, pstmt, rs);
        }

    }

    @Override
    public void update(Flight flight) throws DBException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_UPDATE_FLIGHT);
			pstmt.setString(1, flight.getName());
			pstmt.setString(2, flight.getDate());
			pstmt.setString(3, flight.getDepart());
			pstmt.setString(4, flight.getArrival());
			pstmt.setString(5, flight.getStatus());
			pstmt.setLong(6, flight.getId());
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_UPDATE_FLIGHTS, ex);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_FLIGHTS, ex);
		} finally {
			close(con, pstmt, rs);
		}

    }

    @Override
    public void delete(Flight flight) throws DBException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_DELETE_FLIGHT_BY_ID);
			pstmt.setLong(1, flight.getId());
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_DELETE_FLIGHT_BY_ID, ex);
			throw new DBException(Messages.ERR_CANNOT_DELETE_FLIGHT_BY_ID, ex);
		} finally {
			close(con, pstmt, rs);
		}
    }

	/**
	 * Returns  flight with the given identifier
	 *
	 * @param id
	 * @return flight
	 */

    @Override
    public Flight find(long id) throws DBException {
		Flight flight = null;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstm = null;
		try {

			con = getConnection();
			assert (con != null);
			con.setAutoCommit(false);
			pstm = con.prepareStatement(SQL_FIND_FLIGHT_BY_ID);
			pstm.setLong(1, id);
			rs = pstm.executeQuery();
			while (rs.next()) {
				flight = extractFlight(rs);
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_FIND_FLIGHT_BY_ID, ex);
			throw new DBException(Messages.ERR_CANNOT_FIND_FLIGHT_BY_ID, ex);
		} finally {
			close(con, pstm, rs);
		}

		return flight;
    }

	/**
	 * Returns all flights.
	 *
	 * @return List of flights.
	 */

    @Override
    public List<Flight> findAll() throws DBException {
		List<Flight> flightsList = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_ALL_FLIGHTS_LIST);
			while (rs.next()) {
				flightsList.add(extractFlight(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_FIND_FLIGHT_LIST, ex);
			throw new DBException(Messages.ERR_CANNOT_FIND_FLIGHT_LIST, ex);
		} finally {
			close(con, stmt, rs);
		}
		return flightsList;
    }
}
