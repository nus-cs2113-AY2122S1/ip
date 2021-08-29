package shikabot.command;

public class ListCommand extends Command{

    public ListCommand() {
    }

    @Override
    public void execute() {
        ui.printTasks(tasks);
    }
}
