public abstract class Command {

    public abstract void runCommand() throws DukeException;

    public boolean isExitCommand() {
        return false;
    };
}
