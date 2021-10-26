package tasks;

import java.io.FileWriter;
import java.io.IOException;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public String getClassType() {
        return "[T]";
    }

    @Override
    public String toString() {
        return getClassType() + getStatusIcon() + getDescription();
    }

    @Override
    public void saveTask(String filePath) throws IOException {
        FileWriter fileWrite = new FileWriter(filePath, true);
        fileWrite.write("T | " + getDescription() + " | null | " + getDone() + "\n");
        fileWrite.close();
    }
}
