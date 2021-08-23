import java.util.ArrayList;

public class TaskManager {

    private ArrayList<Task> taskList = new ArrayList<>();

    public TaskManager() {
    }

    public void addTask(Task newTask) {
        this.taskList.add(newTask);
    }

    public String[] getIndexedTaskList() {
        String[] indexedTasks = new String[taskList.size()];
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            indexedTasks[i] = (i + 1) + ". " + task.getDescription();
        }
        return indexedTasks;
    }
}
