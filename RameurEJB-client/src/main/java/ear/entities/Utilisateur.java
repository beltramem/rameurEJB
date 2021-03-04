package ear.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Utilisateur implements Serializable {




    private String identifiant;
    private String mdp;
    private String nom;
    private String prenom;
    private double taille;
    private double poids;
    private Date date_naissance;
    private List<Entrainement> entrainements;


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
        return date_naissance;
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
}
