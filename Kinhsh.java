public class Kinhsh {

    private Employee targetEmp;
    private MisthologikoStoixeio misthStoixeio;
    private double duration;
    private double amount;

    public Kinhsh(Employee targetEmp,
                  MisthologikoStoixeio misthStoixeio,
                  double duration) {
        this.targetEmp = targetEmp;
        this.misthStoixeio = misthStoixeio;
        this.duration = duration;
    }

    public Kinhsh(Employee targetEmp,
                  ProsthetiApodoxi misthStoixeio,
                  double amount) {
        this.targetEmp = targetEmp;
        this.misthStoixeio = misthStoixeio;
        this.amount = amount;
    }

    public Employee getTargetEmp() {
        return targetEmp;
    }

    public MisthologikoStoixeio getMisthStoixeio() {
        return misthStoixeio;
    }

    public double getDuration() {
        return duration;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        if (this.getMisthStoixeio().getClass() == ProsthetiApodoxi.class) {
            return "(Λογιστική Κίνηση) :\n" + "ΕΡΓΑΖΟΜΕΝΟΣ: " + targetEmp.toString() + "\n" + "ΜΙΣΘΟΛΟΓΙΚΟ ΣΤΟΙΧΕΙΟ: " + misthStoixeio.toString() + "\n"
                    + "ΠΟΣΟ: "+ amount;

        } else if(this.getMisthStoixeio().getClass() == EktaktiApodoxi.class){
            return "(Λογιστική Κίνηση) :\n" + "ΕΡΓΑΖΟΜΕΝΟΣ: " + targetEmp.toString() + "\n" + "ΜΙΣΘΟΛΟΓΙΚΟ ΣΤΟΙΧΕΙΟ: " + misthStoixeio.toString() + "\n"
                    + "ΩΡΕΣ: "+ duration;
        } else {
            return "(Λογιστική Κίνηση) :\n" + "ΕΡΓΑΖΟΜΕΝΟΣ: " + targetEmp.toString() + "\n" + "ΜΙΣΘΟΛΟΓΙΚΟ ΣΤΟΙΧΕΙΟ: " + misthStoixeio.toString() + "\n"
                    + "ΗΜΕΡΕΣ: "+ duration;
        }


    }
}