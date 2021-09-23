package duke.command;

public class FindCommand extends Command{
    final public static String COMMAND_WORD = "find";
    final public static String MESSAGE_FORMAT = COMMAND_WORD + " <keyword>";

    private String keyword;

    public FindCommand(String keyword) {
        super();
        this.keyword = keyword;
    }

    @Override
    public void execute(){
        taskManager.findTask(keyword);
    }
}
