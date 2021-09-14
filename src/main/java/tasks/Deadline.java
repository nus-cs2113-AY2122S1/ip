package tasks;

import java.io.FileWriter;
import java.io.IOException;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String date) {
        super(description);
        by = date;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String outcome) {
        by = outcome;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + getBy() + ")";
    }

    @Override
    public String getClassType() {
        return "[D]";
    }

    @Override
    public void saveTask(String filePath) throws IOException {
        FileWriter fileWrite = new FileWriter(filePath, true);
        fileWrite.write("D | " + getDescription() + " | " + getBy() + " | " + getDone() + "\n");
        fileWrite.close();
    }
}
