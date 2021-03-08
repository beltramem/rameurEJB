package ear.model;

import ear.entity.Mesure;
import ear.entity.Utilisateur;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class EntrainementDuree extends  Entrainement{

    private double duree;

    public EntrainementDuree(ear.entity.Entrainement entrainementData, String queu, Utilisateur usr, double duree) {
        super(entrainementData,queu,usr);
        this.duree=duree;
    }



    @Override
    public void lancerEntrainementSolo() throws IOException, TimeoutException, InterruptedException {
        long temps_depart = System.currentTimeMillis();
        while((TimeUnit.MILLISECONDS.toSeconds(temps_depart) - TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())) < Double.valueOf(this.duree).longValue())
        {
            System.out.println("td"+TimeUnit.MILLISECONDS.toSeconds(temps_depart)+"tmp"+TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())+"duree"+Double.valueOf(this.duree).longValue());
            //System.out.println(this.entrainementData.getId());
            Mesure mesure = this.mesureAleatoire(this.utilisateur.getIdentifiant(),null,Integer.valueOf(this.entrainementData.getId()));
            //System.out.println(mesure.toString());
            this.senderData.send_mesure(mesure);

            Thread.sleep(500);
        }
    }
}
