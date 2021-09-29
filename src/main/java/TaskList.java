import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList = new ArrayList<>();

    public TaskList() {}

    public void add(Task toAdd) {
        taskList.add(toAdd);
    }

    public Task get(int toGet) {
        return taskList.get(toGet);
    }

    public void remove(int toRemove) {
        taskList.remove(toRemove);
    }

    public int size() {
        return taskList.size();
    }
}
