package ear.entity;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

import java.io.Serializable;

@Entity
@Table(name = "participe_course")
public class Participe_course implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name="id", nullable=false)
    private Course course;
    @Id
    @ManyToOne @JoinColumn(name="identifiant", nullable=false)
    private Utilisateur usr;
    private int etat;

    @XmlTransient
    @JsonIgnore
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
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

    public Participe_course(Course course, Utilisateur usr, int etat) {
        this.course = course;
        this.usr = usr;
        this.etat = etat;
    }

    public Participe_course() {
    }
}
