public class Deadline extends Task{
    protected String deadLine;

    public Deadline(String description, boolean completed, String deadLine) {
        super(description, completed);
        this.deadLine = deadLine;
    }

    public String getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
    }

    public String deadlineIcon() {
        String completedIcon = "D";
        return  completedIcon;
    }

    public void initialiseDeadline(){
        System.out.println("______________________________\n");
        System.out.println("[" + deadlineIcon() + "]"
                + "[ ]"
                + taskName + "(" + deadLine + ") "
                + "has been added!\n");
    }
}
