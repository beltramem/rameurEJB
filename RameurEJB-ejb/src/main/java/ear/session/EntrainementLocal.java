package ear.session;

import ear.entity.Entrainement;
import ear.entity.Type_activite;
import ear.entity.Utilisateur;

import javax.ejb.Local;
import java.util.List;

@Local
public interface EntrainementLocal {
    public void CreationEntrainement(Type_activite type, int etat, List<Utilisateur> participants);
    public List<Entrainement> getEntrainementByUser(String identifiant);
    public List<Entrainement> getEntrainement();
    public List<Entrainement> getEntrainementByType(Type_activite type);
}
