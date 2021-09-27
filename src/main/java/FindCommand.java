import java.util.ArrayList;
import java.util.List;

public class FindCommand extends Command{

    private final String toFind;
    public static final String MESSAGE = "find";

    /**
     * Constructor of the FindCommand class.
     *
     * @param target the description of tasks needed to be found.
     */
    public FindCommand(String target) {
        this.toFind = target.substring(5);
    }

    /**
     * Executes the Find command.
     *
     * @return the result of find command includes the message shown to user and the relevant tasks found.
     */
    @Override
    public CommandResult execute() {
        List<Task> allTasks = tasksList.listView();
        List<Task> findTasks = new ArrayList<>();
        for(Task task: allTasks) {
            if (task.description.contains(toFind)) {
                findTasks.add(task);
            }
        }
        return new CommandResult(MESSAGE, findTasks);
    }
}
