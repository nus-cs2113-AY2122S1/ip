package shikabot.command;

public class ListCommand extends Command{

    public ListCommand() {
    }

    /**
     * Function that prints the list of all tasks.
     */
    public void execute() {
        ui.printTasks(taskList);
    }
}
