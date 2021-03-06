package ear.service;

import ear.entity.Entrainement;
import ear.entity.Type_activite;
import ear.entity.Utilisateur;
import ear.session.EntrainementLocal;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.awt.*;
import java.util.List;

@Stateless
@Path("/EntrainementService")
public class EntrainementService {

    @EJB
    private EntrainementLocal ent;

    @GET
    @Path("/creationEntrainement/{type_activite}/{etat}/{participant}")
    @Produces(MediaType.APPLICATION_JSON)
    public Entrainement creationEntrainement(
            @PathParam(value="type_activite")int type_activite,
            @PathParam(value="etat")int etat,
            @PathParam(value="participant")String participant) throws Exception {
        System.out.println("MA GROSSE BITE");
        try
        {
            Entrainement e =ent.CreationEntrainement(type_activite,etat,participant);
            return e;
        }catch(Exception e)
        {
            throw new Exception(e);
        }




    }
}
