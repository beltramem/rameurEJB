package ear.service;


import ear.entity.Activite_duree;
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
        try
        {
            List<Activite_duree> activites =tal.getAllActiviteDuree();
            return activites;
        }catch(Exception e)
        {
            throw new Exception(e);
        }
    }

}
