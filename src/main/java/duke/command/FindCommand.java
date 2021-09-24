package duke.command;

/**
 * Find command that will filter out task that its description contains the specified keyword.
 */
public class FindCommand extends Command {

    final public static String COMMAND_WORD = "find";
    final public static String MESSAGE_FORMAT = COMMAND_WORD + " <keyword>";

    private String keyword;

    public FindCommand(String keyword) {
        super();
        this.keyword = keyword;
    }

    /**
     * Method to execute the command by calling the TaskManager to perform its specified operation.
     */
    @Override
    public void execute() {
        taskManager.findTask(keyword);
    }
}
