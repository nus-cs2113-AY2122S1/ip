package commands;

import tasks.Task;
import tasks.TaskList;
import exceptions.TaskEmptyException;
import exceptions.TimeMissingException;

public class AddTaskCommand extends UserCommand {
    private String addCommand;

    public AddTaskCommand (String addCommand, TaskList tasks) {
        super(tasks);
        this.addCommand = addCommand;
    }

    @Override
    public String execute () throws TaskEmptyException, TimeMissingException {
        Task newTask = tasks.addList(this.addCommand);
        int totalNumberOfTasks = tasks.getTotalTaskNumber();

        String result = "     Got it. I've added this task: \n";
        result += "       " + newTask;
        result += "\n     Now you have " + totalNumberOfTasks +
                ((totalNumberOfTasks > 1) ? " tasks" : " task") + " in the list.\n";
        return result;
    }
}
