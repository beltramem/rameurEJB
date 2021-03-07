package ear.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

public class Activite_duree extends Type_activite {
    private double duree;

    public double getDuree() {
        return duree;
    }

    public void setDuree(double duree) {
        this.duree = duree;
    }

    public Activite_duree(int id, String nom, String description, double duree) {
        super(id, nom, description);
        this.duree = duree;
    }

    public Activite_duree(){

    }

    public Activite_duree(double duree) {
        this.duree = duree;
    }
}
