package duke.task;

import java.io.FileWriter;
import java.io.IOException;

/**
 * A subclass of Task that contains the description and date of due of the task.
 */
public class Deadline extends Task {

    public static final String SYMBOL = "D";
    /**
     * The date of due of the task
     */
    protected String by;

    /**
     * The constructor for creating a Deadline class object
     *
     * @param description the description of the deadline
     * @param by the date of due of the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public void writeToFile(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(SYMBOL + " | " + (isDone ? 1 : 0) + " | " + description + " | " + by + System.lineSeparator());
        fw.close();
    }

    @Override
    public String toString() {
        return "[" + SYMBOL + "]" + super.toString() + " (by: " + by + ")";
    }
}
