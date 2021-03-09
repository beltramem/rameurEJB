package ear.model;

import ear.entity.Mesure;
import ear.entity.Utilisateur;
import ear.message.ConsumerTopic;
import ear.message.SenderQueu;
import ear.message.SenderTopic;
import ear.rower.RowerLink;

import java.util.Date;

public abstract class Course implements CourseVShumain, CourseVia{
    protected ear.entity.Course courseData;
    protected SenderQueu senderData;
    protected SenderTopic senderTopic;
    protected ConsumerTopic consumerTopic;
    protected Utilisateur utilisateur;

    protected RowerLink rl;

    public Mesure mesureAleatoire(String pIdUtilisateur, Integer pIdCourse, Integer pIdEntrainement) {

        String idUtilisateur = pIdUtilisateur;
        Integer idCourse = pIdCourse;
        Integer idEntrainement = pIdEntrainement;
        Date d = new Date();
        double vitesse = (Math.random() * 7) + 0;
        double distanceParcourue = (Math.random() * 100) + 1;
        double caloriesBrulees = (Math.random() * 200) + 1;
        Integer puissanceDevellopee = (int)(Math.random() * 150) + 5;
        Integer rythmeCardiaque = (int) (Math.random() * 200) + 60;

        return new Mesure(idUtilisateur, d, vitesse, distanceParcourue, caloriesBrulees, puissanceDevellopee, rythmeCardiaque, idCourse, idEntrainement);
    }

    public Course(ear.entity.Course courseData, String queu, String topic, Utilisateur usr) {
        this.courseData = courseData;
        this.senderData = new SenderQueu(queu);
        this.senderTopic = new SenderTopic(topic);
        this.utilisateur = usr;
        this.consumerTopic = new ConsumerTopic(this.utilisateur.getIdentifiant(),this.courseData.getId());
        //this.rl = new RowerLink();
    }
}
