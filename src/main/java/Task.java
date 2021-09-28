public class Task {
    private boolean isCompleted = false;
    private String taskDescription;
    private final String DOUBLE_SPACE_INDENTATION = "  ";
    private final String TASK_COMPLETED = "X";
    private final String TASK_INCOMPLETE = " ";

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    /**
     * We set task as done.
     * */
    public void setTaskAsDone() {
        this.isCompleted = true;
    }

    /**
     * We set task as undone.
     * */
    public void setTaskAsUndone() {
        this.isCompleted = false;
    }

    /**
     * @return True if the task is completed else return false.
     * */
    public boolean isCompleted() {
        return this.isCompleted;
    }

    /**
     * @return Task description
     * */
    public String getTaskDescription() {
        return this.taskDescription;
    }

    /**
     * We call this method when we add a new task to the task list.
     * */
    public void printAddTaskMessage() {
        System.out.println("Got it. I've added this task: ");
        System.out.println(DOUBLE_SPACE_INDENTATION + this.toString());
    }

    /**
     * We call this method when we mark a task as done.
     * */
    public void printMarkTaskAsDone() {
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(DOUBLE_SPACE_INDENTATION + this.toString());
    }

    /**
     * @return The formatted task string.
     * taskCompleted == 'X' if task is completed else it is just " "
     * */
    @Override
    public String toString() {
        String taskCompleted = "";
        if (this.isCompleted) {
            taskCompleted = TASK_COMPLETED;
        } else {
            taskCompleted = TASK_INCOMPLETE;
        }
        String string = "[" + taskCompleted + "] " + this.taskDescription;
        return string;
    }
}
