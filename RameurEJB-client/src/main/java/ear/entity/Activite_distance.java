package ear.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

public class Activite_distance extends Type_activite {
    private double distance;

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Activite_distance(int id, String nom, String description, double distance) {
        super(id, nom, description);
        this.distance = distance;
    }

    public Activite_distance(){

    }

    public Activite_distance(double distance) {
        this.distance = distance;
    }
}
