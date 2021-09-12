package duke;

public class Task {
    protected String description;
    protected boolean isDone;
    protected char taskType;

    /**
     * Constructor of Task object.
     *
     * @param description Task name of Task.
     * @param taskType    T:todo D:deadline E:event
     */
    public Task(String description, char taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    /**
     * Returns status of Task.
     *
     * @return "X" if isDone is true, else return  " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
        Greet.printLineOnConsole();
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("     " + "[" + this.taskType + "][X] " + this.description);
        Greet.printLineOnConsole();
    }

    /**
     * Returns description of task.
     *
     * @return Description of task.
     */
    public String getDescription() {
        return description;
    }

    public char getTaskType() {
        return taskType;
    }
}
