package duke.logic.commands;

public class CommandResult {
    public final String messageToBeShown;

    public CommandResult(String messageToBeShown) {
        this.messageToBeShown = messageToBeShown;
    }

    @Override
    public String toString() {
        return this.messageToBeShown;
    }

    //TODO: Create separate constructor for list vers

    //
}
