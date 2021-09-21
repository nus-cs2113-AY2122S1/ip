package IzzIbot.command;

import IzzIbot.TaskList;
import IzzIbot.Ui;

public class FindCommand extends Command {

    private String keyword;

    public FindCommand(Ui ui, TaskList tasks, String keyword) {
        super(ui, tasks);
        this.keyword = keyword;
    }

    public void execute() {
        String text = "Here are the matching tasks in your list that contains [" + keyword + "]:\n";
        ui.printWithLines(text + tasks.search(keyword).listTasks());
    }
}
