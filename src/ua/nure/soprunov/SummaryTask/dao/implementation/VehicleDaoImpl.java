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
import ua.nure.soprunov.SummaryTask.dao.VehicleDao;
import ua.nure.soprunov.SummaryTask.Util.Fields;
import ua.nure.soprunov.SummaryTask.dao.entity.Vehicle;
import ua.nure.soprunov.SummaryTask.exception.DBException;
import ua.nure.soprunov.SummaryTask.exception.Messages;

import javax.sql.DataSource;

/**
 * Vehicle DAO implementation. Performs basic read/write operations on Vehicle entity.
 *
 *  @authors Soprunov Igor
 *
 */


public class VehicleDaoImpl extends DatabaseAbstractRepository <Vehicle> implements VehicleDao {
	
	private static final Logger LOG = Logger.getLogger(VehicleDaoImpl.class);
	
	// //////////////////////////////////////////////////////////
		// SQL queries
		// //////////////////////////////////////////////////////////

	
	private static final String SQL_FIND_ALL_VECHICLE_LIST = "select * from mydb.vehicles ";
	
	
	private static final String SQL_DELETE_VEHICAL_BY_ID = "DELETE FROM mydb.vehicles WHERE mydb.vehicles.id=?";
	
	private static final String SQL_CREATE_VEHICLE = "INSERT INTO mydb.vehicles VALUES (DEFAULT,?,?,?,?)";
	
	private static final String SQL_FIND_VEHICLE_BY_ID = SQL_FIND_ALL_VECHICLE_LIST + " WHERE mydb.vehicles.id=?";
	
	private static final String SQL_UPDATE_VECHICLE = "UPDATE mydb.vehicles SET mydb.vehicles.model=?, mydb.vehicles.range=?, mydb.vehicles.type=?,"
			+ " mydb.vehicles.status=?" + "	WHERE mydb.vehicles.id=?";
	
	private static final String SQL_UPDATE_VECHICAL_CHANGE_STATUS = "UPDATE `vehicles` SET `status`=?" + "WHERE id=?";

//	/**
//	 * Initializes DataSource object.
//	 *
//	 */
//
//	public VehicleDaoImpl() throws DBException{
//		super(DataSourceFactory.getDataSource(DataSourceType.MY_SQL_DATASOURCE));
//	}

	/**
	 * Initializes DataSource object.
	 *
	 * @param dataSource
	 */

	public VehicleDaoImpl(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	public  List<Vehicle> findVechicleListSortBy(int start, int recordsPerPage, String orderBy) throws DBException {
		List<Vehicle> requestList = new ArrayList<>();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstm = null;

		try {
			con = getConnection();
			assert (con != null);
			pstm = con.prepareStatement(SQL_FIND_ALL_VECHICLE_LIST + "order by " + orderBy + " limit ?,?");
			pstm.setInt(1, start * recordsPerPage);
			pstm.setInt(2, recordsPerPage);
			rs = pstm.executeQuery();
			while (rs.next()) {
				requestList.add(extractVechical(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_FIND_ALL_VECHICLE_LIST, ex);
			throw new DBException(Messages.ERR_CANNOT_FIND_ALL_VECHICLE_LIST, ex);
		} finally {
			close(con, pstm, rs);
		}
		return requestList;
	}
	
	
	

	private static Vehicle extractVechical(ResultSet rs) throws SQLException {
		Vehicle vehicle = new Vehicle();
		vehicle.setId(rs.getLong(Fields.ENTITY_ID));
		vehicle.setModel(rs.getString(Fields.LIST_VEHICLE_MODEL));
		vehicle.setRange(rs.getInt(Fields.LIST_VEHICLE_RANGE));
		vehicle.setType(rs.getString(Fields.LIST_VEHICLE_TYPE));
		vehicle.setStatus(rs.getString(Fields.LIST_VEHICLE_STATUS));
		return vehicle;
	}


//	public static void changeStatus(String carStatus, String carId) throws DBException {
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		Connection con = null;
//		try {
//			con = DBManager.getInstance().getConnection();
//			pstmt = con.prepareStatement(SQL_UPDATE_VECHICAL_CHANGE_STATUS);
//			pstmt.setString(1, carStatus);
//			pstmt.setString(2, carId);
//			pstmt.executeUpdate();
//			con.commit();
//		} catch (SQLException ex) {
//			LOG.error(Messages.ERR_CANNOT_UPDATE_VECHICAL_CHANGE_STATUS, ex);
//			throw new DBException(Messages.ERR_CANNOT_UPDATE_VECHICAL_CHANGE_STATUS, ex);
//		} finally {
//			DBManager.getInstance().close(con, pstmt, rs);
//		}
//	}

	@Override
	public void create(Vehicle vehicle) throws DBException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_CREATE_VEHICLE,PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, vehicle.getModel());
			pstmt.setInt(2, vehicle.getRange());
			pstmt.setString(3, vehicle.getType());
			pstmt.setString(4, vehicle.getStatus());
			pstmt.execute();
			con.commit();
			rs = pstmt.getGeneratedKeys();

			if (rs.next()) {
				vehicle.setId(rs.getLong(Fields.GENERATED_KEY));
			}
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_CREATE_VEHICLE, ex);
			throw new DBException(Messages.ERR_CANNOT_CREATE_VEHICLE, ex);
		} finally {
			close(con, pstmt, rs);
		}
	}

	@Override
	public void update(Vehicle vehicle) throws DBException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_UPDATE_VECHICLE);
			pstmt.setString(1, vehicle.getModel());
			pstmt.setInt(2, vehicle.getRange());
			pstmt.setString(3, vehicle.getType());
			pstmt.setString(4, vehicle.getStatus());
			pstmt.setLong(5, vehicle.getId());
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_UPDATE_VECHICLE, ex);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_VECHICLE, ex);
		} finally {
			close(con, pstmt, rs);
		}



	}

	@Override
	public void delete(Vehicle vehicle) throws DBException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_DELETE_VEHICAL_BY_ID);
			pstmt.setLong(1, vehicle.getId());
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException ex) {
			LOG.error(Messages.ERR_CANNOT_DELETE_VEHICAL_BY_ID, ex);
			throw new DBException(Messages.ERR_CANNOT_DELETE_VEHICAL_BY_ID, ex);
		} finally {
			close(con, pstmt, rs);
		}


	}

	@Override
	public Vehicle find(long id) throws DBException {
		Vehicle vehicle =null;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {

			con = getConnection();
			assert (con != null);
			pstmt = con.prepareStatement(SQL_FIND_VEHICLE_BY_ID);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vehicle = extractVechical(rs);
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_FIND_VEHICLE_BY_ID, ex);
			throw new DBException(Messages.ERR_CANNOT_FIND_VEHICLE_BY_ID, ex);
		} finally {
			close(con, pstmt, rs);
		}

		return vehicle;
	}

	@Override
	public List<Vehicle> findAll() throws DBException {
		List<Vehicle> vehiclesList = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;

		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_ALL_VECHICLE_LIST);
			while (rs.next()) {
				vehiclesList.add(extractVechical(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_FIND_ALL_VECHICLE_LIST, ex);
			throw new DBException(Messages.ERR_CANNOT_FIND_ALL_VECHICLE_LIST, ex);
		} finally {
			close(con, stmt, rs);
		}
		return vehiclesList ;
	}
}
