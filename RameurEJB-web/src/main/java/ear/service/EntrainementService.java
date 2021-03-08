package ear.service;

import ear.entity.Entrainement;
import ear.message.Consumer;
import ear.session.EntrainementLocal;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

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
        try
        {
            Entrainement e =ent.CreationEntrainement(type_activite,etat,participant);
            Consumer consume = new Consumer(participant);
            consume.getMessage();
            return e;
        }catch(Exception e)
        {
            throw new Exception(e);
        }




    }
}
