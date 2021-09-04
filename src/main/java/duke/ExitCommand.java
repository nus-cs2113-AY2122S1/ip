package duke;

public class ExitCommand extends Command {

    /**
     * Returns true as the Command type is exitCommand
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Does nothing
     *
     * @param taskManager Does not matter in this subclass implementation
     */
    @Override
    public void execute(TaskManager taskManager) {
    }
}
