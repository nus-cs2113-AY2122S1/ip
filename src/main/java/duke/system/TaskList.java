package duke.system;

import duke.task.Task;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void markTaskDone(int currentTaskIndex) throws IndexOutOfBoundsException {
        this.tasks.get(currentTaskIndex).markAsDone();
    }

    public String getTaskDetails(int currentTaskIndex) {
        return this.tasks.get(currentTaskIndex).toString();
    }

    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    public void addTask(Task newTask) {
        tasks.add(newTask);
    }

    public void deleteTask(int currentTaskIndex) {
        tasks.remove(currentTaskIndex);
    }

    public int getTaskListSize() {
        return tasks.size();
    }

    public ArrayList<Task> getRelevantTaskList(String keywords) {
        ArrayList<Task> relevantTasks = new ArrayList<>();
        for (int i = 0; i < this.tasks.size(); i++) {
            Task currentTask = this.tasks.get(i);
            if (currentTask.containsKeywords(keywords)) {
                relevantTasks.add(currentTask);
            }
        }
        return relevantTasks;
    }


}
