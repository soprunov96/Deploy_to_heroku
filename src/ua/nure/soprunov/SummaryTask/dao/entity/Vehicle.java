package ua.nure.soprunov.SummaryTask.dao.entity;

/**
 * Vehicle entity.This  object characterized by model, range,
 * type,status. Every field must be filled.
 *
 @authors Soprunov Igor
 */

public class Vehicle extends Entity {

    private static final long serialVersionUID = -3366494374075967796L;

    String model;

    int range;

    String type;

    String status;

    public Vehicle() {
    }

    public Vehicle(String model, int range, String type, String status) {
        this.model = model;
        this.range = range;
        this.type = type;
        this.status = status;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Vechical [ model=" + model
				+ ", range=" + range
				+ ", type=" + type
                + ", status=" + status + "]";
    }

}
