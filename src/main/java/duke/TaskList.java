package duke;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int size() {
        return tasks.size();
    }

    public String saveTask(int taskIndex) {
        Task task = tasks.get(taskIndex);
        return task.saveFormat();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public Task getTask(int index) {
        try {
            return tasks.get(index);
        } catch (IndexOutOfBoundsException ex) {
            throw new IndexOutOfBoundsException("☹ OOPS!!! The list does not have that many task ><");
        }
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task doneTask(int taskIndex) {
        Task task = getTask(taskIndex);
        task.setDone();
        return task;
    }

    public Task deleteTask(int taskIndex) {
        try {
            return tasks.remove(taskIndex);
        } catch (IndexOutOfBoundsException ex) {
            throw new IndexOutOfBoundsException("☹ OOPS!!! The list does not have that many task ><");
        }
    }

    public TaskList getFilteredTask(String word) {
        ArrayList<Task> filteredTasks = new ArrayList<>();
        for(Task t: tasks) {
            if (t.getDescription().contains(word)) {
                filteredTasks.add(t);
            }
        }
        return new TaskList(filteredTasks);
    }
}

