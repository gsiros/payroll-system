import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class DatabaseFormatter {
    private BufferedWriter buff;
    public void writeStoixeia(ArrayList<MisthologikoStoixeio> stoixeia, String filename){

        try {


            buff = new BufferedWriter(new FileWriter(new File(filename)));
            buff.write("MISTHOLOGIKA_STOIXEIA_COLLECTION\n{\n");
            for(MisthologikoStoixeio m : stoixeia){
                buff.write("\tMISTHOLOGIKO_STOIXEIO\n\t{\n");
                if(m.getClass()==ProsthetiApodoxi.class){
                    buff.write("\t\tTYPE PA\n");
                    buff.write("\t\tCODE "+ m.getId()+"\n");
                    buff.write("\t\tDESCR \""+m.getDescription()+"\"\n");
                }
                else{
                    buff.write("\t\tTYPE "+ m.getType()+"\n");
                    buff.write("\t\tCODE "+ m.getId()+"\n");
                    buff.write("\t\tDESCR \""+m.getDescription()+"\"\n");
                    buff.write("\t\tPERC "+m.getSyntelesths()+"\n");
                }
                buff.write("\t}\n\n");
            }
            buff.write("}");
            buff.close();
        }
        catch (Exception e){
            e.printStackTrace();

        }

    }

    public void writeEmployees(ArrayList<Employee> emps, ArrayList<Kinhsh> kins, String fileName){

        try {
            BufferedWriter buffEmps = new BufferedWriter(new FileWriter(new File(fileName)));
            buffEmps.write("EMPLOYEE_LIST\n{\n");
            for (Employee employee : emps){
                buffEmps.write("\tEMPLOYEE\n\t{\n");
                buffEmps.write("\t\tCODE "+ employee.getID()+ "\n");
                buffEmps.write("\t\tSURNAME \""+ employee.getSurname()+ "\"\n");
                buffEmps.write("\t\tFIRSTNAME \""+ employee.getName()+ "\"\n");
                buffEmps.write("\t\tSALARY "+ employee.getSalary()+ "\n");
                buffEmps.write("\t\tFUND_COEF "+ employee.getPercentage()+ "\n");
                buffEmps.write("\t\tTRNS\n\t\t{\n");
                for (Kinhsh kin: kins){
                    if(kin.getTargetEmp().getID() == employee.getID()){
                        buffEmps.write("\t\t\tTRN\n\t\t\t{\n");
                        buffEmps.write("\t\t\t\tELEMENT_TYPE "+kin.getMisthStoixeio().getType() +"\n");
                        buffEmps.write("\t\t\t\tCODE "+ kin.getMisthStoixeio().getId() + "\n");
                        if(kin.getMisthStoixeio().getClass() == ProsthetiApodoxi.class){
                            buffEmps.write("\t\t\t\tVALUE "+ kin.getAmount() + "\n");
                        }else {
                            buffEmps.write("\t\t\t\tVALUE "+ kin.getDuration() + "\n");
                        }
                        buffEmps.write("\t\t\t}\n\n");
                    }
                }
                buffEmps.write("\t\t}\n");
                buffEmps.write("\t}\n"); // here.
            }
            buffEmps.write("}");
            buffEmps.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
