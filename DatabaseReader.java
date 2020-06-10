import java.io.*;
import java.util.*;

public class DatabaseReader {

    private Employee employeeTarget;
    private MisthologikoStoixeio stoixeioTarget;

    public void loadEmployeeData(String fileName, ArrayList<Employee> employees, ArrayList<Kinhsh> kinhshes, ArrayList<MisthologikoStoixeio> stoixeia){
        try{
            BufferedReader buff = new BufferedReader(new FileReader(new File(fileName)));
            String line = buff.readLine();

            if(line == null || !line.toUpperCase().trim().equals("EMPLOYEE_LIST")){
                throw new Exception("Σφάλμα στην ανάγνωση του αρχείου. Δεν βρέθηκε η λίστα των εργαζομένων");
            }
            line = buff.readLine();
            if(line.equals("{")){
                while (line!= null){

                    line = buff.readLine();
                    if(line!= null && line.toUpperCase().trim().equals("EMPLOYEE")){
                        line = buff.readLine();
                        if(line != null && line.toUpperCase().trim().equals("{")){

                            boolean done = false;
                            String name = "", salary = "", perc = "", id = "", surname = "";
                            do {
                                line = buff.readLine();


                                if(line!= null && line.toUpperCase().trim().split(" ")[0].equals("CODE")){
                                    id = line.toUpperCase().trim().split(" ")[1];
                                }else if(line!= null && line.toUpperCase().trim().split(" ")[0].equals("SURNAME")){
                                    surname = line.toUpperCase().trim().split(" ")[1].replaceAll("\"","");
                                }else if(line!= null && line.toUpperCase().trim().split(" ")[0].equals("FIRSTNAME")){
                                    name = line.toUpperCase().trim().split(" ")[1].replaceAll("\"","");
                                }else if(line!= null && line.toUpperCase().trim().split(" ")[0].equals("SALARY")){
                                    salary = line.toUpperCase().trim().split(" ")[1];
                                }else if(line!= null && line.toUpperCase().trim().split(" ")[0].equals("FUND_COEF")){
                                    perc = line.toUpperCase().trim().split(" ")[1];
                                }else if (line.toUpperCase().trim().equals("}")){
                                    done = true;
                                }else if(line.toUpperCase().trim().equals("TRNS")){
                                    employeeTarget = new Employee(Integer.parseInt(id), name, surname , Double.parseDouble(salary), Double.parseDouble(perc));
                                    employees.add(employeeTarget);
                                    for (Employee emp: employees){
                                        if (emp.getID() == Integer.parseInt(id)){
                                            Employee employeeTarget = emp;
                                        }
                                    }
                                    line = buff.readLine();

                                    if(line != null && line.toUpperCase().trim().equals("{")){
                                        boolean trnComplete = false;

                                        while (!trnComplete){
                                            line = buff.readLine();
                                            if(line!=null && line.toUpperCase().trim().equals("TRN")){
                                                line = buff.readLine();
                                                if(line!=null && line.toUpperCase().trim().equals("{")){
                                                    boolean complete = false;
                                                    String type="", code="", value="";

                                                    do {

                                                        line = buff.readLine().trim().toUpperCase();
                                                        if(line.split(" ")[0].equals("ELEMENT_TYPE")){
                                                            type = line.trim().split(" ")[1];
                                                        }
                                                        else if(line.split(" ")[0].equals("CODE")) {
                                                            code = line.split(" ")[1];
                                                        }
                                                        else if(line.split(" ")[0].equals("VALUE")){
                                                            value = line.split(" ")[1];
                                                        }
                                                        else if(line.toUpperCase().equals("}")){
                                                            complete = true;
                                                        }
                                                        else{
                                                            complete = true;
                                                            throw new Exception("error here.");
                                                        }

                                                    }while(!complete);
                                                    //System.out.println("TRN: "+type+" "+code+" "+value);

                                                    if (type.trim().equals("PA")){
                                                        for (MisthologikoStoixeio misth : stoixeia){
                                                            if (misth.getId() == Integer.parseInt(code) && misth.getClass() == ProsthetiApodoxi.class){
                                                                stoixeioTarget = misth;
                                                            }
                                                        }
                                                        //System.out.println(employeeTarget+"\n"+stoixeioTarget+"\n"+Double.parseDouble(value));
                                                        kinhshes.add(new Kinhsh(employeeTarget, (ProsthetiApodoxi) stoixeioTarget, Double.parseDouble(value)));
                                                    }else if(type.trim().equals("TA")) {
                                                        for (MisthologikoStoixeio misth : stoixeia){
                                                            if (misth.getId() == Integer.parseInt(code) && misth.getClass() == TaktikiApodoxi.class){
                                                                stoixeioTarget = misth;
                                                            }
                                                        }
                                                        //System.out.println(employeeTarget+"\n"+stoixeioTarget+"\n"+Double.parseDouble(value));
                                                        kinhshes.add(new Kinhsh(employeeTarget, stoixeioTarget, Double.parseDouble(value)));
                                                    } else{
                                                        for (MisthologikoStoixeio misth : stoixeia){
                                                            if (misth.getId() == Integer.parseInt(code) && misth.getClass() == EktaktiApodoxi.class){
                                                                stoixeioTarget = misth;
                                                            }
                                                        }
                                                        //System.out.println(employeeTarget+"\n"+stoixeioTarget+"\n"+Double.parseDouble(value));
                                                        kinhshes.add(new Kinhsh(employeeTarget, stoixeioTarget, Double.parseDouble(value)));
                                                    }

                                                } else {
                                                    throw new Exception("error right here");
                                                }

                                            }else if(line!=null && line.toUpperCase().trim().equals("}")){
                                                trnComplete = true;
                                                //System.out.println("ERGAZ: "+name+" "+surname+" "+salary+" "+id+" "+perc);
                                            }
                                            else {
                                                // continue
                                                //throw new Exception("continue loop");
                                            }
                                        }
                                    }else{
                                        throw new Exception("heyyy!");
                                    }
                                }else{
                                    throw new Exception("Σφάλμα στην ανάγνωση του εργαζομένου");
                                }
                            }while (!done);
                        }
                    }

                }

            }
            buff.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<MisthologikoStoixeio> loadStoixeiaDB(String filename){

        ArrayList<MisthologikoStoixeio> output = new ArrayList<>();
         try{

             BufferedReader buff = new BufferedReader(new FileReader(new File(filename)));
             String line = buff.readLine();
             if (line == null || ! line.equals("MISTHOLOGIKA_STOIXEIA_COLLECTION")){
                 throw new Exception("Σφάλμα στην ανάγνωση του αρχείου. Δεν βρέθηκε η συλλογή μισθολογικών στοιχείων.");

             }
             line = buff.readLine();
             if (line.toUpperCase().trim().equals("{") ){

                 while(line!=null){
                     line = buff.readLine();
                     if(line!=null && line.toUpperCase().trim().equals("MISTHOLOGIKO_STOIXEIO")){
                         line = buff.readLine().trim();

                         if(line!=null && line.toUpperCase().equals("{")){
                             boolean done = false;
                             String type = "", id= "", desc = "", perc = "";
                             do{
                                 line = buff.readLine().trim().toUpperCase();
                                 if(line.split(" ")[0].equals("TYPE")){
                                     type = line.trim().split(" ")[1];
                                 }
                                 else if(line.split(" ")[0].equals("CODE")) {
                                     id = line.split(" ")[1];
                                 }
                                 else if(line.split(" ")[0].equals("DESCR")) {
                                     String[] array = line.split(" ");
                                     for (int i = 1; i < array.length; i++) {
                                         desc += array[i].trim().replaceAll("\"", "")+" ";
                                     }
                                     desc=desc.trim();
                                 }
                                 else if(line.split(" ")[0].equals("PERC")){
                                     perc = line.split(" ")[1];
                                 }
                                 else if(line.toUpperCase().equals("}")){
                                     done = true;
                                 }
                                 else{
                                     done = true;
                                     throw new Exception("error here.");
                                 }

                             }
                             while(!done);
                             //System.out.println(type+" "+ id+" "+ desc+" "+ perc+" ");
                             if(type.equals("TA")){
                                 output.add(new TaktikiApodoxi(Integer.parseInt(id), desc, Double.parseDouble(perc)));
                             }
                             else if(type.equals("EA")){
                                 output.add(new EktaktiApodoxi(Integer.parseInt(id), desc, Double.parseDouble(perc)));
                             }
                             else if(type.equals("PA")){
                                 output.add(new ProsthetiApodoxi(Integer.parseInt(id), desc));
                             }
                             else{
                                 throw new Exception("Δεν βρέθηκε Μισθολογικό Στοιχείο. ");
                             }
                         }
                         else if(line.equals("") || line.equals("\n")){
                             throw new Exception("Σφάλμα στην ανάγνωση του Μισθολογικού Στοιχείου.");

                         }

                     }


                 }



             }
             else if (line.toUpperCase().trim().equals("}") ){
                buff.close();
             }
            else{
                throw new Exception("Σφάλμα στην ανάγνωση του αρχείου. Λανθασμένος τρόπος ανοίγματος/κλεισίματος της συλλογής.");

             }
         }
        catch (Exception e){
             e.printStackTrace();

        }

        return output;
    }

}
