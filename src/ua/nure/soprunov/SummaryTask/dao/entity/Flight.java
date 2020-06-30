package ua.nure.soprunov.SummaryTask.dao.entity;

/**
 * Flight entity.
 * Every flight have name, date, depart, arrival, status.
 * 
 @authors Soprunov Igor
 * 
 */
public class Flight extends Entity {

	private static final long serialVersionUID = 4716395168539434663L;

	private String name;
	
	private String driverName;

	private String carModel;
	
	private String date;
	
	private String depart;
	
	private String arrival;
	
	private Long requestId;
	
	private String status;

	private Long driverId;
	
	private Long vehicleId;


	public Flight() {
	}

	public Flight(String name, String date, String depart, String arrival) {
		this.name = name;
		this.date = date;
		this.depart = depart;
		this.arrival = arrival;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDepart() {
		return depart;
	}

	public void setDepart(String depart) {
		this.depart = depart;
	}

	public String getArrival() {
		return arrival;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	public Long getRequestId() {
		return requestId;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getDriverId() {
		return driverId;
	}

	public void setDriverId(Long driverId) {
		this.driverId = driverId;
	}

	public Long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}

	@Override
	public String toString() {
		return "Flight [ name=" + name + ", driverName=" + driverName + ", carModel="
				+ carModel + ", date=" + date + ", depart=" + depart + ", arrival=" + arrival + ", requestId="
				+ requestId + ", status=" + status + ", driverId=" + driverId + ", vehicleId=" + vehicleId + "]";
	}

}