package ear.entity;

import java.io.Serializable;
import java.util.Date;

public class Mesure implements Serializable {


    private static final long serialVersionUID = 1L;
    private int id;
    private String identifiant_utilisateur;
    private Date date;
    private double vitesse;
    private double distance_parcourue;
    private double calories_brulees;
    private Integer puissance_developpe;
    private Integer rythme_cardiaque;
    private Integer id_course;
    private Integer id_entrainement;
    transient int nbCoup=0;

    public int getNbCoup() {
        return nbCoup;
    }

    public void setNbCoup(int nbCoup) {
        this.nbCoup = nbCoup;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentifiant_utilisateur() {
        return identifiant_utilisateur;
    }

    public void setIdentifiant_utilisateur(String identifiant_utilisateur) {
        this.identifiant_utilisateur = identifiant_utilisateur;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getVitesse() {
        return vitesse;
    }

    public void setVitesse(double vitesse) {
        this.vitesse = vitesse;
    }

    public double getDistance_parcourue() {
        return distance_parcourue;
    }

    public void setDistance_parcourue(double distance_parcourue) {
        this.distance_parcourue = distance_parcourue;
    }

    public double getCalories_brulees() {
        return calories_brulees;
    }

    public void setCalories_brulees(int calories_brulees) {
        this.calories_brulees = calories_brulees;
    }

    public Integer getPuissance_developpe() {
        return puissance_developpe;
    }

    public void setPuissance_developpe(Integer puissance_developpe) {
        this.puissance_developpe = puissance_developpe;
    }

    public Integer getRythme_cardiaque() {
        return rythme_cardiaque;
    }

    public void setRythme_cardiaque(Integer rythme_cardiaque) {
        this.rythme_cardiaque = rythme_cardiaque;
    }

    public Integer getIdentifiant_course() {
        return id_course;
    }

    public void setIdentifiant_course(Integer identifiant_course) {
        this.id_course = identifiant_course;
    }

    public Integer getGetIdentifiant_entrainement() {
        return id_entrainement;
    }

    public void setGetIdentifiant_entrainement(Integer getIdentifiant_entrainement) {
        this.id_entrainement = getIdentifiant_entrainement;
    }

    public Mesure(String identifiant_utilisateur, Date date, double vitesse, double distance_parcourue, double calories_brulees, Integer puissance_developpe, Integer rythme_cardiaque, Integer identifiant_course, Integer identifiant_entrainement) {
        this.identifiant_utilisateur = identifiant_utilisateur;
        this.id_course = identifiant_course;
        this.id_entrainement = identifiant_entrainement;
        this.date = date;
        this.vitesse = vitesse;
        this.distance_parcourue = distance_parcourue;
        this.calories_brulees = calories_brulees;
        this.puissance_developpe = puissance_developpe;
        this.rythme_cardiaque = rythme_cardiaque;
    }

    public Mesure(String identifiant_utilisateur, Date date, double vitesse, double distance_parcourue, double calories_brulees, Integer puissance_developpe, Integer rythme_cardiaque, Integer identifiant_course, Integer identifiant_entrainement, int nbCoup) {
        this.identifiant_utilisateur = identifiant_utilisateur;
        this.id_course = identifiant_course;
        this.id_entrainement = identifiant_entrainement;
        this.date = date;
        this.vitesse = vitesse;
        this.distance_parcourue = distance_parcourue;
        this.calories_brulees = calories_brulees;
        this.puissance_developpe = puissance_developpe;
        this.rythme_cardiaque = rythme_cardiaque;
        this.nbCoup = nbCoup;
    }

    public Mesure() {

    }


    @Override
    public String toString() {
        return "Mesure{" +
                "id=" + id +
                ", identifiant_utilisateur='" + identifiant_utilisateur + '\'' +
                ", date=" + date +
                ", vitesse=" + vitesse +
                ", distance_parcourue=" + distance_parcourue +
                ", calories_brulees=" + calories_brulees +
                ", puissance_developpe=" + puissance_developpe +
                ", rythme_cardiaque=" + rythme_cardiaque +
                ", identifiant_course=" + id_course +
                ", identifiant_entrainement=" + id_entrainement +
                ", nbCoups=" + nbCoup +
                '}';
    }
}
