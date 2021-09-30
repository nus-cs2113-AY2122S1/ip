package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Tasks {
    protected final String type = "D";
   // protected String by = "";
    protected LocalDateTime dateTime;

    public Deadline(String description, LocalDateTime dateTime) {
        super(description);
        //this.by = by;
        this.dateTime=dateTime;
    }

    @Override
    public String toString() {
        return "[" + this.type + "]" + "[" + this.done + "] " + description.split("/by ")[0] + " (by: " + dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")";
    }
    public String toOutput(){
        return "D_"+ this.done+"_"+this.description+"_"+this.dateTime;
    }
}

//level8 commit edits
