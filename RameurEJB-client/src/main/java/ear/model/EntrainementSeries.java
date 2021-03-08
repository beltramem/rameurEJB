package ear.model;

import ear.entity.Mesure;
import ear.entity.Utilisateur;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static java.lang.Math.pow;

public class EntrainementSeries extends  Entrainement{
    private int nbCoups;
    private boolean toFinish = false;

    public EntrainementSeries(ear.entity.Entrainement entrainementData, String queu, Utilisateur usr,int nbCoups) {
        super(entrainementData,queu,usr);
        this.nbCoups=nbCoups;
    }
    @Override
    public void lancerEntrainementSolo() throws IOException, TimeoutException, InterruptedException {
        while (!toFinish) {

            Mesure mesure = this.rl.getMesure(this.utilisateur.getIdentifiant(),this.entrainementData.getId());
            this.senderData.send_mesure(mesure);
            toFinish= (this.nbCoups <= mesure.getNbCoup());
            Thread.sleep(500);
        }
    }
}
