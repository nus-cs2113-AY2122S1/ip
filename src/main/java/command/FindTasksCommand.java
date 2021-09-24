package command;

import exception.AustinException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.io.IOException;

public class FindTasksCommand extends Command {
    public static final String COMMAND_KEYWORD = "find";
    private String keyword;

    public FindTasksCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AustinException, IOException {
        tasks.findTasks(keyword);
    }
}
