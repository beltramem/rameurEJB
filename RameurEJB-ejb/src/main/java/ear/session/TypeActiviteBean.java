package ear.session;


import ear.entity.Activite_duree;
import ear.entity.Type_activite;
import ear.entity.Utilisateur;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

import static ear.entity.Type_activite.QN.ALL_ACTIVITES_DUREE;

@Stateless
@Local(TypeActiviteLocal.class)
@Remote(TypeActiviteRemote.class)
public class TypeActiviteBean implements TypeActiviteLocal,TypeActiviteRemote {

    @PersistenceContext(unitName = "primary")
    private EntityManager em;


    @Override
    public List<Activite_duree> getAllActiviteDuree() {
        Query query = em.createNamedQuery(ALL_ACTIVITES_DUREE);
        List<Activite_duree> activites = query.getResultList();
        return  activites;
    }
}
