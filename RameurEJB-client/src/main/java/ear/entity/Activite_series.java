package ear.entity;

public class Activite_series extends Type_activite {
    private int nbSeries;


    public int getNbSeries() {
        return nbSeries;
    }

    public void setNbSeries(int nbSeries) {
        this.nbSeries = nbSeries;
    }

    public Activite_series(int id, String nom, String description, int nbCoups) {
        super(id, nom, description);
        this.nbSeries = nbCoups;
    }

    public Activite_series() {}


    public Activite_series(int nbCoups) {
        this.nbSeries = nbCoups;
    }

    @Override
    public String toString() {
        return "Activite_series{" +
                "nbCoups=" + nbSeries +
                '}';
    }
}
