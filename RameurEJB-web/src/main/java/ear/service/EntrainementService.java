package ear.service;

import ear.entity.Entrainement;
import ear.entity.Type_activite;
import ear.entity.Utilisateur;
import ear.session.EntrainementLocal;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.awt.*;
import java.util.List;

@Stateless
@Path("/EntrainementService")
public class EntrainementService {

    @EJB
    private EntrainementLocal ent;

    @POST
    @Path("/creationEntrainement")
    @Produces(MediaType.APPLICATION_JSON)
    public void creationEntrainement(
            @PathParam(value="type_activite")int type_activite,
            @PathParam(value="etat")int etat,
            @PathParam(value="participant")String participant)
    {
        Entrainement e = ent.CreationEntrainement(type_activite,etat);
        ent.ajouterParticipant(participant,e.getId());
    }
}
