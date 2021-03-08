package ear.session;


import ear.entity.Activite_distance;
import ear.entity.Activite_duree;
import ear.entity.Activite_libre;
import ear.entity.Activite_series;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

import static ear.entity.Type_activite.QN.ALL_ACTIVITES_DUREE;
import static ear.entity.Type_activite.QN.ALL_ACTIVITES_DISTANCE;
import static ear.entity.Type_activite.QN.ALL_ACTIVITES_LIBRE;
import static ear.entity.Type_activite.QN.ALL_ACTIVITES_SERIES;

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

    @Override
    public List<Activite_distance> getAllActiviteDistance() {
        Query query = em.createNamedQuery(ALL_ACTIVITES_DISTANCE);
        List<Activite_distance> activites = query.getResultList();
        return  activites;
    }

    @Override
    public List<Activite_libre> getAllActiviteLibre() {
        Query query = em.createNamedQuery(ALL_ACTIVITES_LIBRE);
        List<Activite_libre> activites = query.getResultList();
        return activites;
    }

    @Override
    public List<Activite_series> getAllActiviteSeries() {
        Query query = em.createNamedQuery(ALL_ACTIVITES_SERIES);
        List<Activite_series> activites = query.getResultList();
        return activites;
    }
}
