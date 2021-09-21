import java.util.ArrayList;
import java.util.List;

public class FindCommand extends Command{

    private final String toFind;
    public static final String MESSAGE = "find";
    public FindCommand(String target) {
        this.toFind = target.substring(5);
    }

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
