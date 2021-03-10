package ear.session;

import ear.entity.Course;
import ear.entity.Type_activite;
import ear.entity.Utilisateur;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CourseLocal {
    public Course CreationCourse(int type, int etat, String participant);
    public List<Course> getCourseByUser(String identifiant);
    public List<Course> getCourse();
    public List<Course> getCourseByType(Type_activite type);
    public Course getCourseById(int id);
    public void rejoindreCourse(Course race, Utilisateur usr);
    public void lancerCourse(int id);
}
