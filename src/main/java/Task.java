public class Task {
    private boolean isCompleted = false;
    private String taskDescription;
    private int taskNumber;

    public Task(String taskDescription) {
        System.out.println("Got it. I've added this task: ");
        this.taskDescription = taskDescription;
    }

    public String getTaskDescription() {
        return this.taskDescription;
    }

    public void setTaskNumber(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public int getTaskNumber() {

        return this.taskNumber;
    }

    public void setTaskAsDone() {
        this.isCompleted = true;
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("  " + this.toString());
    }

    public String getTaskType() {
        String[] taskDescriptionSplitted = this.taskDescription.split(" ");
        String taskType = taskDescriptionSplitted[0];
        return taskType;
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
