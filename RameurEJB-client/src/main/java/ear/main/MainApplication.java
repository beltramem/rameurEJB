package ear.main;

import ear.entity.Utilisateur;
import java.util.Scanner;

public class MainApplication {



    public static void main(String[] args) throws Exception {
        GestionCompte c= new GestionCompte();
        Utilisateur usr = null;
        while(usr==null)
        {
            System.out.println("Si vous avez déjà un compte, tapez '1'. \n Si vous souhaitez créer un compte tapez '2':");
            Scanner sc= new Scanner(System.in);
            String choix = sc.nextLine();
            System.out.println(choix);
            if(choix.equals("1"))
            {
                usr = c.Connexion();
                System.out.println(usr.getIdentifiant());

            }
            else if (choix.equals("2")){
                c.CreationCompte();
                System.out.println("création réussit");
            }
        }


        System.out.println("Menu principal:");
        System.out.println("1 : s'entrainer");
        System.out.println("2 : participer à une course");

        Scanner sc= new Scanner(System.in);
        String choix = sc.nextLine();
        switch (choix)
        {
            case "1": {

                EntrainementGestion eg = new EntrainementGestion();
                System.out.println("1: créer un entrainement solo");
                System.out.println("2: créer un entrainement coop");
                System.out.println("3: rejoindre un entrainement coop");
                String subChoix = sc.nextLine();
                switch (subChoix) {
                    case "1":
                        eg.creerEntrainementSolo(usr);
                        break;
                }
                break;

            }

            case "2":{
                CourseGestion cg = new CourseGestion();
                System.out.println("1: Faire course contre IA");
                System.out.println("2: creer course contre joueur");
                System.out.println("3: rejoindre course contre joueur");
                String subChoix = sc.nextLine();
                switch (subChoix) {
                    case "1":
                        //cg.creerCourseIA(usr);
                        break;
                    case "2":
                        cg.creerCourseVShumain(usr);
                        break;
                    case "3":
                        cg.rejoindreCourse(usr);
                        break;
                }
                break;
            }
        }
    }



}