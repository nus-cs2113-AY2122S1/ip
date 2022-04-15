package herrekt.taskmanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;

    /**
     * Initialise a Task List to manage the list of current tasks.
     *
     * @param tasks The list of current tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }
    
    /** Initialise a Task List to containing an empty list of tasks. */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public int getSize() {
        return tasks.size();
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public void delete(int taskNumber) {
        int index = taskNumber - 1;
        this.tasks.remove(index);
    }

    private int getTaskNumber(Task task) {
        int counter = 1;
        for (int i = 0; i < this.getSize(); i++) {
            if (task.equals(this.tasks.get(i))) {
                return counter;
            } else {
                counter += 1;
            }
        }
        return -1;
    }

    /**
     * Returns true if the task was not done, false if otherwise.
     * Also sets the task's done status to true if it was false from the start.
     *
     * @param taskNumber Number of the task to be marked as done.
     * @return True if the task is not done at the start.
     */
    public boolean markAsDone(int taskNumber) {
        int index = taskNumber - 1;
        Task toMark = this.getTask(index);
        if (toMark.isDone()) {
            return false;
        } else {
            toMark.setDone();
            return true;
        }
    }

    /**
     * Returns the list of current tasks in a format that can be saved and loaded in a .txt file.
     * Converts the task list into a format that is recognizable when loaded from a .txt file.
     *
     * @return The task list in the format to be saved.
     */
    public StringBuilder convertTaskListToSaveFormat() {
        StringBuilder toSaveToFile = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++ ) {
            if (i == this.tasks.size() - 1) {
                toSaveToFile.append((this.tasks.get(i)).toSave());
            } else {
                toSaveToFile.append((this.tasks.get(i)).toSave());
                toSaveToFile.append("\n");
            }
        }
        return toSaveToFile;
    }

    /**
     * Returns a map of tasks within the search parameters and their number on the task list.
     *
     * @param phrase The parameter of the filter.
     * @return A HashMap of tasks in the form of TaskNumber, Task.
     */
    public HashMap<Integer, Task> search(String phrase) {
        switch (phrase) {
        case "t":
            return searchForTodo();
        case "d":
            return searchForDeadline();
        case "e":
            return searchForEvent();
        case "done":
            return searchForDone();
        case "undone":
            return searchForNotDone();
        default:
            return defaultSearch(phrase);
        }
    }

    private HashMap<Integer, Task> defaultSearch(String phrase) {
        HashMap<Integer, Task> results = new HashMap<>();
        for (Task task : this.tasks) {
            if (task.getDescription().toLowerCase().contains(phrase)) {
                results.put(this.getTaskNumber(task), task);
            }
        }
        return results;
    }

    private HashMap<Integer, Task> searchForTodo() {
        HashMap<Integer, Task> results = new HashMap<>();
        for (Task task : this.tasks) {
            if (task instanceof Todo) {
                results.put(this.getTaskNumber(task), task);
            }
        }
        return results;
    }

    private HashMap<Integer, Task> searchForDeadline() {
        HashMap<Integer, Task> results = new HashMap<>();
        for (Task task : this.tasks) {
            if (task instanceof Deadline) {
                results.put(this.getTaskNumber(task), task);
            }
        }
        return results;
    }

    private HashMap<Integer, Task> searchForEvent() {
        HashMap<Integer, Task> results = new HashMap<>();
        for (Task task : this.tasks) {
            if (task instanceof Event) {
                results.put(this.getTaskNumber(task), task);
            }
        }
        return results;
    }

    private HashMap<Integer, Task> searchForDone() {
        HashMap<Integer, Task> results = new HashMap<>();
        for (Task task : this.tasks) {
            if (task.isDone()) {
                results.put(this.getTaskNumber(task), task);
            }
        }
        return results;
    }

    private HashMap<Integer, Task> searchForNotDone() {
        HashMap<Integer, Task> results = new HashMap<>();
        for (Task task : this.tasks) {
            if (!task.isDone()) {
                results.put(this.getTaskNumber(task), task);
            }
        }
        return results;
    }

    @Override
    public String toString() {
        StringBuilder toReturn = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            if (i == tasks.size() - 1) {
                toReturn.append(i + 1)
                        .append(". ")
                        .append(tasks.get(i).toString());
                break;
            }
            toReturn.append(i + 1)
                    .append(". ")
                    .append(tasks.get(i).toString())
                    .append("\n");
        }
        return toReturn.toString();
    }

}
