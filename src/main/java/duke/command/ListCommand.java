package duke.command;

public class ListCommand extends Command {

    final public static String COMMAND_WORD = "list";
    final public static String MESSAGE_FORMAT = COMMAND_WORD + TAG_NO_FORMAT;

    @Override
    public void execute() {
        taskManager.printAllTasks();
    }

}
