public class Task {
    private boolean isCompleted = false;
    private String taskDescription;

    public Task(String taskDescription) {
        System.out.println("Got it. I've added this task: ");
        this.taskDescription = taskDescription;
    }

    public void setTaskAsDone() {
        this.isCompleted = true;
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("  " + this.toString());
    }

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
