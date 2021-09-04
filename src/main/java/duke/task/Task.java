package duke.task;
import duke.exception.DukeException;

public abstract class Task {
    protected String description;
    protected char type;
    protected boolean isDone = false;
    protected static int totalTasks = 0;

    public Task(String description) {
        this.description = description;
        totalTasks++;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[" + type + "][X] " + description;
        } else {
            return "[" + type + "][ ] " + description;
        }
    }

    public void markComplete() throws DukeException {
        if (isDone) {
            throw new DukeException(DukeException.TASK_ALREADY_DONE);
        } else {
            isDone = true;
        }
    }

    public static int getTotalTasks() {
        return totalTasks;
    }

    public String getDescription() {
        return description;
    }
}
