package ear.model;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MesuresAleatoire extends Mesure {
    private String idUtilisateur;
    private Integer idCourse;
    private Integer idEntrainement;
    private String date;
    private double vitesse;
    private double distanceParcourue;
    private Integer caloriesBrulees;
    private Integer puissanceDevellopee;
    private Integer rythmeCardiaque;

    public MesuresAleatoire(String idUtilisateur, Integer idCourse, Integer idEntrainement) {
        this.idUtilisateur = idUtilisateur;
        this.idCourse = idCourse;
        this.idEntrainement = idEntrainement;
        java.util.Date d = new java.util.Date();
        this.date=(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
        this.vitesse = (Math.random() * 7) + 0;
        this.distanceParcourue = (Math.random() * 100) + 1;
        this.caloriesBrulees = (int)(Math.random() * 200) + 1;
        this.puissanceDevellopee= (int)(Math.random() * 150) + 5;
        this.rythmeCardiaque= (int)(Math.random() * 200) + 60;
    }

    @Override
    public String toString()
    {
        String s="";
        s+="Utilisateur: "+this.idUtilisateur+"\n"+
                "Date: "+this.date.toString()+"\n"+
                "Distance Parcourue: "+this.distanceParcourue+"\n"+
                "Vitesse: "+this.vitesse+"\n"+
                "Calories: "+this.caloriesBrulees+"\n"+
                "Puissance: "+this.puissanceDevellopee+"\n"+
                "Rythme cardiaque: "+this.rythmeCardiaque+"\n";
        return s;
    }
}