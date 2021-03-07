package ear.model;

import ear.entities.Utilisateur;
import ear.message.Consumer;
import ear.message.Sender;

public abstract class Entrainement implements EntrainementCoop,EntrainementSolo {

    protected ear.entities.Entrainement entrainementData;
    protected Sender senderData;
    protected Sender senderTopic;
    protected Consumer consumer;
    protected Utilisateur utilisateur;


    public Entrainement(ear.entities.Entrainement entrainementData, String queu, Utilisateur usr) {
        this.entrainementData = entrainementData;
        this.senderData = new Sender(queu);
        this.utilisateur = usr;
    }
}
