public class DeadlineTasks extends Tasks {
    String Deadline;

    public DeadlineTasks(String input, String deadline){
        super(input);
        this.Deadline = deadline;
    }

    @Override
    public String getName() {
        return "[D][" + super.mark() + "] " + super.name + "(" + Deadline.substring(0,Deadline.indexOf(" ")) + ": " + Deadline.substring(Deadline.indexOf(" ") + 1) + ")";
    }
}
