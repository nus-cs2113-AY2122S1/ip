public class Task {
    private boolean isCompleted = false;
    private String taskDescription;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public void setTaskAsDone() {
        this.isCompleted = true;
    }

    public boolean isCompleted() {
        return this.isCompleted;
    }

    public String getTaskDescription() {
        return this.taskDescription;
    }

    public void printAddTaskMessage() {
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + this.toString());
    }

    public void printMarkTaskAsDone() {
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("  " + this.toString());
    }

    /**
     * @return The formatted task string.
     * taskCompleted == 'X' if task is completed else it is just " "
     * */
    @Override
    public String toString() {
        String taskCompleted = "";
        if (this.isCompleted) {
            taskCompleted = "X";
        } else {
            taskCompleted = " ";
        }
        String string = "[" + taskCompleted + "] " + this.taskDescription;
        return string;
    }
}
