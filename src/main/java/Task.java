public class Task {
    private String completed = " ";
    private String task;
    private int taskNumber;

    public Task(String task, int taskNumber) {
        this.task = task;
        this.taskNumber = taskNumber;
    }

    public String getTask() {
        return this.task;
    }

    public int getTaskNumber() {
        return this.taskNumber;
    }

    public void setTaskAsDone() {
        this.completed = "X";
        System.out.println("  " + this.toString());
    }

    @Override
    public String toString() {
        String string = "[" + this.completed + "] " + this.task;
        return string;
    }
}
