package ear.main;

import ear.entities.Utilisateur;
import ear.ws.UtilisateurRestfulClient;

import javax.rmi.CORBA.Util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class GestionCompte {

    public GestionCompte() {
    }

    public Utilisateur Connexion() throws Exception {
        Utilisateur utilisateur = new Utilisateur();
        do {


            Scanner sc = new Scanner(System.in);
            System.out.println("LogIn: ");
            String user = sc.nextLine();
            System.out.println("Mot de passe: ");
            String pass = sc.nextLine();
            UtilisateurRestfulClient utilisateurRestfulClient = new UtilisateurRestfulClient();
            try {
                utilisateur = utilisateurRestfulClient.connexion(user, pass);

            } catch (Exception e) {
                System.out.println(e);
            }
        }while (utilisateur.getIdentifiant()==null);
        return utilisateur;
    }

    public Utilisateur CreationCompte() throws Exception {
        Scanner sc= new Scanner(System.in);
        System.out.println("Création d'un compte: \nIdentifiant: ");
        String user = sc.nextLine();
        System.out.println("Mot de passe: ");
        String pass = sc.nextLine();
        System.out.println("Nom: ");
        String name = sc.nextLine();
        System.out.println("Date de naissance (dd/MM/yyyy): ");
        String birthdate = sc.nextLine();
        try {
            Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(birthdate);
        }catch (Exception e){
            System.out.println(e);
        }
        boolean error = true;
        while(error) {
            error = false;
            System.out.println("Poids: ");
            try{
                double weight = sc.nextDouble();
                System.out.println("Taille: ");
                double height = sc.nextDouble();
            }catch (Exception e){
                System.out.println(e);
                error = true;
            }


        }
        return  new Utilisateur();
    }
}
