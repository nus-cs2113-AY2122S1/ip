
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskList {

    private final List<Task> tasksList = new ArrayList<>();

    public TaskList() {}

    public void add(Task toAdd) {
        tasksList.add(toAdd);
    }

    public Task get(int toGet) {
        return tasksList.get(toGet);
    }

    public int size() {
        return tasksList.size();
    }

    public List<Task> listView() {
        return Collections.unmodifiableList(tasksList);
    }

    public void remove(Task toRemove) {
        tasksList.remove(toRemove);
    }

    public static class TaskNotFoundException extends Exception {}

}
