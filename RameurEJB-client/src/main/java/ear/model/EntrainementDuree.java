package ear.model;

import ear.entities.Activite_duree;
import ear.entities.Utilisateur;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class EntrainementDuree extends  Entrainement{

    private double duree;

    public EntrainementDuree(ear.entities.Entrainement entrainementData, String queu, Utilisateur usr, double duree) {
        super(entrainementData,queu,usr);
        this.duree=duree;
    }


    @Override
    public void lancerEntrainementSolo() throws IOException, TimeoutException, InterruptedException {
        long temps_depart = System.currentTimeMillis();
        while((temps_depart - System.currentTimeMillis()) < this.duree)
        {
            MesuresAleatoire mesure = new MesuresAleatoire(this.utilisateur.getIdentifiant(),null,this.entrainementData.getId());
            this.senderData.send_mesure(mesure);
            System.out.println(mesure.toString());
            Thread.sleep(500);
        }
    }
}
