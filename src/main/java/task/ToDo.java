package task;

import task.Task;

public class ToDo extends Task {
    public ToDo(String desc) {
        super(desc);
    }

    public String toString() {
        return "[t] [" + getStatus() + "] " + getDescription();
    }

    public String toDataString() {
        String output = "T | ";
        if (getStatus().equals("X")) {
            output += "1 | ";
        } else {
            output += "0 | ";
        }
        output += getDescription();
        return output;
    }
}
