package ear.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import static ear.entity.Course.QN.ALL_COURSE;
import static ear.entity.Course.QN.FIND_COURSE;

@Entity
@Table(name = "course")
@NamedQueries({@NamedQuery(name=ALL_COURSE, query="select o FROM Course o"),
        @NamedQuery(name=FIND_COURSE, query="select o FROM Course o WHERE o.id = :ID")})
public class Course implements Serializable {

    public static interface QN {
        /**
         * Search all Course.
         */
        String ALL_COURSE = "course.allCourse";

        /**
         * Search a id Course.
         */
        String FIND_COURSE = "course.findCourse";
    }

    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue( strategy=GenerationType.IDENTITY )
    private int id;
    @ManyToOne
    @JoinColumn(name="type_activite",referencedColumnName = "id")
    private Type_activite type_activite;
    private int etat;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date date;
    @OneToMany( targetEntity=Participe_course.class, mappedBy="course" )
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

    public Course(int id, Type_activite type_activite, int etat, Date date) {
        this.id = id;
        this.type_activite = type_activite;
        this.etat = etat;
        this.date = date;
        this.participants = new ArrayList<Utilisateur>();
    }

    public Course(Type_activite type_activite, int etat, Date date) {
        this.type_activite = type_activite;
        this.etat = etat;
        this.date = date;
        this.participants = new ArrayList<Utilisateur>();
    }

    public Course(Type_activite type_activite, int etat, List<Utilisateur> participants) {
        this.type_activite = type_activite;
        this.etat = etat;
        this.date = new Date();
        this.participants = participants;
    }

    public void addParticipant(Utilisateur u)
    {
        this.participants.add(u);

    }

    public Course() {
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", type_activite=" + type_activite +
                ", etat=" + etat +
                ", date=" + date +
                ", participants=" + participants +
                '}';
    }
}
