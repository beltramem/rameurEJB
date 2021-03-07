package ear.main;

import ear.entities.Activite_duree;
import ear.entities.Utilisateur;
import ear.model.Entrainement;
import ear.model.EntrainementDuree;
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
        Scanner sc= new Scanner(System.in);
        String choix = sc.nextLine();
        switch (choix)
        {
            case "1":
                System.out.println("Choisir dans la liste :");
                List<Activite_duree> activites= tarc.getActiviteDuree();

                for (int i=0;i<activites.size();i++) {
                    System.out.println(i+1+":"+activites.get(i).getNom()+" description:"+activites.get(0).getDescription());
                }
                int subChoix = sc.nextInt();

                ear.entities.Entrainement entdata = erc.creationEntrainement(subChoix,0, usr.getIdentifiant());
                String queu = "Entrainement_"+usr.getIdentifiant();
                Entrainement ent = new EntrainementDuree(entdata,queu,usr,activites.get(subChoix-1).getDuree());
                ent.lancerEntrainementSolo();


        }


    }
}
