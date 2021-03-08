package ear.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


public class Course implements Serializable {


    private int id;
    private Type_activite type_activite;
    private int etat;
    private Date date;


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

    public Course(int id, Type_activite type_activite, int etat, Date date) {
        this.id = id;
        this.type_activite = type_activite;
        this.etat = etat;
        this.date = date;
    }

    public Course() {
    }
}
