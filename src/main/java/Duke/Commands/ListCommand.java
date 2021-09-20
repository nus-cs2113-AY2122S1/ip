package Duke.Commands;

import Duke.DukeException;
import Duke.UI;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    String[] splittedInput;

    public ListCommand(String input) throws DukeException {
        splittedInput = input.split(" ");
        if (splittedInput.length > 1) {
            throw new DukeException("☹ OOPS!!! Sorry but list command shouldn't consist of anything else other than \"list\" itself.");
        }
    }

    @Override
    public void execute() {
        UI.printListMessage(taskList);
    }
}
