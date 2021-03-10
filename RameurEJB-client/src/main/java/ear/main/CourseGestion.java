package ear.main;


import ear.entity.*;
import ear.model.CourseDistance;
import ear.model.CourseDuree;
import ear.model.CourseSeries;
import ear.ws.CourseRestfulClient;
import ear.ws.TypeActiviteRestfulClient;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;


public class CourseGestion {
    private TypeActiviteRestfulClient tarc;
    private CourseRestfulClient crc;

    public CourseGestion() {
        this.tarc = new TypeActiviteRestfulClient();
        this.crc = new CourseRestfulClient();
    }

    public void lancerCourse(ear.entity.Course coursedata, ear.model.Course race) throws IOException, TimeoutException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        boolean tourne=true;
        while (tourne)
        {
            coursedata = crc.getCourse(coursedata.getId());
            System.out.println("1: maj des participants");
            System.out.println("2: lancer la course");
            int lancer = sc.nextInt();
            switch (lancer)
            {
                case 2:{
                    coursedata = crc.getCourse(coursedata.getId());
                    if(coursedata.getParticipants().size()>1)
                    {
                        tourne=false;

                        crc.lancerCourse(coursedata.getId());
                        race.lancerCourseVShumain();
                    }
                    else
                    {
                        System.out.println("trop peu de participants");
                    }
                    break;
                }
            }
        }
    }

    public void creerCourseVShumain(Utilisateur usr) throws Exception {
        System.out.println("Choisir le type d'activite");
        System.out.println("1: Activite duree");
        System.out.println("2: Activite distance ");
        System.out.println("3: Activite nombre de series ");
        Scanner sc = new Scanner(System.in);
        String choix = sc.nextLine();
        String queu = usr.getIdentifiant();

        switch (choix) {
            case "1": {
                System.out.println("Choisir dans la liste :");
                List<Activite_duree> activites= tarc.getActiviteDuree();

                for (int i=0;i<activites.size();i++) {
                    System.out.println(i+1+":"+activites.get(i).getNom()+" description:"+activites.get(0).getDescription());
                }
                int subChoix = sc.nextInt();

                //System.out.println(usr.getIdentifiant());
                ear.entity.Course coursedata = crc.creationCourse(activites.get(subChoix-1).getId(),0, usr.getIdentifiant());
                String topic = "course."+coursedata+"."+usr.getIdentifiant();
                ear.model.Course race = new CourseDuree(coursedata,queu,topic,usr,activites.get(subChoix-1).getDuree());
                lancerCourse(coursedata,race);
                break;
            }
            case "2": {
                System.out.println("Choisir dans la liste :");
                List<Activite_distance> activites= tarc.getActiviteDistance();

                for (int i=0;i<activites.size();i++) {
                    System.out.println(i+1+":"+activites.get(i).getNom()+" description:"+activites.get(0).getDescription());
                }
                int subChoix = sc.nextInt();

                //System.out.println(usr.getIdentifiant());
                ear.entity.Course coursedata = crc.creationCourse(activites.get(subChoix-1).getId(),0, usr.getIdentifiant());
                String topic = "course."+coursedata+"."+usr.getIdentifiant();
                ear.model.Course race = new CourseDistance(coursedata,queu,topic,usr,activites.get(subChoix-1).getDistance());
                lancerCourse(coursedata,race);
                System.out.println("course fini");
                break;
            }
            case "3": {
                System.out.println("Choisir dans la liste :");
                List<Activite_series> activites= tarc.getActiviteSeries();

                for (int i=0;i<activites.size();i++) {
                    System.out.println(i+1+":"+activites.get(i).getNom()+" description:"+activites.get(0).getDescription());
                }
                int subChoix = sc.nextInt();

                //System.out.println(usr.getIdentifiant());
                ear.entity.Course coursedata = crc.creationCourse(activites.get(subChoix-1).getId(),0, usr.getIdentifiant());
                String topic = "course."+coursedata+"."+usr.getIdentifiant();
                ear.model.Course race = new CourseSeries(coursedata,queu,topic,usr,activites.get(subChoix-1).getNbSeries());
                lancerCourse(coursedata,race);
                break;
            }

        }
    }

    public void rejoindreCourse(Utilisateur u) throws Exception {

        System.out.println("taper le numéro de la course");
        Scanner sc = new Scanner(System.in);
        int choix = sc.nextInt();
        crc.rejoindreCourse(choix,u);
        while (crc.getCourse(choix).getEtat()!=1)
        {
            System.out.println("en attente du lancement");
        }
        Course coursedata = crc.getCourse(choix);
        String topic = "course."+coursedata+"."+u.getIdentifiant();
        String queu = u.getIdentifiant();


        Activite_distance existDistance = tarc.getActiviteDistance().stream()
                .filter(distance -> coursedata.getType_activite().getId()==distance.getId())
                .findAny()
                .orElse(null);

        Activite_duree existDuree = tarc.getActiviteDuree().stream()
                .filter(duree -> coursedata.getType_activite().getId()==duree.getId())
                .findAny()
                .orElse(null);

        Activite_series existSerie = tarc.getActiviteSeries().stream()
                .filter(serie -> coursedata.getType_activite().getId()==serie.getId())
                .findAny()
                .orElse(null);

        if(existDistance != null)
        {
            CourseDistance course = new CourseDistance(coursedata,queu,topic,u,existDistance.getDistance());
            course.lancerCourseVShumain();
        }else if(existDuree != null)
        {
            CourseDuree course = new CourseDuree(coursedata,queu,topic,u,existDuree.getDuree());
            course.lancerCourseVShumain();
        }else if(existSerie != null)
        {
            CourseSeries course = new CourseSeries(coursedata,queu,topic,u,existSerie.getNbSeries());
            course.lancerCourseVShumain();
        }





    }
}
