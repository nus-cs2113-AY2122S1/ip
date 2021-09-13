package duke.task;

import duke.task.Task;

/**
 * Represents a Deadline input by user. Identified by 'deadline' header and '\by'.
 * Inherits Task class.
 */
public class Deadline extends Task {
    public String deadline;

    /**
     * Creates a Deadline and assigns taskDescription and deadline.
     * @param taskDescription String before the '\by' in the user input. Represents task description.
     * @param deadline String after the '\by' in the user input. Represents time that deadline should be done by.
     */
    public Deadline(String taskDescription, String deadline) {
        super(taskDescription);
        this.deadline = deadline;
    }

    /**
     * Displays the current deadline as well as completion status.
     * @return A string in the format "[D][ ] (taskDescription)" The box will be [X] if the task is completed.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }

    /**
     * Formats the data to include deadline timing and 'D' header
     * @return A string in the format "D,{X, },description,timing"
     */
    @Override
    public String toFile() {
        return "D," + super.toFile() + "," + deadline + "\n";
    }
}
