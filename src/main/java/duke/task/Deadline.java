package duke.task;

import duke.manager.TaskManager;

import java.time.LocalDateTime;

public class Deadline extends Task {

    protected LocalDateTime by;
    protected String byString;

    /**
     * Constructor to initialise the description
     * and the deadline of the task.
     *
     * @param description string with the task
     *                    description
     * @param by          deadline for the task
     */
    public Deadline(String description, LocalDateTime by, String byString) {
        super(description);
        this.by = by;
        this.byString = byString;
    }

    /**
     * Returns the formatted Description of the task.
     *
     * @return returns a String with the deadline task description
     */
    @Override
    public String getDescription() {
        return "[D]" + super.getDescription() + " (by: " + TaskManager.stringFormatter.format(by) + ")";
    }

    @Override
    public String fileDescription() {
        return "D | " + super.fileDescription() + " | " + byString;
    }
}
