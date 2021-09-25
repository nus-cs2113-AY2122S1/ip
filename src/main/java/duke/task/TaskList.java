package duke.task;

import java.util.ArrayList;

public class TaskList {
    public static ArrayList<List> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    public TaskList(ArrayList<List> list) {
        this.list = list;
    }

    public List getTask(int taskId) {
        return list.get(taskId);
    }

    public boolean addTask(List task) {
        return list.add(task);
    }

    public List removeTask(int taskId) {
        return list.remove(taskId);
    }

    public int listSize() {
        return list.size();
    }
    @Override
    public String toString() {
        return String.valueOf(list);
    }
}
