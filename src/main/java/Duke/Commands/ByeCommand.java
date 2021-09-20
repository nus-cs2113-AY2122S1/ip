package Duke.Commands;

import Duke.DukeException;
import Duke.Task.Task;

import java.util.ArrayList;

public class ByeCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    String[] splittedInput;

    public ByeCommand(String input) throws DukeException {
        splittedInput = input.split(" ");
        if (splittedInput.length > 1) {
            throw new DukeException("☹ OOPS!!! Sorry but bye command shouldn't consist of anything else other than \"bye\" itself.");
        }
    }

    @Override
    public void execute(ArrayList<Task> tasksList) {
    }

}
