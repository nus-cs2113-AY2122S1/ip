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
     * Constructor of Task object.
     *
     * @param description Task name of Task.
     * @param taskType    T:todo D:deadline E:event
     * @param isDone      Status of Task
     */
    public Task(String description, char taskType, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
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
        Ui.printLineOnConsole();
        System.out.println(Ui.TASK_CHECK_DONE);
        System.out.println("     " + "[" + this.taskType + "][X] " + this.description);
        Ui.printLineOnConsole();
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
