package ear.session;

import ear.entity.Entrainement;
import ear.entity.Type_activite;
import ear.entity.Utilisateur;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@Local(EntrainementLocal.class)
@Remote(EntrainementRemote.class)
public class EntrainementBean implements EntrainementLocal, EntrainementRemote {

    @PersistenceContext(unitName = "primary")
    private EntityManager em;



    public void CreationEntrainement(Type_activite type, int etat, List<Utilisateur> participants){
        Entrainement ent = new Entrainement(type,0,participants);
        em.persist(ent);
    }


    public List<Entrainement> getEntrainementByUser(String identifiant) {
        return null;
    }


    public List<Entrainement> getEntrainement() {
        return null;
    }


    public List<Entrainement> getEntrainementByType(Type_activite type) {
        return null;
    }
}
