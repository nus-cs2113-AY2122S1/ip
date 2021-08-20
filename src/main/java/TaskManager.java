public class TaskManager {
    private Task[] taskList = new Task[100];
    private int taskCount;

    public void addTask(String taskName) {
        taskList[taskCount] = new Task(taskName);
        taskCount++;
    }

    public void listTask() {
        for (int i = 0; i < taskCount; i++) {
            System.out.println(i + 1 + ". " + taskList[i].getTask());
        }
    }
}
