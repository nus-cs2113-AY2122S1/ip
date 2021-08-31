public class Task {
    /** Name of task */
    private String name;
    /** Whether the task has been completed */
    private boolean isDone;
    /** Type of task stored as ['Type'] */
    protected String typeOfTask = "[T]";

    /**
     * Creates a task with the specified name.
     * @param name Name of the task.
     */
    public Task(String name) {
        this.name = name;
        isDone = false;
    }

    /**
     * Get the name of the task.
     * @return Name of task.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the completion status of the task as a symbol.
     * @return "[X]" if done and "[ ]" if not done.
     */
    public String getDoneStatusAsSymbol() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Get the type of task.
     * @return [T] if todo, [D] if deadline, [E] if event.
     */
    public String getTypeOfTask() {
        return typeOfTask;
    }

    /**
     * Find out if task is done.
     * @return True if done or False is not done.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Change if task has been done.
     * @param done True if done or False if not done.
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Prints task in a human-readable format.
     * @return [Type of Task][Completion Status] [Name of Task]
     */
    public String printTask() {
        return this.getTypeOfTask() + this.getDoneStatusAsSymbol() + " " + this.getName();
    }
}
