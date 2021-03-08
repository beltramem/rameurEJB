package ear.session;

import ear.entity.Activite_distance;
import ear.entity.Activite_duree;
import ear.entity.Activite_libre;
import ear.entity.Activite_series;

import java.util.List;

public interface TypeActiviteLocal {

    public List<Activite_duree> getAllActiviteDuree();
    public List<Activite_distance> getAllActiviteDistance();
    public List<Activite_libre> getAllActiviteLibre();
    public List<Activite_series> getAllActiviteSeries();
}
