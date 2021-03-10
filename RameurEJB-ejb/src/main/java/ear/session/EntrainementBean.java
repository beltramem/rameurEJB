package ear.session;

import ear.entity.Entrainement;
import ear.entity.Participe_entrainement;
import ear.entity.Type_activite;
import ear.entity.Utilisateur;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

import static ear.entity.Type_activite.QN.PAR_ID;
import static ear.entity.Utilisateur.QN.FIND_COMPTE;

@Stateless
@Local(EntrainementLocal.class)
@Remote(EntrainementRemote.class)
public class EntrainementBean implements EntrainementLocal, EntrainementRemote {

    @PersistenceContext(unitName = "primary")
    private EntityManager em;



    public Entrainement CreationEntrainement(int type_id, int etat,String participant){




        //System.out.println(type_id);
        Query query = em.createNamedQuery(PAR_ID);

        query.setParameter("ID",type_id);
        System.out.println(type_id);
        List<Type_activite> activite = query.getResultList();
        System.out.println(activite.size());

        Query query2 = em.createNamedQuery(FIND_COMPTE);
        query2.setParameter("ID",participant);
        List<Utilisateur> participants = query2.getResultList();


        Entrainement ent = new Entrainement(activite.get(0),0,participants);
        Participe_entrainement pe = new Participe_entrainement(ent,participants.get(0), etat);
        em.persist(ent);
        em.persist(pe);
        return ent;
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
