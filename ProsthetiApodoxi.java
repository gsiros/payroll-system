public class ProsthetiApodoxi extends MisthologikoStoixeio {

    private int id;
    private static int idMaker;

    public ProsthetiApodoxi(){
        super();
    }

    public ProsthetiApodoxi(String description){
        super(description);
        this.id = ++idMaker;
    }

    public ProsthetiApodoxi(int id, String description){
        super(description);
        this.id = id;
        idMaker = id;
    }

    @Override
    public String toString(){
        return "(Πρόσθετη Αποδοχή):" +" ID: " + id + super.toString();
    }

    @Override
    public int getId() {
        return id;
    }
    public String getType(){
        return "PA";
    }

    @Override
    public double getSyntelesths() {
        return 1;
    }
}
