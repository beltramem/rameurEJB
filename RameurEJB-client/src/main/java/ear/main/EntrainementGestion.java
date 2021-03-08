package ear.main;

import ear.entity.*;
import ear.entity.Entrainement;
import ear.model.*;
import ear.ws.EntrainementRestfulClient;
import ear.ws.TypeActiviteRestfulClient;

import java.util.List;
import java.util.Scanner;

public class EntrainementGestion {
    private TypeActiviteRestfulClient tarc;
    private EntrainementRestfulClient erc;

    public EntrainementGestion() {
        this.tarc = new TypeActiviteRestfulClient();
        this.erc = new EntrainementRestfulClient();
    }

    public void creerEntrainementSolo(Utilisateur usr) throws Exception {
        System.out.println("Choisir le type d'activite");
        System.out.println("1: Activite duree");
        System.out.println("2: Activite distance ");
        System.out.println("3: Activite libre ");
        System.out.println("4: Activite nombre de series ");
        Scanner sc= new Scanner(System.in);
        String choix = sc.nextLine();
        switch (choix)
        {
            case "1":{
                System.out.println("Choisir dans la liste :");
                List<Activite_duree> activites= tarc.getActiviteDuree();

                for (int i=0;i<activites.size();i++) {
                    System.out.println(i+1+":"+activites.get(i).getNom()+" description:"+activites.get(0).getDescription());
                }
                int subChoix = sc.nextInt();

                ear.entity.Entrainement entdata = erc.creationEntrainement(activites.get(subChoix-1).getId(),0, usr.getIdentifiant());
                String queu = usr.getIdentifiant();
                ear.model.Entrainement ent = new EntrainementDuree(entdata,queu,usr,activites.get(subChoix-1).getDuree());
                ent.lancerEntrainementSolo();
                break;
            }
            case "2": {
                System.out.println("Choisir dans la liste :");
                List<Activite_distance> activites = tarc.getActiviteDistance();

                for (int i = 0; i < activites.size(); i++) {
                    System.out.println(i + 1 + ":" + activites.get(i).getNom() + " description:" + activites.get(0).getDescription());
                }
                int subChoix = sc.nextInt();

                ear.entity.Entrainement entdata = erc.creationEntrainement(activites.get(subChoix-1).getId(), 0, usr.getIdentifiant());
                String queu = usr.getIdentifiant();
                ear.model.Entrainement ent = new EntrainementDistance(entdata, queu, usr, activites.get(subChoix - 1).getDistance());
                ent.lancerEntrainementSolo();
                break;
            }
            case "3": {
                System.out.println("Choisir dans la liste :");
                List<Activite_libre> activites = tarc.getActiviteLibre();

                for (int i = 0; i < activites.size(); i++) {
                    System.out.println(i + 1 + ":" + activites.get(i).getNom() + " description:" + activites.get(0).getDescription());
                }
                int subChoix = sc.nextInt();

                ear.entity.Entrainement entdata = erc.creationEntrainement(activites.get(subChoix-1).getId(), 0, usr.getIdentifiant());
                String queu = usr.getIdentifiant();
                ear.model.Entrainement ent = new EntrainementLibre(entdata, queu, usr);
                ent.lancerEntrainementSolo();
                break;
            }
            case "4": {
                System.out.println("Choisir dans la liste :");
                List<Activite_series> activites = tarc.getActiviteSeries();

                for (int i = 0; i < activites.size(); i++) {
                    System.out.println(i + 1 + ":" + activites.get(i).getNom() + " description:" + activites.get(0).getDescription());
                }
                int subChoix = sc.nextInt();

                ear.entity.Entrainement entdata = erc.creationEntrainement(activites.get(subChoix-1).getId(), 0, usr.getIdentifiant());
                String queu = usr.getIdentifiant();
                System.out.println(activites.get(0).toString());
                ear.model.Entrainement ent = new EntrainementSeries(entdata, queu, usr,activites.get(subChoix - 1).getNbSeries());
                ent.lancerEntrainementSolo();
                break;
            }
        }


    }
}
