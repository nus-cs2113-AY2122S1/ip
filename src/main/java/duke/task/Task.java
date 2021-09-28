package duke.task;

public class Task {
    private String command;
    private boolean isDone;
    private String deadline;
    private boolean needToDo;


    /**
     * Constructor with task command.
     * @param command Task command.
     */
    public Task(String command) {
        this.command = command;
        this.isDone = false;
    }

    /**
     * Constructor with task command and whether task has been done.
     * @param command Task command
     * @param isDone Whether task has been done.
     */
    public Task(String command, boolean isDone){
        this(command);
        if(isDone){
            taskDone();
        }
    }

    /**
     * Returns task command.
     * @return Task command.
     */
    public String getTaskCommand() {
        return command;
    }

    /**
     * Check whether task command contains search keyword.
     * @param keyword Keyword that user typed in to search.
     * @return whether the task command contains given keyword.
     */
    public boolean containKeyword(String keyword){
        return command.contains(keyword);
    }

    /**
     * Returns the status icon according to whether task has been done.
     * @return The status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns whether task has been done.
     * @return Whether task has been done.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Mark the task as done.
     */
    public void taskDone(){
        this.isDone = true;
    }

    /**
     * Returns the task in string.
     * @return Task in string.
     */
    @Override
    public String toString(){
        return "[" + getStatusIcon() + "] " + command;
    }
}


