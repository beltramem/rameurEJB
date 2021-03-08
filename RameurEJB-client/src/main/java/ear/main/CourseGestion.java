package ear.main;

import ear.entity.Activite_duree;
import ear.entity.Utilisateur;
import ear.model.CourseDistance;
import ear.ws.CourseRestfulClient;
import ear.ws.TypeActiviteRestfulClient;

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
        switch (choix) {
            case "1": {
                System.out.println("Choisir dans la liste :");
                List<Activite_duree> activites= tarc.getActiviteDuree();

                for (int i=0;i<activites.size();i++) {
                    System.out.println(i+1+":"+activites.get(i).getNom()+" description:"+activites.get(0).getDescription());
                }
                int subChoix = sc.nextInt();

                ear.entity.Course coursedata = crc.creationCourse(activites.get(subChoix-1).getId(),0, usr.getIdentifiant());
                String queu = usr.getIdentifiant();
                String topic = "course."+coursedata+"."+usr.getIdentifiant();
                ear.model.Course race = new CourseDistance(coursedata,queu,topic,usr,activites.get(subChoix-1).getDuree());
                race.lancerCourseVShumain();
                break;
            }
        }
    }
}
