package commands;

import tasks.Task;
import tasks.TaskList;

/**
 * A class to handle commands of adding tasks into the task list. Types of tasks can be Todo, Deadline, and Event.
 */
public class AddTaskCommand extends UserCommand {
    private Task newTask;

    public static String giveHelp() {
        return String.format("     The correct format of the command to ask a task should be:\n     ");
    }

    public AddTaskCommand(Task newTask, TaskList tasks) {
        super(tasks);
        this.newTask = newTask;
    }


    @Override
    public String execute () {
        tasks.addTask(this.newTask);
        int totalNumberOfTasks = tasks.getTotalTaskNumber();

        return String.format("     Got it. I've added this task: \n       %s" +
                "\n     Now you have %d %s in the list.\n", newTask, totalNumberOfTasks,
                (totalNumberOfTasks > 1) ? "tasks" : "task");
    }
}
