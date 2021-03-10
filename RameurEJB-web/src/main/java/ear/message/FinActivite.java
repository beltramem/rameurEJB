package ear.message;

import java.io.Serializable;

public class FinActivite implements Serializable {
    private int etat;
    private Integer id_course;
    private Integer id_entrainement;

    public Integer getId_course() {
        return id_course;
    }

    public void setId_course(Integer id_course) {
        this.id_course = id_course;
    }

    public Integer getId_entrainement() {
        return id_entrainement;
    }

    public void setId_entrainement(Integer id_entrainement) {
        this.id_entrainement = id_entrainement;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public FinActivite(int etat, Integer id_course, Integer id_entrainement) {
        this.etat = etat;
        this.id_course = id_course;
        this.id_entrainement = id_entrainement;
    }
}
