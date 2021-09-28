public class FindCommand extends Command {
    protected static String keyword;

    public void execute(TaskList tl) {
        tl.findTasks(keyword.toLowerCase());
    }

    public FindCommand(String findCommandInput) {
        keyword = findCommandInput.substring("find ".length());
    }
}
