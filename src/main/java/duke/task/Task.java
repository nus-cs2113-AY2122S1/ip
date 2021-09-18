package duke.task;

import duke.parser.Parser;
import duke.exception.DukeInvalidAddTaskException;

public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor of Task object, initializing the description and done status
     * of the task.
     * 
     * @param description task description.
     * @throws DukeInvalidAddTaskException if task description contains "--", which
     * is a format reserved for only when saving the task to the text file.
     */
    public Task(String description) throws DukeInvalidAddTaskException {
        if (description.contains("--")) {
            throw new DukeInvalidAddTaskException();
        }
        this.description = Parser.getDescription(description);
        this.isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }
    
    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        isDone = true;
    }
    
    public String toString() {
        String statusIcon;
        if (isDone) {
            statusIcon = "X";
        } else {
            statusIcon = " ";
        }
        return "[" + statusIcon + "] " + description;
    }
}
