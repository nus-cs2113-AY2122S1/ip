package duke.task;

import java.io.FileWriter;
import java.io.IOException;

public class Deadline extends Task {
    public static final String SYMBOL = "D";
    protected String by;

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
