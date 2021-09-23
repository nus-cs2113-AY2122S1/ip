package karen.tasklist;

import karen.tasklist.task.Task;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void addTask(Task task){
        this.taskList.add(task);
    }

    public int getSize() {
        return this.getTaskList().size();
    }

    public void removeTask(int taskIndex) {
        this.taskList.remove(taskIndex);
    }
}
