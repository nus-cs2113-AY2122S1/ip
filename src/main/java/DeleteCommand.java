
public class DeleteCommand extends Command{

    public static final String MESSAGE_REMOVE = "     Noted. I've removed this task:";
    public static final String MESSAGE_INVALID_DELETE = "     ☹ OOPS!!! The description of a delete cannot be empty " +
            "or out of range.";
    public DeleteCommand(int targetIndex) {
        super(targetIndex);
    }

    @Override
    public CommandResult execute() {
        try {
            final Task target = getTargetTask();
            tasksList.remove(target);
            return new CommandResult(MESSAGE_REMOVE, target, tasksList.size());
        }  catch (IndexOutOfBoundsException e) {
            return new CommandResult(MESSAGE_INVALID_DELETE);
        }
    }

}
