package duke.task;

import duke.exception.DukeInvalidAddTaskException;

public abstract class Task {
    private String description;
    private boolean isDone;

    public Task(String description) throws DukeInvalidAddTaskException {
        if (description.contains("--")) {
            throw new DukeInvalidAddTaskException();
        }
        this.description = description;
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
