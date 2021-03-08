package ear.session;

import ear.entity.Course;
import ear.entity.Type_activite;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CourseLocal {
    public Course CreationCourse(int type, int etat, String participant);
    public List<Course> getCourseByUser(String identifiant);
    public List<Course> getCourse();
    public List<Course> getCourseByType(Type_activite type);
}
