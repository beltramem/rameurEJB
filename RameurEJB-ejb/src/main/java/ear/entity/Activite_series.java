package ear.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "activite_series")
public class Activite_series extends Type_activite {
    @Column
    private int nbSeries;

    public int getNbSeries() {
        return nbSeries;
    }

    public void setNbSeries(int nbSeries) {
        this.nbSeries = nbSeries;
    }

    public Activite_series(int id, String nom, String description, int nbSeries) {
        super(id, nom, description);
        this.nbSeries = nbSeries;
    }

    public Activite_series(){

    }

    public Activite_series(int nbSeries) {
        this.nbSeries = nbSeries;
    }
}
