package ear.model;

import ear.entity.Mesure;
import ear.entity.Utilisateur;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static java.lang.Math.pow;

public class EntrainementDistance extends  Entrainement{
    private double distance;
    private boolean toFinish = false;

    public EntrainementDistance(ear.entity.Entrainement entrainementData, String queu, Utilisateur usr, double distance) {
        super(entrainementData,queu,usr);
        this.distance=distance;
    }
    @Override
    public void lancerEntrainementSolo() throws IOException, TimeoutException, InterruptedException {

        while (!toFinish) {
            //System.out.println("totalTime: "+totalTime+" startTime: "+startTime + " duree: "+duree + " Pow: "+(duree*pow(10,9)));
            //System.out.println(this.entrainementData.getId());
            //Mesure mesure = this.mesureAleatoire(this.utilisateur.getIdentifiant(), null, Integer.valueOf(this.entrainementData.getId()));
            //System.out.println(mesure.toString());

            Mesure mesure = this.rl.getMesure(this.utilisateur.getIdentifiant(),this.entrainementData.getId());
            System.out.println(mesure.toString());
            this.senderData.send_mesure(mesure);
            toFinish = (distance <= mesure.getDistance_parcourue());
            Thread.sleep(500);
        }
        //this.rl.goToMenu();
    }
}
