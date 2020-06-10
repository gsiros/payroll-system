public class EktaktiApodoxi extends StoixeioErgasias {

    private double percentage;
    private int id;
    private static int idMaker;
    public EktaktiApodoxi(){
        super();
    }

    @Override
    public double getSyntelesths() {
        return percentage;
    }

    public EktaktiApodoxi(String description, double percentage){
        super(description);
        this.percentage = percentage;
        this.id = ++idMaker;
    }

    public EktaktiApodoxi(int id, String description, double percentage){
        super(description);
        this.percentage = percentage;
        this.id = id;
        idMaker = id;
    }

    @Override
    public int getId() {
        return id;
    }
    public String getType(){
        return "EA";
    }

    @Override
    public String toString(){
       return "(Εκτακτη Αποδοχή):" +" ID: " + id + super.toString();
    }
}
