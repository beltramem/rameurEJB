package ear.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import static ear.entity.Utilisateur.QN.ALL_COMPTE;
import static ear.entity.Utilisateur.QN.FIND_COMPTE;

@Entity
@Table(name = "utilisateur")
@NamedQueries({@NamedQuery(name=ALL_COMPTE, query="select o FROM Utilisateur o"),
        @NamedQuery(name=FIND_COMPTE, query="select o FROM Utilisateur o WHERE o.identifiant = :ID")})
@XmlRootElement
public class Utilisateur implements Serializable {

    public static interface QN {
        /**
         * Search all authors.
         */
        String ALL_COMPTE = "utilisateur.allCompte";

        /**
         * Search a named author.
         */
        String FIND_COMPTE = "utilisateur.findCompte";
    }

    //private static final long serialVersionUID = 0l;
    @Id
    private String identifiant;
    private String mdp;
    private String nom;
    private String prenom;
    private double taille;
    private double poids;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="CET")
    private Date date_naissance;

    @ManyToMany
    @JoinTable( name = "participe_entrainement",
            joinColumns = @JoinColumn( name = "identifiant" ),
            inverseJoinColumns = @JoinColumn( name = "id" ) )
    private List<Entrainement> entrainements;

    @XmlTransient
    public List<Entrainement> getEntrainements() {
        return entrainements;
    }

    public void setEntrainements(List<Entrainement> entrainements) {
        this.entrainements = entrainements;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public double getTaille() {
        return taille;
    }

    public void setTaille(double taille) {
        this.taille = taille;
    }

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public Date getDate_naissance() {
        return this.date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public Utilisateur(String identifiant, String mdp, String nom, String prenom, double taille, double poids, Date date_naissance) {
        this.identifiant = identifiant;
        this.mdp = mdp;
        this.nom = nom;
        this.prenom = prenom;
        this.taille = taille;
        this.poids = poids;
        this.date_naissance = date_naissance;
    }

    public Utilisateur()
    {
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "identifiant='" + identifiant + '\'' +
                ", mdp='" + mdp + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", taille=" + taille +
                ", poids=" + poids +
                ", date_naissance=" + date_naissance +
                '}';
    }
}
