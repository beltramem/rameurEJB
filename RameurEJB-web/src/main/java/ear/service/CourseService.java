package ear.service;


import ear.entity.Course;
import ear.entity.Utilisateur;
import ear.message.Consumer;
import ear.session.CourseLocal;
import ear.session.UtilisateurLocal;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("/CourseService")
public class CourseService {

    @EJB
    private CourseLocal race;
    @EJB
    private UtilisateurLocal ul;

    @GET
    @Path("/creationCourse/{type_activite}/{etat}/{participant}")
    @Produces(MediaType.APPLICATION_JSON)
    public Course creationCourse(
            @PathParam(value="type_activite")int type_activite,
            @PathParam(value="etat")int etat,
            @PathParam(value="participant")String participant) throws Exception {
        try {
            Course c = race.CreationCourse(type_activite, etat, participant);
            Consumer consume = new Consumer(participant);
            consume.getMessage();
            return c;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @PUT
    @Path("/rejoindreCourse/{id_course}/{id_utilisateur}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response rejoidre(@PathParam("id_course") int id_course,
                             Utilisateur utilisateur) throws Exception {

        Utilisateur customer = ul.connexion(utilisateur.getIdentifiant(),utilisateur.getMdp());
        if (customer == null) {
            throw new WebApplicationException("Can't find it", 404);
        }

        Course course = race.getCourseById(id_course);
        if (course == null) {
            throw new WebApplicationException("Can't find it", 404);
        }

        course.addParticipant(utilisateur);
        return Response.noContent().build();
    }
}


