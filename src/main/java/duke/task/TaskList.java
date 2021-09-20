package duke.task;

import java.util.ArrayList;

public class TaskList {
    private static final String MESSAGE_LIST_HEADER = "Task List:";

    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    private boolean isValidIndex(int index) {
        return (index >= 0 && index < getSize());
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task addTodoTask(String description) {
        Task task = new Todo(description);
        tasks.add(task);
        return task;
    }

    public Task addDeadlineTask(String description, String by) {
        Task task = new Deadline(description, by);
        tasks.add(task);
        return task;
    }

    public Task addEventTask(String description, String at) {
        Task task = new Event(description, at);
        tasks.add(task);
        return task;
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public int getSize() {
        return tasks.size();
    }

    public Task getTaskAt(int index) {
        if (isValidIndex(index)) {
            return tasks.get(index);
        }

        return null;
    }

    public Task removeTaskAt(int index) {
        if (isValidIndex(index)) {
            return tasks.remove(index);
        }

        return null;
    }

    @Override
    public String toString() {
        StringBuilder messageBuilder = new StringBuilder(MESSAGE_LIST_HEADER);
        for (int i = 0; i < getSize(); i += 1) {
            messageBuilder.append("\n");
            messageBuilder.append(i + 1);
            messageBuilder.append(": ");
            messageBuilder.append(getTaskAt(i));
        }

        return messageBuilder.toString();
    }
}
