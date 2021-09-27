package Duke.Commands;

import Duke.DukeException;

public class ByeCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    String[] splittedInput;

    /**
     * Checks if there are characters present after the "bye" command.
     *
     * @param input The entire line of command entered by the user.
     * @throws DukeException If there are characters present after the "bye" command.
     */
    public ByeCommand(String input) throws DukeException {
        splittedInput = input.split(" ");
        if (splittedInput.length > 1) {
            throw new DukeException("☹ OOPS!!! Sorry but bye command shouldn't consist of anything else other than \"bye\" itself.");
        }
    }

    @Override
    public void execute() {
    }

}
