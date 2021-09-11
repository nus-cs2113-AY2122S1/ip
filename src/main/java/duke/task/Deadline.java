package duke.task;

import java.io.FileWriter;
import java.io.IOException;

public class Deadline extends Task {

    protected String by;

    public Deadline(String descr, String by) {
        super(descr);
        this.by = by;
    }

    @Override
    public void writeToFile(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write("D" + " | " + (isDone ? 1 : 0) + " | " + descr + " | " + by + System.lineSeparator());
        fw.close();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
