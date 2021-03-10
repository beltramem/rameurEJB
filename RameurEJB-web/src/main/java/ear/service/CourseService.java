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

    @GET
    @Path("/getCourse/{id_course}")
    @Produces(MediaType.APPLICATION_JSON)
    public Course getCourse(
            @PathParam(value="id_course")int id_course) throws Exception {
        try {
            Course c = race.getCourseById(id_course);
            return c;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @PUT
    @Path("/rejoindreCourse/{id_course}/{id_utilisateur}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response rejoindreCourse(@PathParam("id_course") int id_course,
                             @PathParam("id_utilisateur") String id_utilisateur) throws Exception {


        Utilisateur utilisateur = ul.getUtilisateur(id_utilisateur);
        if (utilisateur == null) {
            throw new WebApplicationException("Can't find it", 404);
        }

        Course course = race.getCourseById(id_course);
        if (course == null) {
            throw new WebApplicationException("Can't find it", 404);
        }

        race.getCourseById(id_course).addParticipant(utilisateur);
        Consumer consume = new Consumer(id_utilisateur);
        consume.getMessage();
        return Response.noContent().build();
    }

    @PUT
    @Path("/lancerCourse/{id_course}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response lancerCourse(@PathParam("id_course") int id_course) throws Exception {


        Course course = race.getCourseById(id_course);
        if (course == null) {
            throw new WebApplicationException("Can't find it", 404);
        }

        race.lancerCourse(id_course);
        return Response.noContent().build();
    }


}


