package tasks;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTasks extends Tasks {
    LocalDateTime Deadline;
    String strDeadline;

    protected DeadlineTasks(String name, String deadline){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy HHmm");
        this.name = name;
        this.strDeadline = deadline;
        this.Deadline = LocalDateTime.parse(deadline.substring(deadline.indexOf(" ") + 1), formatter);
        this.isCompleted = false;
    }

    @Override
    protected String getName() {
        return "[D][" + getComplete() + "] " + name + "(" + strDeadline.substring(0,strDeadline.indexOf(" ")) + ": "
                + Deadline.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy hh:mm a")) + ")";
    }

    @Override
    protected String getTaskData(){
        return "D," + isCompleted + "," + name + "," + strDeadline;
    }
}
