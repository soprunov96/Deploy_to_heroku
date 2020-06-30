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
import ua.nure.soprunov.SummaryTask.dao.RequestDao;
import ua.nure.soprunov.SummaryTask.Util.Fields;
import ua.nure.soprunov.SummaryTask.dao.entity.Request;
import ua.nure.soprunov.SummaryTask.exception.DBException;
import ua.nure.soprunov.SummaryTask.exception.Messages;

import javax.sql.DataSource;

/**
 * Request DAO implementation. Performs basic read/write operations on Request entity.
 *
 *  @authors Soprunov Igor
 *
 */

public class RequestDaoImpl extends DatabaseAbstractRepository<Request> implements RequestDao {

	private static final Logger LOG = Logger.getLogger(RequestDaoImpl.class);

	// //////////////////////////////////////////////////////////
		// SQL queries
		// //////////////////////////////////////////////////////////

	
	private static final String SQL_CONSIDER_REQUEST = "INSERT INTO flights "
			+ "  VALUES (default, ?, ?, ?, ?, ?, ?,?)";
	
	private static final String SQL_FIND_ALL_REQUEST_LIST = "select * from mydb.requests ";
	
	private static final String SQL_FIND_REQUEST_BY_ID = SQL_FIND_ALL_REQUEST_LIST + " where mydb.requests.request_id=?";
	
	private static final String SQL_CREATE_REQUEST = "INSERT INTO mydb.requests  (`request_id`,`range`,`type`,`driver_id`)"
			+ " VALUES (default,?,?,?)";

	private static final String SQL_DELETE_REQUEST_BY_ID = "DELETE FROM mydb.requests WHERE mydb.requests.request_id =?";

	private static final String SQL_UPDATE_FLIGHT_REQUEST_BY_ID = "UPDATE mydb.flights SET mydb.flights.request_id = null  WHERE mydb.flights.request_id=?";



//	/**
//	 * Initializes DataSource object.
//	 *
//	 */
//	public RequestDaoImpl() {
//		super(DataSourceFactory.getDataSource(DataSourceType.MY_SQL_DATASOURCE));
//	}


	/**
	 * Initializes DataSource object.
	 *
	 * @param dataSource
	 */
	public RequestDaoImpl(DataSource dataSource) {
		super(dataSource);
	}


	/**
	 * Returns limited sorted flight list  where request_id IS NULL
	 * 
	 * @param recordsPerPage
	 * @param start
	 * @param orderBy 
	 * 
	 * @return List of flights.
	 */
//	
//	public void considerRequest(String name, String date, String arrival, String depart, Long driverId, Long carId,
//			String status) throws DBException {
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		Connection con = null;
//
//		try {
//			con = DBManager.getInstance().getConnection();
//			pstmt = con.prepareStatement(SQL_CONSIDER_REQUEST);
//			pstmt.setString(1, name);
//			pstmt.setString(2, date);
//			pstmt.setString(3, depart);
//			pstmt.setString(4, arrival);
//			pstmt.setLong(5, driverId);
//			pstmt.setLong(6, carId);
//			pstmt.setString(7, status);
//			pstmt.executeUpdate();
//			con.commit();
//		} catch (SQLException ex) {
//			DBManager.getInstance().rollback(con);
//			LOG.error(Messages.ERR_CANNOT_CONSIDER_REQUEST, ex);
//			throw new DBException(Messages.ERR_CANNOT_CONSIDER_REQUEST, ex);
//		} finally {
//			DBManager.getInstance().close(con, pstmt, rs);
//		}
//
//	}

//	/**
//	 * Returns request list
//	 *
//	 *
//	 * @return List of request.
//	 */
//
//	public static List<Request> findRequestsList() throws DBException {
//		List<Request> requestList = new ArrayList<>();
//		Statement stmt = null;
//		ResultSet rs = null;
//		Connection con = null;
//
//		try {
//			con = DBManager.getInstance().getConnection();
//			stmt = con.createStatement();
//			rs = stmt.executeQuery(SQL_FIND_ALL_REQUEST_LIST);
//			while (rs.next()) {
//				requestList.add(extractRequestList(rs));
//			}
//			con.commit();
//		} catch (SQLException ex) {
//			DBManager.getInstance().rollback(con);
//			LOG.error(Messages.ERR_CANNOT_FIND_ALL_REQUEST_LIST, ex);
//			throw new DBException(Messages.ERR_CANNOT_FIND_ALL_REQUEST_LIST, ex);
//		} finally {
//			DBManager.getInstance().close(con, stmt, rs);
//		}
//		return requestList;
//	}
	
	/**
	 * Extracts a request list from the result set.
	 * 
	 * @param rs Result set from which a request list will be extracted.
	 * @return Menu item entity.
	 */
	
