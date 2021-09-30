package Task;

import java.time.LocalDate;
import java.time.LocalTime;

public class Deadline extends Task {
    /** The deadline of the task**/
    protected String by;

    /**
     * A constructor of Deadline.
     * @param content the name of the task.
     * @param by the deadline of the task
     */
    public Deadline(String content, String by) {
        super(content);
        this.by = by;
    }

    /**
     * A methods to return the whole information of the Deadline
     * @return A String contains the type, name, and deadline for this task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(" + by + ")";
    }
}
