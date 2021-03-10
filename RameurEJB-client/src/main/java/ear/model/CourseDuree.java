package ear.model;

import ear.entity.Mesure;
import ear.entity.Utilisateur;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static java.lang.Math.pow;

public class CourseDuree  extends  Course{
    private double duree;
    private double totalTime;
    private long startTime = System.nanoTime();
    private boolean toFinish = false;

    public CourseDuree(ear.entity.Course courseData, String queu, String topic, Utilisateur usr, double duree) {
        super(courseData, queu, topic, usr);
        this.duree = duree;
        this.totalTime = (this.duree*pow(10,9));
    }

    @Override
    public void lancerCourseVShumain() throws IOException, TimeoutException, InterruptedException {
        String routingKey = "course."+this.courseData.getId()+".#";

        this.rl.goToMenu();
        while (!toFinish) {
        //MESURE AVEC RAMEUR
        Mesure mesure = this.rl.getMesure(this.utilisateur.getIdentifiant(),this.courseData.getId());

        //MESURE ALEATOIRE
        //Mesure mesure = this.mesureAleatoire(this.utilisateur.getIdentifiant(),this.courseData.getId(),null);
        System.out.println(mesure.toString());
        this.senderData.send_mesure(mesure);

        this.senderTopic.send_mesure(mesure,routingKey);
        this.consumerTopic.getMessage();
        toFinish = (System.nanoTime() - startTime >= totalTime);
        Thread.sleep(500);
        }
        this.rl.goToMenu();
    }
}
