package duke.command;

import duke.DukeException;

public class ExitCommand implements Command{
    @Override
    public String run() throws DukeException {
        String resultMsg = "So long!";
        return resultMsg;
    }
}
