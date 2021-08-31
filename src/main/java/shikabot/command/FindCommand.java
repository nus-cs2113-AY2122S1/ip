package shikabot.command;

public class FindCommand extends Command {

    private final String SEARCHTERM;

    public FindCommand(String str) {
        this.SEARCHTERM = str;
    }

    @Override
    public void execute() {
        ui.printMatchingTasks(taskList, SEARCHTERM);
    }
}
