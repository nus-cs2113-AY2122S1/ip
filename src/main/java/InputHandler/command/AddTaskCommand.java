package InputHandler.command;

import InputHandler.exception.TaskEmptyException;
import InputHandler.exception.TimeMissingException;

public class AddTaskCommand extends UserCommand {
    private String addCommand;

    public AddTaskCommand (String addCommand, TaskList tasks) {
        super(tasks);
        this.addCommand = addCommand;
    }

    @Override
    public void execute () throws TaskEmptyException, TimeMissingException {
        Task newTask = tasks.addList(this.addCommand);
        System.out.println("     Got it. I've added this task: ");
        System.out.println("       " + newTask);
        int totalNumberOfTasks = tasks.getTotalTaskNumber();
        System.out.println("     Now you have " + totalNumberOfTasks +
                ((totalNumberOfTasks > 1) ? " tasks" : " task") + " in the list.");

    }
}
