public class TaktikiApodoxi extends StoixeioErgasias {

    private double percentage;
    private int id;
    private static int idMaker;

    public TaktikiApodoxi(){
        super();
    }

    public TaktikiApodoxi(String description, double percentage){
        super(description);
        this.percentage = percentage;
        this.id = ++idMaker;
    }

    public TaktikiApodoxi(int id, String description, double percentage){
        super(description);
        this.percentage = percentage;
        this.id = id;
        idMaker = id;
    }

    @Override
    public double getSyntelesths() {
        return percentage;
    }

    @Override
    public int getId(){
        return id;
    }
    public String getType(){
        return "TA";
    }

    @Override
    public String toString() {
        return "(Τακτική Αποδοχή):" + " ID: "+id+ super.toString();
    }
}
