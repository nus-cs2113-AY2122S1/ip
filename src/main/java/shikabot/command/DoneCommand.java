package shikabot.command;

public class DoneCommand extends Command {

    private final int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute() {
        try {
            tasks.get(index).markAsDone();
            ui.printDoneTaskMessage(tasks, index);
        } catch (IndexOutOfBoundsException e) {
            ui.printInvalidTaskMessage();
        }
    }
}
