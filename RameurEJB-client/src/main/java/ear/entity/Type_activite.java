package ear.entity;


import java.io.Serializable;


public class Type_activite implements Serializable {



    //private static final long serialVersionUID = 0l;

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

    @Override
    public String toString() {
        return "Type_activite{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}