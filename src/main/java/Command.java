public abstract class Command {

    /**
     * Abstract method that runs user input commands.
     *
     * @throws DukeException If exception is thrown by Command subclasses.
     */
    public abstract void runCommand() throws DukeException;

    /**
     * Checks if the exit command is being called.
     *
     * @return True if Command subclass is exitCommand.
     *         False if other Command subclasses.
     */
    public boolean isExitCommand() {
        return false;
    };
}
