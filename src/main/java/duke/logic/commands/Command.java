package duke.logic.commands;


import duke.data.task.Task;
import duke.data.task.TaskList;

import static duke.ui.Ui.EMPTY;
import static duke.ui.Ui.LS;

/**
 * Abstract class used to represent executable Commands
 * All Commands can be executed to return a CommandResult
 */
public abstract class Command {
    protected TaskList tasks;
    public abstract CommandResult execute();

    /**
     * Provides the task list that the command will operate on
     * @param tasks TaskList class containing current tasks
     */
    public void setTasks(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns all the tasks in string form
     */
    public String getStringOfAllTasks() {
        String stringOfAllTasks = EMPTY;
        int taskNum = 1;
        for (Task task : this.tasks.getTasks()) {
            stringOfAllTasks = stringOfAllTasks + taskNum + "." + task.toString() + LS;
            taskNum++;
        }
        return stringOfAllTasks;
    }

}
