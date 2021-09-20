package duke;

import java.util.ArrayList;

public class TaskList {

    protected static ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public int getSize() {
        return tasks.size();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void addTodo(String description) {
        tasks.add(new Todo(description));
    }

    public void addDeadline(String description, String by) {
        tasks.add(new Deadline(description, by));
    }

    public void addEvent(String description, String at) {
        tasks.add(new Event(description, at));
    }

    public void completeTask(int index) {
        tasks.get(index).completeTask();
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }

    public void listTask() {
        for (int i = 0; i < tasks.size(); i++) {
            tasks.get(i).printTask(i + 1);
        }
    }

}
