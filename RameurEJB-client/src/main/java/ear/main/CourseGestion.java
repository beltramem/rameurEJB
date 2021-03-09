package ear.main;

import com.sun.deploy.ui.AboutDialog;
import ear.entity.Activite_distance;
import ear.entity.Activite_duree;
import ear.entity.Utilisateur;
import ear.model.CourseDistance;
import ear.ws.CourseRestfulClient;
import ear.ws.TypeActiviteRestfulClient;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;


public class CourseGestion {
    private TypeActiviteRestfulClient tarc;
    private CourseRestfulClient crc;

    public CourseGestion() {
        this.tarc = new TypeActiviteRestfulClient();
        this.crc = new CourseRestfulClient();
    }

    public void creerCourseVShumain(Utilisateur usr) throws Exception {
        System.out.println("Choisir le type d'activite");
        System.out.println("1: Activite duree");
        System.out.println("2: Activite distance ");
        System.out.println("3: Activite libre ");
        System.out.println("4: Activite nombre de series ");
        Scanner sc = new Scanner(System.in);
        String choix = sc.nextLine();
        String queu = usr.getIdentifiant();

        switch (choix) {
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
                boolean tourne=true;
                while (tourne)
                {
                    for( Utilisateur u : coursedata.getParticipants() ) {
                        System.out.println(u.getIdentifiant());
                    }
                    System.out.println("1: maj des participants");
                    System.out.println("2: lancer la course");
                    int lancer = sc.nextInt();
                    switch (lancer)
                    {
                        case 2:{
                            if(coursedata.getParticipants().size()>1)
                            {
                                tourne=false;
                                race.lancerCourseVShumain();
                            }
                            else
                            {
                                System.out.println("trop peu de participants");
                            }
                        }
                    }
                }
            }
        }
    }

    public void rejoindreCourse(Utilisateur u) throws IOException {
        System.out.println("taper le numéro de la course");
        Scanner sc = new Scanner(System.in);
        int choix = sc.nextInt();
        crc.rejoindreCourse(choix,u);

    }
}
