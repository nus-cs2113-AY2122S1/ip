package duke.tasklist_new;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList(){
        tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks(){
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasksList) {
        tasks = tasksList;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int index) {
        Task task = tasks.get(index);
        tasks.remove(task);
        return task;
    }

    public Boolean isDone(int index) {
        Task task = tasks.get(index);
        return task.isDone();
    }

    public void markDone(int index) {
        Task task = tasks.get(index);
        tasks.get(index).markAsDone();
    }
}
