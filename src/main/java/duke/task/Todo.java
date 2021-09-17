package duke.task;

import java.io.FileWriter;
import java.io.IOException;

public class Todo extends Task {
    public static final String SYMBOL = "T";

    public Todo(String description) {
        super(description);
    }

    @Override
    public void writeToFile(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(SYMBOL + " | " + (isDone ? 1 : 0) + " | " + description + System.lineSeparator());
        fw.close();
    }

    @Override
    public String toString() {
        return "[" + SYMBOL + "]" + super.toString();
    }
}