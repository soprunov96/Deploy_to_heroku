package ua.nure.soprunov.SummaryTask.dao.entity;

/**
 * Request entity.
 * Every flight have range, date, carType, driver id who create this request.
 *
 @authors Soprunov Igor
 */

public class Request extends Entity {


    private static final long serialVersionUID = 2120929153851692595L;


    private int range;

    private String carType;

    private Long driverId;

    public Request() {
    }

    public Request(int range, String carType, Long driverId) {
        this.range = range;
        this.carType = carType;
        this.driverId = driverId;
    }


    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    @Override
    public String toString() {
        return "Request [ range=" + range + ", carType=" + carType + ", driverId="
                + driverId + "]";
    }

}
