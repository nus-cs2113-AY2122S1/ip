package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;

public class FindCommand extends Command {
    protected String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void executeUserCommand(TaskList taskList, Storage storage) throws DukeException {
        taskList.findTask(keyword);
    }
}
