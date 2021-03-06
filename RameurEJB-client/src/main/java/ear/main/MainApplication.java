package ear.main;

import ear.entities.Entrainement;
import ear.entities.Utilisateur;
import ear.ws.EntrainementRestfulClient;
import ear.ws.UtilisateurRestfulClient;

import java.util.Date;
import java.util.Scanner;

public class MainApplication {



    public static void main(String[] args) throws Exception {
        GestionCompte c= new GestionCompte();
        boolean tmp=true;
        while(tmp)
        {
            tmp = false;
            System.out.println("Si vous avez déjà un compte, tapez '1'. \n Si vous souhaitez créer un compte tapez '2':");
            Scanner sc= new Scanner(System.in);
            String choix = sc.nextLine();
            System.out.println(choix);
            if(choix.equals("1"))
            {
                Utilisateur usr = c.Connexion();
                System.out.println(usr.getIdentifiant());

            }
            else if (choix.equals("2")){
                c.CreationCompte();
                System.out.println("création réussit");
            }
            else{
                tmp = true;
            }
        }

        /*EntrainementRestfulClient entr = new EntrainementRestfulClient();
        Entrainement ent = entr.creationEntrainement(1,0,"betramem");
        System.out.println(ent.toString());*/

        System.out.println("Menu principal:");
        System.out.println("1 : s'entrainer");
        System.out.println("2 : participer à une course");

        Scanner sc= new Scanner(System.in);
        String choix = sc.nextLine();
        switch (choix)
        {
            case "1":

        }
    }



}