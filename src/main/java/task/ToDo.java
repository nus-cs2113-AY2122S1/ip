package task;

import task.Task;

public class ToDo extends Task {
    public ToDo(String desc, Boolean status) {
        super(desc, status);
    }

    public String toString() {
        return "[t] [" + getStatus() + "] " + getDescription();
    }
}
