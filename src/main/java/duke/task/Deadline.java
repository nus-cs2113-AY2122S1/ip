package duke.task;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Deadline extends Task {
    public static final String SYMBOL = "D";
    protected LocalDateTime by;
    private TaskTimeManager taskTimeManager = new TaskTimeManager();

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public LocalDateTime getBy() {
        return by;
    }

    @Override
    public void writeToFile(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(SYMBOL + " | " + (isDone ? 1 : 0) + " | " + description + " | "
                + taskTimeManager.getWriteToFileFormat(by) + System.lineSeparator());
        fw.close();
    }

    @Override
    public String toString() {
        return "[" + SYMBOL + "]" + super.toString() + " (by: " + taskTimeManager.getDisplayFormat(by) + ")";
    }
}
