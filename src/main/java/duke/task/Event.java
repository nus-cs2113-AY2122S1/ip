package duke.task;

import java.io.FileWriter;
import java.io.IOException;

/**
 * A subclass of Task that contains the description and date of commencement of the task.
 */
public class Event extends Task {

    public static final String SYMBOL = "E";
    /**
     * The date of commencement of the event
     */
    protected String at;

    /**
     * The constructor for creating an Event class object
     *
     * @param description the description of the event
     * @param at the date of commencement of the event
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public void writeToFile(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(SYMBOL + " | " + (isDone ? 1 : 0) + " | " + description + " | " + at + System.lineSeparator());
        fw.close();
    }

    @Override
    public String toString() {
        return "[" + SYMBOL + "]" + super.toString() + " (at: " + at + ")";
    }
}