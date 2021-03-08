package ear.model;

import ear.entity.Mesure;
import ear.entity.Utilisateur;
import ear.message.Consumer;
import ear.message.Sender;

import java.util.Date;

public abstract class Entrainement implements EntrainementCoop,EntrainementSolo {

    protected ear.entity.Entrainement entrainementData;
    protected Sender senderData;
    protected Sender senderTopic;
    protected Consumer consumer;
    protected Utilisateur utilisateur;

    public Mesure mesureAleatoire(String pIdUtilisateur, Integer pIdCourse, Integer pIdEntrainement) {

        String idUtilisateur = pIdUtilisateur;
        Integer idCourse = pIdCourse;
        Integer idEntrainement = pIdEntrainement;
        Date d = new Date();
        double vitesse = (Math.random() * 7) + 0;
        double distanceParcourue = (Math.random() * 100) + 1;
        double caloriesBrulees = (Math.random() * 200) + 1;
        double puissanceDevellopee = (Math.random() * 150) + 5;
        double rythmeCardiaque = (Math.random() * 200) + 60;

        return new Mesure(idUtilisateur, d, vitesse, distanceParcourue, caloriesBrulees, puissanceDevellopee, rythmeCardiaque, idCourse, idEntrainement);
    }

    public Entrainement(ear.entity.Entrainement entrainementData, String queu, Utilisateur usr) {
        this.entrainementData = entrainementData;
        this.senderData = new Sender(queu);
        this.utilisateur = usr;
    }
}
