package duke.task;

import java.util.ArrayList;

public class TaskDoneList {
    public static ArrayList<List> doneList;

    public TaskDoneList() {
        doneList = new ArrayList<>();
    }

    public List getDoneTask(int taskId) {
        return doneList.get(taskId);
    }

    public boolean addDoneTask(List task) {
        return doneList.add(task);
    }

    public List removeDoneTask(int taskId) {
        return doneList.remove(taskId);
    }

    public int doneListSize() {
        return doneList.size();
    }

    @Override
    public String toString() {
        return String.valueOf(doneList);
    }
}
