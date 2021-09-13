package duke.tasks;

import duke.Task;

public class Deadlines extends Task {
    protected String date;

    public Deadlines(String description, String date) {
        super(description);
        this.date = date;
    }

    public String toString() {
        return description;
    }

    public String getTaskType() {
        return "deadline";
    }

    public String getTaskID() {
        return "D";
    }

    public String getDate() {
        return date;
    }
}
