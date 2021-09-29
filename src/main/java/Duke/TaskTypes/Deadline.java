package Duke.TaskTypes;

import Duke.BackEnd.DukeParser;
import java.time.LocalDateTime;
import static Duke.UI.DukeConstants.FORMAT_DATE_TIME_OUTPUT;

public class Deadline extends Task{

    protected LocalDateTime by;

    /**
     * Constructor for deadline class
     *
     * @param description deadline description
     * @param by deadline by description
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    //Add Getter and Setter
    public LocalDateTime getBy() {
        return by;
    }

    public void setBy(LocalDateTime by) {
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() +" (by: " + DukeParser.convertDateTimeToString(by, FORMAT_DATE_TIME_OUTPUT) + ")";
    }

    @Override
    public String getType() {
        return "D";
    }
}
