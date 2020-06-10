public class Employee {

    private String name;
    private String surname;
    private double salary;
    private double percentage;
    private int id;
    private static int idMaker;


    public Employee(String name, String surname, double salary, double percentage){
        this.name = name;
        this.surname = surname;
        this.salary = salary;
        this.percentage = percentage;
        this.id = ++idMaker;
    }

    public Employee(int id, String name, String surname, double salary, double percentage){
        this.name = name;
        this.surname = surname;
        this.salary = salary;
        this.percentage = percentage;
        this.id = id;
        idMaker = id;
    }

    public int getID(){
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public double getSalary() {
        return salary;
    }

    public double getPercentage() {
        return percentage;
    }

    public double getHmeromisthio(){
        return salary/25;
    }

    public double getOromisthio(){
        return getHmeromisthio()/8;
    }

    @Override
    public String toString() {
        return  "ID: "+id +", Όνομα: " + name +
                ", Επώνυμο: " + surname + 
                ", Μισθός: " + salary +
                ", Ποσοστό Ασφαλιστικής Εισφοράς: " + percentage;
    }
    
}

