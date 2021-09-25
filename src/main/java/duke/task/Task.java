package duke.task;

public abstract class Task {
    protected String description;
    protected boolean isDone = false;

    public Task(String name) {
        this.description = name;
    }

    public String getStatusSymbol() {
        return ("[" + (isDone ? "X" : " ") + "]");
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String name) {
        this.description = name;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void setDone() {
        isDone = true;
    }

    public String toString() {
        return (getTaskSymbol() + getStatusSymbol() + " " + description);
    }

    /**
     * returns the task symbol according to the type of Task.
     * [T] for Todo, [D] for Deadline, [E] for Event.
     * @return task type symbol
     */
    public abstract String getTaskSymbol();

    /**
     * returns the task object in String form for saving into save file
     * In the form [a][b] Description DT: LocalDateTime
     * a is task type, b is isDone status, "DT: LocalDateTime"  is only for Deadline or Event classes.
     * @return Task object as a String
     */
    public abstract String toStringForSave();
}
