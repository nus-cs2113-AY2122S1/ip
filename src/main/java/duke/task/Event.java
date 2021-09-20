package duke.task;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Event extends Task {
    public static final String SYMBOL = "E";
    protected LocalDateTime at;
    private TaskTimeManager taskTimeManager = new TaskTimeManager();

    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

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