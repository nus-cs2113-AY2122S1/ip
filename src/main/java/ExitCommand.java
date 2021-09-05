public class ExitCommand extends Command {

    /**
     * Override superclass Command to not perform any actions.
     */
    @Override
    public void runCommand() {
        // Do nothing for now
    }

    /**
     * Detects that the exit command has been called.
     *
     * @return true
     */
    @Override
    public boolean isExitCommand() {
        return true;
    }
}
