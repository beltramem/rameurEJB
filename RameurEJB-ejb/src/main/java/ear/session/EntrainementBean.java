package ear.session;

import ear.entity.Entrainement;
import ear.entity.Type_activite;

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


    @Override
    public void CreationEntrainement(Type_activite type, int etat) {

    }

    @Override
    public List<Entrainement> getEntrainementByUser(String identifiant) {
        return null;
    }

    @Override
    public List<Entrainement> getEntrainement() {
        return null;
    }

    @Override
    public List<Entrainement> getEntrainementByType(Type_activite type) {
        return null;
    }
}