	private static Request extractRequestList(ResultSet rs) throws SQLException {
		Request requestList = new Request();
		requestList.setId(rs.getLong(Fields.LIST_REQUEST_ID));
//		requestList.setRequestId(rs.getLong(Fields.LIST_REQUEST_ID));
		requestList.setRange(rs.getInt(Fields.LIST_REQUEST_VEHICLE_RANGE));
		requestList.setCarType(rs.getString(Fields.LIST_REQUEST_VEHICLE_TYPE));
		requestList.setDriverId(rs.getLong(Fields.LIST_REQUEST_DRIVER_ID));

		return requestList;
	}
	
//	/**
//	 * Returns request with the given identifier
//	 *
//	 * @param id
//	 *
//	 * @return request.
//	 */
//	public static Request findRequestById(long id) throws DBException {
//		Request request = new Request();
//		Connection con = null;
//		ResultSet rs = null;
//		PreparedStatement pstm = null;
//		try {
//
//			con = DBManager.getInstance().getConnection();
//			assert (con != null);
//			con.setAutoCommit(false);
//			pstm = con.prepareStatement(SQL_FIND_REQUEST_BY_ID);
//			pstm.setLong(1, id);
//			rs = pstm.executeQuery();
//			while (rs.next()) {
//				request = extractRequestList(rs);
//
//			}
//			con.commit();
//		} catch (SQLException ex) {
//			DBManager.getInstance().rollback(con);
//			LOG.error(Messages.ERR_CANNOT_FIND_REQUEST_BY_ID, ex);
//			throw new DBException(Messages.ERR_CANNOT_FIND_REQUEST_BY_ID, ex);
//		} finally {
//			DBManager.getInstance().close(con, pstm, rs);
//		}
//
//		return request;
//	}
	
	
//	/**
//	 * create request
//	 *
//	 * @param range
//	 * @param type
//	 * @param driverId
//	 */
//	public static void createRequest(Integer range, String type, Long driverId) throws DBException {
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		Connection con = null;
//
//		try {
//			con = DBManager.getInstance().getConnection();
//			pstmt = con.prepareStatement(SQL_CREATE_REQUEST);
//			pstmt.setInt(1, range);
//			pstmt.setString(2, type);
//			pstmt.setLong(3, driverId);
//
//			pstmt.executeUpdate();
//			con.commit();
//		} catch (SQLException ex) {
//			DBManager.getInstance().rollback(con);
//			LOG.error(Messages.ERR_CANNOT_CREATE_REQUEST, ex);
//			throw new DBException(Messages.ERR_CANNOT_CREATE_REQUEST, ex);
//		} finally {
//			DBManager.getInstance().close(con, pstmt, rs);
//		}
//
//	}

	@Override
	public void create(Request request) throws DBException {
		PreparedStatement prstmt = null;
		ResultSet rs = null;
		Connection con = null;

		try {
			con = getConnection();
			prstmt = con.prepareStatement(SQL_CREATE_REQUEST,PreparedStatement.RETURN_GENERATED_KEYS);
			prstmt.setInt(1, request.getRange());
			prstmt.setString(2, request.getCarType());
			prstmt.setLong(3, request.getDriverId());

			prstmt.executeUpdate();
			con.commit();
			rs = prstmt.getGeneratedKeys();

			if (rs.next()) {
				request.setId(rs.getLong(Fields.GENERATED_KEY));
			}

		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_CREATE_REQUEST, ex);
			throw new DBException(Messages.ERR_CANNOT_CREATE_REQUEST, ex);
		} finally {
			close(con, prstmt, rs);
		}

	}

	@Override
	public void update(Request request) throws DBException {


	}

	@Override
	public void delete(Request request) throws DBException {
		PreparedStatement pstmt = null;
		Connection con = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_UPDATE_FLIGHT_REQUEST_BY_ID);
			pstmt.setLong(1, request.getId());
			pstmt.executeUpdate();
			con.commit();

			con = getConnection();
			pstmt = con.prepareStatement(SQL_DELETE_REQUEST_BY_ID);
			pstmt.setLong(1, request.getId());
			pstmt.executeUpdate();
			con.commit();



		} catch (SQLException ex) {
			LOG.error(Messages.ERR_CANNOT_DELETE_REQUEST_BY_ID, ex);
			throw new DBException(Messages.ERR_CANNOT_DELETE_REQUEST_BY_ID, ex);
		} finally {
			close(con, pstmt, null);
		}

	}

	@Override
	public Request find(long id) throws DBException {
		Request request = null;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstm = null;
		try {

			con = getConnection();
			assert (con != null);
			con.setAutoCommit(false);
			pstm = con.prepareStatement(SQL_FIND_REQUEST_BY_ID);
			pstm.setLong(1, id);
			rs = pstm.executeQuery();
			while (rs.next()) {
				request = extractRequestList(rs);

			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_FIND_REQUEST_BY_ID, ex);
			throw new DBException(Messages.ERR_CANNOT_FIND_REQUEST_BY_ID, ex);
		} finally {
			close(con, pstm, rs);
		}

		return request;
	}

	@Override
	public List<Request> findAll() throws DBException {
		List<Request> requestList = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;

		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_ALL_REQUEST_LIST);
			while (rs.next()) {
				requestList.add(extractRequestList(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_FIND_ALL_REQUEST_LIST, ex);
			throw new DBException(Messages.ERR_CANNOT_FIND_ALL_REQUEST_LIST, ex);
		} finally {
			close(con, stmt, rs);
		}
		return requestList;
	}
}
