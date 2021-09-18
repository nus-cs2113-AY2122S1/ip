package tasks;

import exceptions.TaskEmptyException;
import exceptions.TimeMissingException;

import java.util.List;
import java.util.ArrayList;

/**
 * <h1>Stores all tasks of a user</h1>
 *
 */
public class TaskList {
    private List<Task> tasks = new ArrayList<>();

    /**
     * Adds a task into the list using the add task command.
     * @param userInput A String follows the exact format of adding a task
     * @return Task The Task object that has been added the list.
     * @throws TimeMissingException Throws TimeMissingException if the time of Deadline/Event is missing.
     * @throws TaskEmptyException Throws TaskEmptyException if the content of a task is missing.
     */
    public Task addTask (String userInput) throws TimeMissingException, TaskEmptyException {
        Task newTask;
        newTask = getTask(userInput);
        this.tasks.add(newTask);
        return newTask;
    }

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

    public Task markAsDone(int index) throws IndexOutOfBoundsException {
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

    private Task getTask(String userInput) throws TaskEmptyException, TimeMissingException {
        String taskType, taskName, deadline;
        int taskTypeIndex = userInput.indexOf(" ");
        int deadlineIndex = userInput.lastIndexOf("/");

        try {
            taskType = userInput.substring(0, taskTypeIndex);
        } catch (StringIndexOutOfBoundsException e) {
            throw new TaskEmptyException(userInput);
        }

        if (taskType.equals("todo")) {
            taskName = userInput.substring(taskTypeIndex + 1);
            return new Todo(taskName, false);
        }

        if (deadlineIndex == -1) {
            throw new TimeMissingException(taskType);
        }

        deadline = userInput.substring(deadlineIndex + 1);
        taskName = userInput.substring(taskTypeIndex, deadlineIndex);
        if (taskType.equals("deadline")) {
            return new Deadline(taskName, deadline, false);
        }
        return new Event(taskName, deadline, false);
    }


}
