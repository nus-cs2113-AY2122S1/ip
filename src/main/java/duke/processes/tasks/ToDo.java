package duke.processes.tasks;

import java.time.LocalDateTime;

public class ToDo extends Task {

    public ToDo(String description, LocalDateTime d) {

        super(description, d);
    }

    public String toString() {
        return description;
    }

    public String getTaskType() {
        return "todo";
    }

    public String getTaskID() {
        return "T";
    }

    public String getDate() {
        return " ";
    }
}
