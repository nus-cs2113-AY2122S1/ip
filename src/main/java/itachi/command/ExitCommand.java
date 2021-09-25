package itachi.command;

import itachi.Storage;
import itachi.TaskList;
import itachi.exception.ItachiException;

public class ExitCommand extends Command {

    public static boolean isExit() {
        return isOver = true;
    }

    @Override
    public void executeUserCommand(TaskList taskList, Storage storage) throws ItachiException {
    }
}
