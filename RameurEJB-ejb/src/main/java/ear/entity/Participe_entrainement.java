package ear.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "participe_entrainement")
public class Participe_entrainement implements Serializable {
    @Id
    @ManyToOne @JoinColumn(name="id", nullable=false)
    private Entrainement entrainement;
    @Id
    @ManyToOne @JoinColumn(name="identifiant", nullable=false)
    private Utilisateur usr;
    private int etat;


    public Entrainement getEnt() {
        return entrainement;
    }

    public void setEnt(Entrainement ent) {
        this.entrainement = ent;
    }

    public Utilisateur getUsr() {
        return usr;
    }

    public void setUsr(Utilisateur usr) {
        this.usr = usr;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public Participe_entrainement(Entrainement ent, Utilisateur usr, int etat) {
        this.entrainement = ent;
        this.usr = usr;
        this.etat = etat;
    }

    public Participe_entrainement() {
    }
}


