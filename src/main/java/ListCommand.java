

import java.util.List;

public class ListCommand extends Command{

    public static final String MESSAGE = "list";

    @Override
    public CommandResult execute() {
        List<Task> allTasks = tasksList.listView();
        return new CommandResult(MESSAGE, allTasks, tasksList.size());
    }
}
