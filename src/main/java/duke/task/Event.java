package duke.task;

import java.io.FileWriter;
import java.io.IOException;

public class Event extends Task {
    protected String at;

    public Event(String descr, String at) {
        super(descr);
        this.at = at;
    }

    @Override
    public void writeToFile(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write("E" + " | " + (isDone ? 1 : 0) + " | " + descr + " | " + at + System.lineSeparator());
        fw.close();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}