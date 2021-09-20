package tasks;

import java.time.LocalDate;

public class Deadline extends Task{

    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = TimeHandler.getDate(by);
    }

    public String toString(){
        return "[D]" + super.toString() + " (by: " + TimeHandler.formatDate(by) + ')';
    }
}
