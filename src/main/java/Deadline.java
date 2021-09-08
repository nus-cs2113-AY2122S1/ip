public class Deadline extends Task{
    protected String deadLine;

    public Deadline(String description, boolean completed, String deadLine) {
        super(description, completed);
        this.deadLine = deadLine;
    }

    /**
     * Contains deadline Icon [D].
     *
     * @return Icon in String format.
     */
    public String deadlineIcon() {
        String deadlineIcon = "D";
        return  deadlineIcon;
    }

    /**
     * Output message when the deadline task is added.
     */
    public void initialiseDeadline(){
        System.out.println("______________________________\n");
        System.out.println("[" + deadlineIcon() + "]"
                + "[ ]"
                + taskName + "(" + deadLine + ") "
                + " has been added!\n");
    }

    /**
     * Override the toString method of Deadline class.
     *
     * @return String message in the right format.
     */
    @Override
    public String toString(){
        String s = "[" + deadlineIcon() + "] " + "[" + super.completedTaskIcon() + "]" + super.taskName + deadLine;
        return s;
    }
}