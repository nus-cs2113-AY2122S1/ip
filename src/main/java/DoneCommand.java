
public class DoneCommand extends Command{

    public static final String MESSAGE_DONE = "     Nice! I've marked this task as done:";
    public static final String MESSAGE_INVALID_DONE = "     ☹ OOPS!!! The description of a done cannot be empty " +
            "or out of range.";
    public DoneCommand(int targetVisibleIndex) {
        super(targetVisibleIndex);
    }

    @Override
    public CommandResult execute() {
        try {
        final Task target = getTargetTask();
        if (target instanceof Todo) {
            ((Todo) target).setDone();
        } else if (target instanceof Deadline) {
            ((Deadline) target).setDone();
        } else {
            ((Event) target).setDone();
        }
        return new CommandResult(MESSAGE_DONE, target);
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult(MESSAGE_INVALID_DONE);
        }

    }
}
