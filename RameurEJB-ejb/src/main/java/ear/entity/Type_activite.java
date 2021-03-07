package ear.entity;



import static ear.entity.Type_activite.QN.ALL_ACTIVITES;
import static ear.entity.Type_activite.QN.PAR_ID;
import static ear.entity.Type_activite.QN.ALL_ACTIVITES_DUREE;



import java.io.Serializable;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@Table(name = "type_activite")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({@NamedQuery(name=ALL_ACTIVITES, query="select o FROM Type_activite o"),
        @NamedQuery(name=PAR_ID, query="select o FROM Type_activite o WHERE o.id = :ID"),
        @NamedQuery(name=ALL_ACTIVITES_DUREE, query ="select o FROM Type_activite o join Activite_duree ad on o.id=ad.id order by o.id")}
        )
@XmlTransient
public class Type_activite implements Serializable {



    public static interface QN {

        /**

         * Search all authors.

         */

        String ALL_ACTIVITES = "type_activite.allActivites";



        /**

         * Search a named author.

         */

        String PAR_ID = "type_activite.parID";

        String ALL_ACTIVITES_DUREE = "type_activite.ALL_ACTIVITES_DUREE";

    }



    //private static final long serialVersionUID = 0l;

    @Id
    private int id;

    private String nom;

    private String description;



    public int getId() {

        return id;

    }



    public String getNom() {

        return nom;

    }



    public String getDescription() {

        return description;

    }



    public void setId(int id) {

        this.id = id;

    }



    public void setNom(String nom) {

        this.nom = nom;

    }



    public void setDescription(String description) {

        this.description = description;

    }



    public Type_activite(int id, String nom, String description) {

        this.id = id;

        this.nom = nom;

        this.description = description;

    }



    public Type_activite() {

    }







}