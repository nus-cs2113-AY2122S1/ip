package duke.task;

import duke.exception.DukeException;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public static Task parse(String taskInfo) throws DukeException {
        if (taskInfo.isBlank()) {
            // the string is empty or contains only white space
            throw new DukeException("The description of a deadline cannot be empty :-(");
        } else {
            int i = taskInfo.indexOf(" /by ");
            if ( i == -1) {
                throw new DukeException("There should be a \"/by\" in the deadline :-(");
            }else{
                String description = taskInfo.substring(0, i);
                String by = taskInfo.substring(i + 5);
                return new Deadline(description, by);
            }
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
