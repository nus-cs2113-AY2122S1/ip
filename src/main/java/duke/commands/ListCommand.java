package duke.commands;

public class ListCommand extends Command {

    private static final String LIST_TASKS_MESSAGE = "Wow! I found these tasks in your list:";

    public ListCommand() {
        super();
    }

    @Override
    public CommandResult executeCommand() {
        String listOfTasks = taskManager.listTasks();
        CommandResult result = new CommandResult(LIST_TASKS_MESSAGE + listOfTasks);
        return result;
    }
}
