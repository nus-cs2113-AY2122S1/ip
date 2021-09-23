package tasks;

/**
 * Represents deadline tasks specifically
 */
public class DeadlineTasks extends Tasks {
    String Deadline;

    protected DeadlineTasks(String name, String deadline){
        this.name = name;
        this.Deadline = deadline;
        this.isCompleted = false;
    }

    @Override
    protected String getName() {
        return "[D][" + getComplete() + "] " + name + "(" + Deadline.substring(0,Deadline.indexOf(" ")) + ": "
                + Deadline.substring(Deadline.indexOf(" ") + 1) + ")";
    }

    @Override
    protected String getTaskData(){
        return "D," + isCompleted + "," + name + "," + Deadline;
    }
}
