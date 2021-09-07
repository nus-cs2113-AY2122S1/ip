package task;

import task.Task;

public class ToDo extends Task {
    public ToDo(String desc) {
        super(desc);
    }

    public String toString() {
        return "[t] [" + getStatus() + "] " + getDescription();
    }
}
