public abstract class MisthologikoStoixeio {

    private int id;
    private String description;

    public MisthologikoStoixeio(){}

    public MisthologikoStoixeio(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

    public int getId(){
        return id;
    }

    // Tha ylopoihthei stis klaseis apogonous:
    // ProsthetiApodoxi, TaktikiApodoxi, EktaktiApodoxi
    public abstract double getSyntelesths();

    @Override
    public String toString() {
        return ", Τίτλος: "+ description;
    }

    public abstract String getType();

}

