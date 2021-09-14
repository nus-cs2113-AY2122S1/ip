package tasks;

public class DeadlineTasks extends Tasks {
    String Deadline;

    public DeadlineTasks(String name, String deadline){
        this.name = name;
        this.Deadline = deadline;
        this.isCompleted = false;
    }

    @Override
    public String getName() {
        return "[D][" + super.mark() + "] " + name + "(" + Deadline.substring(0,Deadline.indexOf(" ")) + ": "
                + Deadline.substring(Deadline.indexOf(" ") + 1) + ")";
    }
}
