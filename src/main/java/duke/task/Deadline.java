package duke.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Deadline extends Task {
    //private String by;
    private LocalDate by;

//    public Deadline(String description,String by){
//        super(description);
//        this.by = by;
//    }

    public Deadline(String description, LocalDate by){
        super(description);
        this.by = by;
    }

    public Deadline(String taskDescription,LocalDate by, boolean isDone){
        super (taskDescription,isDone);
        this.by = by;
    }

    public String getBy() {
        return by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + getBy() + ")";
    }
}
