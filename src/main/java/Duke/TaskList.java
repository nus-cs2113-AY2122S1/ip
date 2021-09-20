package Duke;

import Duke.Task.Task;

import java.util.ArrayList;

public class TaskList {

    private static final ArrayList<Task> TASKS_ARRAY_LIST = new ArrayList<>();

    public TaskList() {};

    public void addAll(TaskList taskList) {
        TASKS_ARRAY_LIST.addAll(taskList.getEntireList());
    }

    public void addTask(Task task) {
        TASKS_ARRAY_LIST.add(task);
    }

    public ArrayList<Task> getEntireList() {
        return TASKS_ARRAY_LIST;
    }

    public Task getTask(int taskIndex) {
        return TASKS_ARRAY_LIST.get(taskIndex);
    }

    public void removeTask(int taskIndex) {
        TASKS_ARRAY_LIST.remove(taskIndex);
    }

    public int getSize() {
        return TASKS_ARRAY_LIST.size();
    }
}
