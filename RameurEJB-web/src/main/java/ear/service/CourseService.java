package ear.service;


import ear.entity.Course;
import ear.message.Consumer;
import ear.session.CourseLocal;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("/CourseService")
public class CourseService {

    @EJB
    private CourseLocal race;

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
}
