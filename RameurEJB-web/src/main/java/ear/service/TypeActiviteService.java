package ear.service;


import ear.entity.Activite_distance;
import ear.entity.Activite_duree;
import ear.entity.Activite_libre;
import ear.entity.Activite_series;
import ear.session.TypeActiviteLocal;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Stateless
@Path("/TypeActiviteService")
public class TypeActiviteService {

    @EJB
    private TypeActiviteLocal tal;

    @GET
    @Path("/getActiviteDuree")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Activite_duree> getActiviteDuree() throws Exception {
        try {
            List<Activite_duree> activites = tal.getAllActiviteDuree();
            return activites;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @GET
    @Path("/getActiviteDistance")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Activite_distance> getActiviteDistance() throws Exception {
        try
        {
            List<Activite_distance> activites =tal.getAllActiviteDistance();
            return activites;
        }catch(Exception e)
        {
            throw new Exception(e);
        }
    }

    @GET
    @Path("/getActiviteLibre")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Activite_libre> getActiviteLibre() throws Exception {
        try
        {
            List<Activite_libre> activites =tal.getAllActiviteLibre();
            return activites;
        }catch(Exception e)
        {
            throw new Exception(e);
        }
    }

    @GET
    @Path("/getActiviteSeries")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Activite_series> getActiviteSeries() throws Exception {
        try
        {
            List<Activite_series> activites =tal.getAllActiviteSeries();
            return activites;
        }catch(Exception e)
        {
            throw new Exception(e);
        }
    }

}
