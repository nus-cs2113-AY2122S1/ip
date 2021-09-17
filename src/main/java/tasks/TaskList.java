package tasks;

import exceptions.TaskEmptyException;
import exceptions.TimeMissingException;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class TaskList implements Serializable {
    private List<Task> tasks = new ArrayList<>();

    public Task addList(String userInput) throws TimeMissingException, TaskEmptyException {
        Task newTask;
        newTask = getTask(userInput);
        this.tasks.add(newTask);
        return newTask;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

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

    private void add(Task e) {
        this.tasks.add(e);
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
            if (t.getTaskName().contains(keyword)) {
                satisfiedTasks.add(t);
            }
        }
        return satisfiedTasks;
    }



}
