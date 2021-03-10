package ear.model;

import ear.entity.Mesure;
import ear.entity.Utilisateur;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static java.lang.Math.pow;

public class EntrainementDuree extends  Entrainement{

    private double duree;
    private double totalTime;
    private long startTime = System.nanoTime();
    private boolean toFinish = false;

    public EntrainementDuree(ear.entity.Entrainement entrainementData, String queu, Utilisateur usr, double duree) {
        super(entrainementData,queu,usr);
        this.duree=duree;
        this.totalTime = (this.duree*pow(10,9));
    }



    @Override
    public void lancerEntrainementSolo() throws IOException, TimeoutException, InterruptedException {
        long temps_depart = System.currentTimeMillis();
        while(!toFinish)
        {
            System.out.println("totalTime: "+totalTime+" startTime: "+startTime + " duree: "+duree + " Pow: "+(duree*pow(10,9)));
            //System.out.println(this.entrainementData.getId());
            Mesure mesure = this.rl.getMesure(this.utilisateur.getIdentifiant(),null,this.entrainementData.getId());
            //System.out.println(mesure.toString());
            this.senderQueuData.send_mesure(mesure);
            toFinish = (System.nanoTime() - startTime >= totalTime);
            Thread.sleep(500);
        }
    }
}
