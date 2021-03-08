package ear.entity;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Entrainement implements Serializable {



    private int id;

    private Type_activite type_activite;
    private int etat;
    private Date date;
    private List<Utilisateur> participants;

    public List<Utilisateur> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Utilisateur> participants) {
        this.participants = participants;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Type_activite getType_activite() {
        return type_activite;
    }

    public void setType_activite(Type_activite type_activite) {
        this.type_activite = type_activite;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Entrainement(int id, Type_activite type_activite, int etat, Date date) {
        this.id = id;
        this.type_activite = type_activite;
        this.etat = etat;
        this.date = date;
        this.participants = new ArrayList<Utilisateur>();
    }

    public Entrainement(Type_activite type_activite, int etat, Date date) {
        this.type_activite = type_activite;
        this.etat = etat;
        this.date = date;
        this.participants = new ArrayList<Utilisateur>();
    }

    public Entrainement(Type_activite type_activite, int etat,List<Utilisateur> participants) {
        this.type_activite = type_activite;
        this.etat = etat;
        this.date = new Date();
        this.participants = participants;
    }

    public Entrainement() {
        this.date = new Date();
        this.participants = new ArrayList<Utilisateur>();
    }

    @Override
    public String toString() {
        return "Entrainement{" +
                "id=" + id +
                ", type_activite=" + type_activite +
                ", etat=" + etat +
                ", date=" + date +
                ", participants=" + participants +
                '}';
    }
}

