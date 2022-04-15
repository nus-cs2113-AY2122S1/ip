package duke.command;

import duke.common.Messages;

/**
 * Bye command that will exit the program.
 */
public class ByeCommand extends Command {

    final public static String COMMAND_WORD = "bye";
    final public static String MESSAGE_FORMAT = COMMAND_WORD + TAG_NO_FORMAT;

    /**
     * Method to check if any given command is of type ByeCommand as ByeCommand will trigger a program termination.
     *
     * @param command Given command object to be tested.
     * @return Whether the given command object is of type ByeCommand.
     */
    public static boolean isExit(Command command) {
        return command instanceof ByeCommand;
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(Messages.MESSAGE_COMMAND_BYE);
    }
}
