package duke.processes.tasks;

import java.time.LocalDateTime;

public class ToDo extends Task {

    public ToDo(String description, LocalDateTime d) {

        super(description, d);
    }

    @Override
    public String toString() {

        return description;
    }

    @Override
    public String getTaskType() {

        return "todo";
    }

    @Override
    public String getTaskID() {

        return "T";
    }

    @Override
    public String getDate() {

        return " ";
    }
}
