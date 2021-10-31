public class SwitchCommand extends Command {
    /** Determines if need to switch to ECHO Mode */
    protected int toEcho = 0;

    /**
     * Switches toEcho to 1 so as to switch to ECHO mode.
     *
     * @param tl TaskList object storing all user-created tasks.
     */
    public void execute(TaskList tl) {
        toEcho = 1;
    }

    public SwitchCommand() {
    }
}
