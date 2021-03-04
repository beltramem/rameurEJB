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
import javax.persistence.Query;
import java.util.List;

import static ear.entity.Entrainement.QN.FIND_ENTRAINEMENT;
import static ear.entity.Type_activite.QN.PAR_ID;
import static ear.entity.Utilisateur.QN.FIND_COMPTE;

@Stateless
@Local(EntrainementLocal.class)
@Remote(EntrainementRemote.class)
public class EntrainementBean implements EntrainementLocal, EntrainementRemote {

    @PersistenceContext(unitName = "primary")
    private EntityManager em;



    public Entrainement CreationEntrainement(int type_id, int etat){
        Query query = em.createNamedQuery(PAR_ID);
        query.setParameter("ID",type_id);
        List<Type_activite> activite = query.getResultList();
        System.out.println(activite.size());
        Entrainement ent = new Entrainement(activite.get(0),0);
        em.persist(ent);
        return ent;
    }

    public void ajouterParticipant(String identifiant, int entrainement_id)
    {
        Query query = em.createNamedQuery(FIND_ENTRAINEMENT);
        query.setParameter("IO",entrainement_id);
        List<Entrainement> entrainements = query.getResultList();

        Query query2 = em.createNamedQuery(FIND_COMPTE);
        query2.setParameter("IO",identifiant);
        List<Utilisateur> utilisateur = query2.getResultList();

        entrainements.get(0).add_participant(utilisateur.get(0));

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
