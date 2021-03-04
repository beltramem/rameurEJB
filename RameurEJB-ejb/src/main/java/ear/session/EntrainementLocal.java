package ear.session;

import ear.entity.Entrainement;
import ear.entity.Type_activite;
import ear.entity.Utilisateur;

import javax.ejb.Local;
import java.util.List;

@Local
public interface EntrainementLocal {
    public Entrainement CreationEntrainement(int type, int etat);
    public List<Entrainement> getEntrainementByUser(String identifiant);
    public List<Entrainement> getEntrainement();
    public List<Entrainement> getEntrainementByType(Type_activite type);
    public void ajouterParticipant(String identifiant, int entrainement_id);
}
