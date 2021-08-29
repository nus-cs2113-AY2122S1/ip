package shikabot.command;

public class DeleteCommand extends Command {

    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute() {
        try {
            ui.printDeleteTaskMessage(tasks, index);
            tasks.remove(index);
            ui.printTaskCount(tasks.size());
        } catch (IndexOutOfBoundsException e) {
            ui.printInvalidTaskMessage();
        }
    }
}
