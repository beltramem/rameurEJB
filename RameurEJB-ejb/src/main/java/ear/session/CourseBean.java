package ear.session;

import ear.entity.Course;
import ear.entity.Type_activite;
import ear.entity.Utilisateur;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

import static ear.entity.Course.QN.FIND_COURSE;
import static ear.entity.Type_activite.QN.PAR_ID;
import static ear.entity.Utilisateur.QN.FIND_COMPTE;

@Stateless
@Local(CourseLocal.class)
@Remote(CourseRemote.class)
public class CourseBean implements CourseLocal, CourseRemote {

    @PersistenceContext(unitName = "primary")
    private EntityManager em;



    public Course CreationCourse(int type_id, int etat, String participant){




        Query query = em.createNamedQuery(PAR_ID);

        query.setParameter("ID",type_id);
        System.out.println(type_id);
        List<Type_activite> activite = query.getResultList();
        System.out.println(activite.size());

        Query query2 = em.createNamedQuery(FIND_COMPTE);
        query2.setParameter("ID",participant);
        List<Utilisateur> participants = query2.getResultList();


        Course race = new Course(activite.get(0),0,participants);
        em.persist(race);
        return race;
    }



    public List<Course> getCourseByUser(String identifiant) {
        return null;
    }

    @Override
    public List<Course> getCourse() {
        return null;
    }

    @Override
    public List<Course> getCourseByType(Type_activite type) {
        return null;
    }

    @Override
    public Course getCourseById(int id) {
        Course race = null;
        Query query = em.createNamedQuery(FIND_COURSE);
        query.setParameter("ID",id);
        List<Course> courses = query.getResultList();

        if (courses.size()>0) {
         race=courses.get(0);
        }
        return race;
    }

    @Override
    public void lancerCourse(int id) {
        Query query = em.createNamedQuery(FIND_COURSE);
        query.setParameter("ID",id);
        List<Course> courses = query.getResultList();

        if (courses.size()>0) {
            courses.get(0).setEtat(1);
        }
    }


}
