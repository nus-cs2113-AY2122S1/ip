package duke.command;

import duke.data.TaskList;

/**
 * Command to list tasks from both the task list and save file.
 *  A <code>List</code> command can be called with the prefix 'list' in Duke.
 */
public class ListCommand extends Command {
    public ListCommand() {
        super(CommandPrefix.LIST);
    }
    @Override
    public void saveListAndPrintDone(TaskList tasks) {
        super.saveListAndPrintDone(tasks);
        System.out.println("listing tasks!");
    }

    /**
     * Lists all tasks and their attributes
     *  e.g (Is a task a todo/ event/ deadline, due date, and if task is done)
     * @param tasks TaskList to be read
     *
     */
    @Override
    public void execute(TaskList tasks) {
        tasks.listTasks();
        saveListAndPrintDone(tasks);
    }
}
