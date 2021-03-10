package ear.model;

import ear.entity.Mesure;
import ear.entity.Utilisateur;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class EntrainementLibre extends  Entrainement{

    public EntrainementLibre(ear.entity.Entrainement entrainementData, String queu, Utilisateur usr) {
        super(entrainementData,queu,usr);
    }
    @Override
    public void lancerEntrainementSolo() throws IOException, TimeoutException, InterruptedException {
        while (true) {

            Mesure mesure =this.rl.getMesure(this.utilisateur.getIdentifiant(),null,this.entrainementData.getId());
            this.senderQueuData.send_mesure(mesure);
            Thread.sleep(500);
        }
    }
}
