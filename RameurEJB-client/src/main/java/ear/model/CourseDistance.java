package ear.model;

import ear.entity.Mesure;
import ear.entity.Utilisateur;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class CourseDistance extends  Course{
    private double distance;
    private boolean toFinish = false;

    public CourseDistance(ear.entity.Course courseData, String queu, String topic,Utilisateur usr, double distance) {
        super(courseData,queu,topic,usr);
        this.distance=distance;
    }

    @Override
    public void lancerCourseVShumain() throws IOException, TimeoutException, InterruptedException {

        while (!toFinish) {

            Mesure mesure = this.rl.getMesure(this.utilisateur.getIdentifiant(),this.courseData.getId());
            System.out.println(mesure.toString());
            this.senderData.send_mesure(mesure);
            toFinish = (distance <= mesure.getDistance_parcourue());
            Thread.sleep(500);
        }
        //this.rl.goToMenu();
    }
}
