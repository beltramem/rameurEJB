package ear.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import ear.entities.Utilisateur;
import ear.session.UtilisateurLocal;

import java.util.Date;

@Stateless
@Path("/UtilisateurService")
public class UtilisateurService {
    @EJB
    private  UtilisateurLocal user;


    @POST
    @Path("/creationCompte/{identifiant}/{mdp}/{nom}/{prenom}/{taille}/{poids}/{date_naissance}")
    @Produces(MediaType.APPLICATION_JSON)
    public void creationCompte (
            @PathParam(value="identifiant") String identifiant,
            @PathParam(value="mdp") String mdp,
            @PathParam(value="nom")String nom,
            @PathParam(value="prenom")String prenom,
            @PathParam(value="taille")float taille,
            @PathParam(value="poids")float poids,
            @PathParam(value="date_naissance")Date date_naissance) throws Exception {
        user.creationCompte(identifiant,mdp,nom,prenom,taille,poids,date_naissance);
    }

    @POST
    @Path("/connexion/{identifiant}/{mdp}")
    @Produces(MediaType.APPLICATION_JSON)
    public Utilisateur connexion(@PathParam(value="identifiant") String identifiant, @PathParam(value="mdp") String mdp) throws Exception {
        return user.connexion(identifiant,mdp);
    }
    /*
    @GET
    @Path("/updateCompte/{utilisateur}")
    @Produces(MediaType.APPLICATION_JSON)
    public void updateCompte(@PathParam(value="utilisateur") Utilisateur utilisateur) throws Exception
    {
        user.updateCompte(utilisateur);
    }
    */
}
