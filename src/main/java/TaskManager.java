import java.util.ArrayList;

public class TaskManager {

    private ArrayList<Task> taskList = new ArrayList<>();

    public TaskManager() {
    }

    public int getTotalTasks() {
        return taskList.size();
    }

    public Task getTask(int taskIndex) {
        return this.taskList.get(taskIndex);
    }

    public void addTask(Task newTask) {
        this.taskList.add(newTask);
    }

    public void markTaskAsDone(int taskIndex) {
        taskList.get(taskIndex).markAsDone();
    }
}
