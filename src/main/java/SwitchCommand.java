public class SwitchCommand extends Command {
    protected int toEcho = 0;

    public void execute(TaskList tl) {
        toEcho = 1;
    }

    public SwitchCommand() {
    }
}
