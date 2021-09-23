package duke.tasklist;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();

    public int getListSize() {
        return taskList.size();
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public void markDone(int index) {
        taskList.get(index).markAsDone();
    }

    public void deleteTask(int index) {
        taskList.remove(index);
    }

    public ArrayList<Task> findTask(String task) {
        int size = this.getListSize();
        ArrayList<Task> tasksFound = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            Task currTask = this.getTask(i);

            if (currTask.getDescription().contains(task)) {
                tasksFound.add(currTask);
            }
        }

        return tasksFound;
    }
}
