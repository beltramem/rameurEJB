package ear.main;

import ear.entities.Utilisateur;
import ear.ws.UtilisateurRestfulClient;

import javax.rmi.CORBA.Util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class GestionCompte {
    private UtilisateurRestfulClient utilisateurRestfulClient;

    public GestionCompte() {
        this.utilisateurRestfulClient = new UtilisateurRestfulClient();
    }


    public Utilisateur Connexion() throws Exception {
        Utilisateur utilisateur = new Utilisateur();
        do {


            Scanner sc = new Scanner(System.in);
            System.out.println("LogIn: ");
            String user = sc.nextLine();
            System.out.println("Mot de passe: ");
            String pass = sc.nextLine();

            try {
                utilisateur = utilisateurRestfulClient.connexion(user, pass);

            } catch (Exception e) {
                System.out.println(e);
            }
        }while (utilisateur.getIdentifiant()==null);
        return utilisateur;
    }

    public void CreationCompte() throws Exception {
        UtilisateurRestfulClient utilisateurRestfulClient = new UtilisateurRestfulClient();
        Scanner sc= new Scanner(System.in);
        System.out.println("Création d'un compte: \nIdentifiant: ");
        String user = sc.nextLine();
        System.out.println("Mot de passe: ");
        String pass = sc.nextLine();
        System.out.println("Nom: ");
        String name = sc.nextLine();
        System.out.println("prenom: ");
        String surname = sc.nextLine();
        System.out.println("Date de naissance (dd/MM/yyyy): ");
        String birthdate = sc.nextLine();
        Date date1= new Date();
        try {
            date1=new SimpleDateFormat("dd/MM/yyyy").parse(birthdate);
        }catch (Exception e){
            System.out.println(e);
        }
        boolean error = true;
        float weight=0;
        float height=0;
        while(error) {
            error = false;
            System.out.println("Poids: ");
            try{
                weight = sc.nextFloat();
                System.out.println("Taille: ");
                height = sc.nextFloat();
            }catch (Exception e){
                System.out.println(e);
                error = true;
            }
        }
        try {

                    this.utilisateurRestfulClient.creationCompte(user,pass,name,surname,weight,height,date1);
        }
        catch (Exception e)
        {
            throw new Exception(e);
        }

    }
}
