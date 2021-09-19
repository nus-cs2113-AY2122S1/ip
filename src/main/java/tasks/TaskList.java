package tasks;

import java.util.List;
import java.util.ArrayList;

/**
 * <h1>Stores all tasks of a user</h1>
 *
 */
public class TaskList {
    private List<Task> tasks = new ArrayList<>();


    /**
     * Adds a task into the list with a Task object.
     *
     * @param task A Task object to be inserted into the list
     * @return Nothing
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * List all the tasks stored as plain text.
     *
     * @return String The plain text describes all tasks stored.
     */
    public String listTasks() {
        String tasklist = "";
        for (int i = 0; i < tasks.size(); i++) {
            tasklist += "     " + (i + 1) + "." + tasks.get(i) + "\n";
        }
        return tasklist;
    }

    public int getTotalTaskNumber() {
        return this.tasks.size();
    }

    public Task markAsDone(int index) throws IndexOutOfBoundsException{
        Task completedTask = this.tasks.get(index);
        completedTask.setCompleted();
        return completedTask;
    }

    public Task deleteTask(int index) throws IndexOutOfBoundsException {
        return this.tasks.remove(index - 1);
    }

    public String save() {
        String result = "";
        for (Task task : tasks) {
            result += task.save();
        }
        return result;
    }

    public TaskList findTask(String keyword) {
        TaskList satisfiedTasks = new TaskList();
        for (Task t: tasks) {
            if (t.searchKeyword(keyword)) {
                satisfiedTasks.addTask(t);
            }
        }
        return satisfiedTasks;
    }

    public String sort() {
        tasks.sort((r1, r2) -> {
            if (r1.getTime() == null && r2.getTime() != null) {
                return 1;
            }

            if (r2.getTime() == null && r1.getTime() != null) {
                return -1;
            }

            if (r1.getTime() != null && r2.getTime() != null) {
                return r1.getTime().compareTo(r2.getTime());
            }

            return r1.getTaskName().compareTo(r2.getTaskName());
        });

        return this.listTasks();

    }



}
