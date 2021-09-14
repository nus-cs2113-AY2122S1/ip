package tasks;

import java.io.FileWriter;
import java.io.IOException;

public class Event extends Deadline{
    public Event(String description, String date) {
        super(description, date);
    }

    @Override
    public String getClassType() {
        return "[E]";
    }

    @Override
    public String toString() {
        return getClassType() + getStatusIcon() + getDescription() + " (at: " + getBy() + ")";
    }

    @Override
    public void saveTask(String filePath) throws IOException {
        FileWriter fileWrite = new FileWriter(filePath, true);
        fileWrite.write("E | " + getDescription() + " | " + getBy() + " | " + getDone() + "\n");
        fileWrite.close();
    }
}
