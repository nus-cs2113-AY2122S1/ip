package duke.logic.commands;

/**
 *  Represents the result of the execution of commands. Contains a String of message that describes the result of the
 *  execution.
 */
public class CommandResult {
    public final String messageToBeShown;

    public CommandResult(String messageToBeShown) {
        this.messageToBeShown = messageToBeShown;
    }

    @Override
    public String toString() {
        return this.messageToBeShown;
    }

}
