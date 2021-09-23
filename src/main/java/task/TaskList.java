package task;

import parser.Parser;
import java.io.IOException;
import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> tasks = new ArrayList<>();

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getTaskCount() {
        return tasks.size();
    }


    /**
     * Adds a new task to the full list of tasks stored by taro based on whether it is a todo, deadline or event.
     * Increments the total count of tasks.
     *
     * @param taskName the description of the task to be added as specified by the user
     * @param date the date or time (either deadline or event date) to be attached to the task
     * @param taskType either "deadline" or "event", used to indicate whether the task is a deadline or event
     * @return the task.Task instance that has been added
     */
    public Task addTask(String taskType, String taskName, String date) {
        Task newTask = null;
        switch(taskType) {
        case Parser.COMMAND_TODO:
            newTask = new Todo(taskName);
            tasks.add(newTask);
            break;
        case Parser.COMMAND_DEADLINE:
            newTask = new Deadline(taskName, date);
            tasks.add(newTask);
            break;
        case Parser.COMMAND_EVENT:
            newTask = new Event(taskName, date);
            tasks.add(newTask);
            break;
        default:
            break;
        }
        return newTask;
    }

    /**
     * Marks the task at taskIndex in the list as done
     *
     * @param taskIndex the (index + 1) of the task to be marked done in tasks
     */
    public Task markAsDone(int taskIndex) {
        Task doneTask = tasks.get(taskIndex);
        doneTask.setDone();
        return doneTask;
    }

    public Task deleteTask(int taskIndex) throws IOException {
        Task toDelete = tasks.get(taskIndex);
        tasks.remove(toDelete);
        return toDelete;
    }

}
