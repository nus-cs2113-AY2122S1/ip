package itachi.command;

import itachi.Storage;
import itachi.TaskList;
import itachi.exception.ItachiException;

public class FindCommand extends Command {
    protected String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void executeUserCommand(TaskList taskList, Storage storage) throws ItachiException {
        taskList.findTask(keyword);
    }
}
