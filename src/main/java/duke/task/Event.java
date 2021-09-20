package duke.task;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * A subclass of Task that contains the description and date of commencement of the task.
 */
public class Event extends Task {

    public static final String SYMBOL = "E";
    private static final TaskTimeManager taskTimeManager = new TaskTimeManager();

    /**
     * The date of commencement of the event
     */
    protected LocalDateTime at;

    /**
     * The constructor for creating an Event class object
     *
     * @param description the description of the event
     * @param at the date of commencement of the event
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the date of commencement of the event
     *
     * @return the date of commencement of the event
     */
    public LocalDateTime getAt() {
        return at;
    }

    @Override
    public void writeToFile(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(SYMBOL + " | " + (isDone ? 1 : 0) + " | " + description + " | "
                + taskTimeManager.getWriteToFileFormat(at) + System.lineSeparator());
        fw.close();
    }

    @Override
    public String toString() {
        return "[" + SYMBOL + "]" + super.toString() + " (at: " + taskTimeManager.getDisplayFormat(at) + ")";
    }
}