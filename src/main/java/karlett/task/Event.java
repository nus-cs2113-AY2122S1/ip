package karlett.task;

import java.io.FileWriter;
import java.io.IOException;

public class Event extends Task {

    protected String at;

    /* constructor used for user input */
    public Event(String description, String at) throws IOException {
        this.description = description;
        this.isDone = false;
        this.at = at;
        increaseNumberOfTasks();
        printNewTaskAddedMessage();
        appendNewTaskToFile();
    }

    /* constructor used for loading file data */
    public Event(String description, String at, boolean isDone) throws IOException {
        this.description = description;
        this.isDone = isDone;
        this.at = at;
        increaseNumberOfTasks();
    }

    //@Override
    private void appendNewTaskToFile() throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        String textToAppend = "E | 0 | " + description + " | " + at + "\n";
        fw.write(textToAppend);
        fw.close();
    }

    @Override
    public String toString() {
        return "[E] [" + this.getStatusIcon() + "] " + this.getDescription() + " (at: " + at + ")";
    }
}
