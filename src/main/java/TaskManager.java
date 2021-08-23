import java.util.ArrayList;

public class TaskManager {

    private ArrayList<Task> taskList = new ArrayList<>();

    public TaskManager() {
    }

    public void addTask(Task newTask) {
        this.taskList.add(newTask);
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }
}
