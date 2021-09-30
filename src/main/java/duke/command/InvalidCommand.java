package duke.command;

import duke.DukeException;

public class InvalidCommand implements Command {
    private static final CommandType type = CommandType.INVALID;

    /**
     * Throws DukeException for running an invalid command
     *
     * @param printMessage Print message to user on executing command. Value is irrelevant
     * @throws DukeException Thrown for running an invalid command
     */
    @Override
    public void run(boolean printMessage) throws DukeException {
        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * @return Type of command
     */
    @Override
    public CommandType getType() {
        return type;
    }
}
