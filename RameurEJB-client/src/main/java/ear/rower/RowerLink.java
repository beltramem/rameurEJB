package ear.rower;

import ear.entity.Mesure;
import javarow.*;

import java.time.Duration;
import java.util.Date;

import static javarow.ShortCommand.*;

public class RowerLink {
    Rower r;
    Command command;
    static int rafraichissement =500;
    private Integer puissanceTmp;
    int coups;
    double distanceTmp;

    public static double vitesse(double dist, double temps)
    {
        System.out.println("dist"+dist);
        double vit;
        dist = dist/10;
        vit = dist/ (temps / 1000);
        return vit;
    }

    public RowerLink()
    {
        this.r = Rower.getInstance();
        this.puissanceTmp=0;
        this.coups=0;
        this.distanceTmp=0;
        r.getMonitor();
        r.getSerialId();
        this.command = new Command();
        command.addCommand(ShortSpecificPMCommand.CSAFE_PM_GET_WORKDISTANCE);
        command.addCommand(CSAFE_GETCALORIES_CMD);
        command.addCommand(CSAFE_GETPOWER_CMD);
        command.addCommand(CSAFE_GETHRCUR_CMD);

    }


    public void goToMenu(){
        r.goToMenuScreen();
        this.command.addCommand(CSAFE_RESET_CMD);
        r.sendCommand(this.command);
    }
    public void setWorkoutDistance(int dist){
        r.setWorkoutDistance(dist);
    }
    public void lancerEntrainementDuree(int duree)
    {
        r.setWorkoutTime(Duration.ofMinutes(duree));

    }


    public Mesure getMesure(String usr,Integer id_course, Integer id_entrainement)
    {
        UsbResponse response = r.sendCommand(this.command);
        Response.CSAFE_PM_GET_WORKDISTANCE dist = (Response.CSAFE_PM_GET_WORKDISTANCE) response.specificPMResponses.get(Response.SPECIFIC_PM_CODE.CSAFE_PM_GET_WORKDISTANCE);
        Response.CSAFE_GETCALORIES_CMD cal = (Response.CSAFE_GETCALORIES_CMD) response.csafeResponses.get(Response.CODE.CSAFE_GETCALORIES_CMD);
        Response.CSAFE_GETPOWER_CMD puis = (Response.CSAFE_GETPOWER_CMD) response.csafeResponses.get(Response.CODE.CSAFE_GETPOWER_CMD);
        Response.CSAFE_GETHRCUR_CMD freq = (Response.CSAFE_GETHRCUR_CMD) response.csafeResponses.get(Response.CODE.CSAFE_GETHRCUR_CMD);

        double distanceTotale=((double)dist.workDistanceDecimeter)/10;
        distanceTotale+=((double)(dist.remainingFractionalDistanceDecimeter)/10);
        double calories=cal.calories;

        Integer puissance;
        if(puis.strokeWatts==puissanceTmp){
            puissance=null;
        }
        else {
            puissance = puis.strokeWatts;
            puissanceTmp = puissance;
            this.coups++;
        }
        //puissance = (double) puis.strokeWatts;
        distanceTmp=distanceTotale-distanceTmp;
        Integer freqCardiaque=null;
        if (freq.beatsPerMinute!=0)
        {
            freqCardiaque=freq.beatsPerMinute;
        }
        double vitesse1=vitesse(distanceTmp, rafraichissement);

        distanceTmp=distanceTotale;


        System.out.println("usr:"+usr+"date:"+new Date()+"vitesse:"+vitesse1+"dist:"+distanceTotale+"calo:"+calories+"puissance:"+puissance+"freq:"+freqCardiaque+"idcourse:"+null+"identrainement:"+id_entrainement+"nbcoups:"+coups);
        Mesure m = new Mesure(usr,new Date(),vitesse1,distanceTotale,calories,puissance,freqCardiaque,id_course,id_entrainement,coups);
        //System.out.println(m.toString());

        //System.out.println("Coups de ram: "+coups);

        return m;
    }
}
