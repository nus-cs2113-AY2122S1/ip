package Duke.Commands;

import Duke.DukeException;
import Duke.UI;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    String[] splittedInput;

    /**
     * Checks if there are characters present after the "list" command.
     *
     * @param input The entire line of command entered by the user.
     * @throws DukeException If there are characters present after the "list" command.
     */
    public ListCommand(String input) throws DukeException {
        splittedInput = input.split(" ");
        if (splittedInput.length > 1) {
            throw new DukeException("☹ OOPS!!! Sorry but list command shouldn't consist of anything" + System.lineSeparator()
                    + "\telse other than \"list\" itself.");
        }
    }

    /**
     * Prints the entire task list.
     */
    @Override
    public void execute() {
        UI.printListMessage(taskList);
    }
}
