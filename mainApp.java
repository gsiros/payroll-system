import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class mainApp {

    public static void main (String[] args){
        // Load the data:
        System.out.print("Φόρτωση μισθολογικών στοιχείων από βάση δεδομένων... ");
        DatabaseReader dbr = new DatabaseReader();
        ArrayList<MisthologikoStoixeio> stoixeia = dbr.loadStoixeiaDB("stoixeiaFINAL.txt");
        System.out.print("ΕΠΙΤΥΧΙΑ!\n");
        System.out.print("Φόρτωση εργαζομένων και λογιστικών κινήσεων από βάση δεδομένων... ");
        ArrayList<Employee> ergazColl = new ArrayList<>();
        ArrayList<Kinhsh> kinColl = new ArrayList<>();
        dbr.loadEmployeeData("employeesFINAL.txt",ergazColl, kinColl, stoixeia);
        System.out.print("ΕΠΙΤΥΧΙΑ!\n");
        /*ArrayList<MisthologikoStoixeio> stoixeia = new ArrayList<>();
        stoixeia.add(new TaktikiApodoxi( "Ημέρες Εργασίας", 1));
        stoixeia.add(new TaktikiApodoxi("Ημέρες Άδειας", 0.02));
        stoixeia.add(new TaktikiApodoxi("Ημέρες Ασθένειας", 0.5));
        stoixeia.add(new EktaktiApodoxi("Υπερωρίες", 1.7));
        stoixeia.add(new EktaktiApodoxi("Νυχτερινή Εργασία", 1.4));
        stoixeia.add(new ProsthetiApodoxi("Bonus"));
        stoixeia.add(new ProsthetiApodoxi("Προμήθεια Πωλήσεων"));

        ArrayList<Employee> ergazColl = new ArrayList<>();
        ergazColl.add(new Employee("Αναστάσιος", "Τουμαζάτος", 3000,0.6));
        ergazColl.add(new Employee("Νικόλαος", "Χριστοδούλου", 1000,0.35));
        ergazColl.add(new Employee("Χρίστος", "Χριστοδούλου", 400,0.9));
        ergazColl.add(new Employee("Γεώργιος", "Σύρος", 99.99,0.5));

        ArrayList<Kinhsh> kinColl = new ArrayList<>();
        kinColl.add(new Kinhsh(ergazColl.get(1), stoixeia.get(0), 20));
        kinColl.add(new Kinhsh(ergazColl.get(1), stoixeia.get(2), 5));
        kinColl.add(new Kinhsh(ergazColl.get(1), stoixeia.get(3), 5));
        kinColl.add(new Kinhsh(ergazColl.get(1), stoixeia.get(4), 5));
        kinColl.add(new Kinhsh(ergazColl.get(1), (ProsthetiApodoxi) stoixeia.get(5), 500));
        */
        // Main Menu
        Scanner s = new Scanner(System.in);
        System.out.println("-- Λογιστική Εφαρμογή (eLogistics Corporation. @ ALL RIGHTS RESERVED @ 2020)-- ");
        boolean notDone = true;
        while(notDone) {
            System.out.println("=========================================================");
            System.out.println(" 1. Εισαγωγή νέου μισθολογικού στοιχείου");
            System.out.println(" 2. Εισαγωγή νέου εργαζόμενου");
            System.out.println(" 3. Εισαγωγή νέας κίνησης εργαζόμενου");
            System.out.println(" 4. Εμφάνιση των μισθολογικών στοιχείων ");
            System.out.println(" 5. Εμφάνιση των κινήσεων εργαζομένων για ένα εργαζόμενο");
            System.out.println(" 6. Υπολογισμός του πληρωτέου ενός εργαζομένου");
            System.out.println(" 7. Υπολογισμός υποχρεώσεων της εταιρίας");
            System.out.println(" 0. ΕΞΟΔΟΣ");
            System.out.println("=========================================================");
            System.out.print(">>> ");
            int choice = s.nextInt();
            switch (choice) {
                case 0:
                    notDone = false;
                    DatabaseFormatter dbf = new DatabaseFormatter();
                    System.out.println();
                    System.out.print("Αποθήκευση μισθολογικών στοιχείων σε βάση δεδομένων... ");
                    dbf.writeStoixeia(stoixeia,"stoixeiaFINAL.txt");
                    System.out.print("ΕΠΙΤΥΧΙΑ!\n");
                    System.out.print("Αποθήκευση εργαζομένων και λογιστικών κινήσεων σε βάση δεδομένων... ");
                    dbf.writeEmployees(ergazColl,kinColl,"employeesFINAL.txt");
                    System.out.print("ΕΠΙΤΥΧΙΑ!\n");
                    System.out.println("ΕΞΟΔΟΣ");
                    break;
                case 1:
                    System.out.println("Εισαγωγή Νέου Μισθολογικού Στοιχείου: ");
                    System.out.println("1. Τακτική Αποδοχή");
                    System.out.println("2. Έκτακτη Αποδοχή");
                    System.out.println("3. Πρόσθετη Αποδοχή");
                    System.out.print("Αριθμός κατηγορίας: ");
                    int c1 = s.nextInt();
                    String title;
                    double synt;
                    switch (c1) {
                        case 1:
                            System.out.println("Εισαγωγή Νέας Τακτικής Αποδοχής");
                            System.out.print("Τίτλος: ");
                            s.nextLine();
                            title = s.nextLine();
                            System.out.print("Συντελεστής: ");
                            synt = s.nextDouble();
                            stoixeia.add(new TaktikiApodoxi(title, synt));
                            break;
                        case 2:
                            System.out.println("Εισαγωγή Νέας Έκτακτης Αποδοχής");
                            System.out.print("Τίτλος: ");
                            s.nextLine();
                            title = s.nextLine();
                            System.out.print("Συντελεστής: ");
                            synt = s.nextDouble();
                            stoixeia.add(new EktaktiApodoxi(title, synt));
                            break;
                        case 3:
                            System.out.println("Εισαγωγή Νέας Πρόσθετης Αποδοχής");
                            System.out.print("Τίτλος: ");
                            s.nextLine();
                            title = s.nextLine();
                            stoixeia.add(new ProsthetiApodoxi(title));
                            break;
                    }
                    break;
                case 2:
                    String name, surname;
                    double salary, perc;
                    System.out.println("Εισαγωγή Νέου Εργαζόμενου");
                    System.out.print("Όνομα: ");
                    s.nextLine();
                    name = s.nextLine();
                    System.out.print("Επίθετο: ");
                    surname = s.nextLine();
                    System.out.print("Μισθός: ");
                    salary = s.nextDouble();
                    System.out.print("Ποσοστό Ασφαλιστικών Εισφορών: ");
                    perc = s.nextDouble();
                    ergazColl.add(new Employee(name, surname, salary, perc));
                    break;
                case 3:
                    System.out.println("Εισαγωγή Νέας Λογιστικής Κίνησης");
                    System.out.println("Επιλέξτε Εργαζόμενο: ");
                    for (Employee e : ergazColl) {
                        System.out.println(e);
                    }
                    System.out.print("ID: ");
                    int sel = s.nextInt();
                    System.out.println("Επιλέξτε Μισθολογικό Στοιχείο");
                    for (int i = 0; i < stoixeia.size(); i++) {
                        System.out.println(i + ". " + stoixeia.get(i));
                    }
                    System.out.print("Αριθμός: ");
                    int sel1 = s.nextInt();
                    if (stoixeia.get(sel1).getClass() == ProsthetiApodoxi.class) {
                        System.out.print("Εισάγετε Ποσό: ");
                        double amount = s.nextDouble();

                        for(Employee emp : ergazColl){
                            if(emp.getID() == sel)
                                kinColl.add(new Kinhsh(emp, (ProsthetiApodoxi) stoixeia.get(sel1), amount));
                        }

                    } else {
                        System.out.print("Εισάγετε Ημέρες/Ώρες: ");
                        double amount = s.nextDouble();
                        for(Employee emp : ergazColl){
                            if(emp.getID() == sel)
                                kinColl.add(new Kinhsh(emp, stoixeia.get(sel1), amount));
                        }
                    }
                    break;
                case 4:
                    System.out.println("Λίστα Μισθολογικών Στοιχείων");
                    for (MisthologikoStoixeio m : stoixeia) {
                        System.out.println(m);
                    }
                    break;
                case 5:
                    System.out.println("Επιλέξτε Εργαζόμενο: ");
                    for (Employee e : ergazColl) {
                        System.out.println(e);
                    }
                    System.out.print("ID: ");
                    int sel2 = s.nextInt();
                    System.out.println("Λογιστικές Κινήσεις του Εργαζόμενου με ID: " + sel2);
                    for (Kinhsh kin : kinColl) {
                        if (kin.getTargetEmp().getID() == sel2)
                            System.out.println(kin);
                    }
                    break;
                case 6:
                    System.out.println("Επιλέξτε Εργαζόμενο: ");
                    for (Employee e : ergazColl) {
                        System.out.println(e);
                    }
                    System.out.print("ID Εργαζομένου: ");
                    int sel3 = s.nextInt();
                    PlhrwteoCalculator pl = new PlhrwteoCalculator(kinColl);
                    for (Employee e : ergazColl){
                        if(e.getID() == sel3)
                            System.out.println(e.toString() + " / Πληρωτέο: " + pl.calcPlhrwteoFor(e));
                    }
                    break;

                case 7:
                    PlhrwteoCalculator calc = new PlhrwteoCalculator(kinColl);
                    double synAsfalistikes = 0;
                    double synForos = 0;
                    System.out.println("Υπολογισμός Υποχρεώσεων της Εταιρίας");
                    for (Employee e: ergazColl){
                        double plhrwteo = calc.calcPlhrwteoFor(e);
                        System.out.println(e);
                        System.out.println("Πληρωτέο Εργαζομένου: " + plhrwteo);
                        System.out.println("Ασφαλιστικές Εισφορές: " + calc.getAsfalistikes());
                        System.out.println("Φόρος: " + calc.getForos());
                        synAsfalistikes += calc.getAsfalistikes();
                        synForos += calc.getForos();
                    }
                    System.out.println("======================================");
                    System.out.println("Συνολικές Υποχρεώσεις της Εταιρείας: ");
                    System.out.println("Συνολικές Ασφαλιστικές Εισφορές: " + synAsfalistikes);
                    System.out.println("Συνολικός Φόρος: " + synForos );
                    break;

            }
        }
    }
}
