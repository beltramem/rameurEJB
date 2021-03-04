package ear.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

import static ear.entity.Entrainement.QN.ALL_ENTRAINEMENT;
import static ear.entity.Entrainement.QN.FIND_ENTRAINEMENT;

@Entity
@Table(name = "entrainement")
@NamedQueries({@NamedQuery(name=ALL_ENTRAINEMENT, query="select o FROM Entrainement o"),
        @NamedQuery(name=FIND_ENTRAINEMENT, query="select o FROM Entrainement o WHERE o.id = :ID")})
public class Entrainement implements Serializable {

    public static interface QN {
        /**
         * Search all Entrainement.
         */
        String ALL_ENTRAINEMENT = "entrainement.allEntrainement";

        /**
         * Search a id Entrainement.
         */
        String FIND_ENTRAINEMENT = "entrainement.findEntrainement";

   }

    @Id
    @GeneratedValue( strategy=GenerationType.IDENTITY )
    private int id;
    @ManyToOne
    @JoinColumn(name="type_activite",referencedColumnName = "id")
    private Type_activite type_activite;
    private int etat;
    private Date date;
    @ManyToMany
    @JoinTable( name = "participe_entrainement",
            joinColumns = @JoinColumn( name = "id" ),
            inverseJoinColumns = @JoinColumn( name = "identifiant" ) )
    private List<Utilisateur> participants;

    public void add_participant(Utilisateur paticipant)
    {

    }

    @XmlTransient
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

    public Entrainement(Type_activite type_activite, int etat) {
        this.type_activite = type_activite;
        this.etat = etat;
        this.date = new Date();
        this.participants = new ArrayList<Utilisateur>();
    }

    public Entrainement() {
        this.date = new Date();
        this.participants = new ArrayList<Utilisateur>();
    }
}
