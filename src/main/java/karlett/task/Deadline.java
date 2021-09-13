package karlett.task;

import java.io.FileWriter;
import java.io.IOException;

public class Deadline extends Task {

    protected String by;

    /* constructor used for user input */
    public Deadline(String description, String by) throws IOException {
        this.description = description;
        this.isDone = false;
        this.by = by;
        increaseNumberOfTasks();
        printNewTaskAddedMessage();
        appendNewTaskToFile();
    }

    /* constructor used for loading file data */
    public Deadline(String description, String by, boolean isDone) throws IOException {
        this.description = description;
        this.isDone = isDone;
        this.by = by;
        increaseNumberOfTasks();
    }

    //@Override
    private void appendNewTaskToFile() throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        String textToAppend = "D | 0 | " + description + " | " + by + "\n";
        fw.write(textToAppend);
        fw.close();
    }

    @Override
    public String toString() {
        return "[D] [" + this.getStatusIcon() + "] " + this.getDescription() + " (by: " + by + ")";
    }
}
