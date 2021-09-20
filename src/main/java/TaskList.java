import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> tasks = new ArrayList<Task>();

    public TaskList() {
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task getLastTask() {
        return tasks.get(tasks.size() - 1);
    }

    public void deleteItemFromIndex(int index) {
        tasks.remove(index);
    }

    public int getLength() {
        return tasks.size();
    }

    public Task getItemFromIndex(int index) {
        return tasks.get(index);
    }

    public ArrayList<Task> getList() {
        return tasks;
    }


}
