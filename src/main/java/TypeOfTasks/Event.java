package TypeOfTasks;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class Event extends Task {
    protected String at;
    protected String tag = "E";
    protected LocalDate localdate;
    
    public Event(String description,String at) {
        super(description);
        this.at = at;
        try {
            this.localdate = LocalDate.parse(at);
            this.at = localdate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException dateTimeParseException) {
            this.at = at;
        }
    }

    public String getInfo() {
        return at;
    }

    public String getTag() {
        return tag;
    }
    public void printList(Task theTask, int index) {
        System.out.println(index + ".[E][" + theTask.getStatus() + "] "+ theTask.getDescription() + "(at: " + at + ")");
    }
}
