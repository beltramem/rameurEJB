package ear.session;

import ear.entity.Mesure;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Local(MesureLocal.class)
@Remote(MesureRemote.class)
public class MesureBean implements MesureLocal,MesureRemote {

    @PersistenceContext(unitName = "primary")
    private EntityManager em;


    @Override
    public void addMesure(Mesure mesure) {
        System.out.println("COUCOU JE SUIS LA");
        em.persist(mesure);
    }
}
