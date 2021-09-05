package command;

public class AddTaskCommand extends UserCommand {
    private String addCommand;

    public AddTaskCommand (String addCommand, TaskList tasks) {
        super(tasks);
        this.addCommand = addCommand;
    }

    @Override
    public void execute () throws TaskEmptyException, TimeMissingException {
        int totalNumberOfTasks;
        totalNumberOfTasks = tasks.addList(this.addCommand);
        System.out.println("     Got it. I've added this task: ");
        System.out.println("     Now you have " + totalNumberOfTasks +
                ((totalNumberOfTasks > 1) ? " tasks" : " task") + " in the list.");

    }
}
