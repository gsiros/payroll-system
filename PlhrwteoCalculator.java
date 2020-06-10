import java.util.ArrayList;

public class PlhrwteoCalculator {

    private ArrayList<Kinhsh> k;
    private double taktikesFOROS, ektaktesFOROS, prosthetesFOROS, Foros;


    public PlhrwteoCalculator(ArrayList<Kinhsh> k){
        this.k = k;
    }

    public double calcPlhrwteoFor(Employee target){
        double plhr = 0.0;
        double taktikes = 0.0;
        double ektaktes = 0.0;
        double prosthetes = 0.0;

        for(Kinhsh kin : k){
            if(kin.getTargetEmp().getID() == target.getID()){

                if(kin.getMisthStoixeio().getClass() == TaktikiApodoxi.class){
                    taktikes += target.getHmeromisthio()*kin.getDuration()*kin.getMisthStoixeio().getSyntelesths();
                } else if (kin.getMisthStoixeio().getClass() == EktaktiApodoxi.class){
                    ektaktes += target.getOromisthio()*kin.getDuration()*kin.getMisthStoixeio().getSyntelesths();
                } else {
                    prosthetes += kin.getAmount();
                }

            }
        }

        setTaktikesFOROS(taktikes*target.getPercentage());
        setEktaktesFOROS(ektaktes*target.getPercentage());
        setProsthetesFOROS(target.getPercentage()*prosthetes);
        taktikes -= getTaktikesFOROS();
        ektaktes -= getEktaktesFOROS();
        prosthetes -= getProsthetesFOROS();
        double forologhteoTAkaiEA = taktikes + ektaktes;
        double annualForologhteo = forologhteoTAkaiEA*14;
        double annualForos = forosCalculator(annualForologhteo);
        double forosTAkaiEA = annualForos/14;
        double forosPA = 0.2*prosthetes;
        Foros = forosPA + forologhteoTAkaiEA;
        return forologhteoTAkaiEA + prosthetes - forosTAkaiEA - forosPA;
    }

    private double forosCalculator(double amount){
        double foros;
        if(amount <=10000){
            foros = amount*0.1;
        } else if(amount <=30000) {
            foros = (amount - 10000)*0.2 + 1000;
        } else {
            foros = (amount - 30000)*0.3 + 20000*0.2 + 1000;
        }
        return foros;
    }

    private void setTaktikesFOROS(double amount){
        taktikesFOROS = amount;
    }

    private void setEktaktesFOROS(double amount){
        ektaktesFOROS = amount;
    }

    private void setProsthetesFOROS(double amount){
        prosthetesFOROS = amount;
    }

    private double getEktaktesFOROS() {
        return ektaktesFOROS;
    }

    private double getTaktikesFOROS() {
        return taktikesFOROS;
    }

    private double getProsthetesFOROS() {
        return prosthetesFOROS;
    }

    public double getAsfalistikes(){
        return getEktaktesFOROS()+getProsthetesFOROS()+getTaktikesFOROS();
    }

    public double getForos(){
        return Foros;
    }
}
