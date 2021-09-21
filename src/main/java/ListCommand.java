

import java.util.List;

public class ListCommand extends Command{

    public static final String MESSAGE = "list";

    /**
     * Executes the ListCommand.
     *
     * @return the result of list command includes the message shown to user, the relevant tasks to list
     * and total number of tasks.
     */
    @Override
    public CommandResult execute() {
        List<Task> allTasks = tasksList.listView();
        return new CommandResult(MESSAGE, allTasks, tasksList.size());
    }
}
