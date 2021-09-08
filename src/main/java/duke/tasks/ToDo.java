package duke.tasks;

import duke.Task;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public String toString() {
        return description;
    }

    public String getTaskType() {
        return "ToDo";
    }

    public String getTaskID() {
        return "T";
    }

    public String getDate() {
        return " ";
    }
}
