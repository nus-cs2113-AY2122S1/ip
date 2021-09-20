package herrekt;

import herrekt.taskmanager.Task;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    TaskList(List<Task> tasks) {
        this.tasks = tasks;

    }

    TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public List<Task> getTasks() {
        return tasks;
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

    public void delete(Task task) {
        this.tasks.remove(task);
    }

    public void delete(int taskNumber) {
        int index = taskNumber - 1;
        this.tasks.remove(index);
    }

    public void markAsDone(int taskNumber) {
        int index = taskNumber - 1;
        this.getTask(index).setDone();
    }

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

    public List<Task> search(String phrase) {
        List<Task> results = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task.getDescription().contains(phrase)) {
                results.add(task);
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
