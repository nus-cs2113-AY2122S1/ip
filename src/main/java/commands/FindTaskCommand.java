package commands;

import exceptions.DukeException;
import tasks.TaskList;

public class FindTaskCommand extends UserCommand{
    private String keyword;

    public FindTaskCommand(TaskList tasks, String keyword) {
        super(tasks);
        this.keyword = keyword;
    }

    @Override
    public String execute () throws DukeException {
        String result = "     Here are the matching tasks in your list:\n";
        result += this.tasks.findTask(keyword).listTasks();
        return result;
    }
}
