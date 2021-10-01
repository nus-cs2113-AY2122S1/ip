package shikabot.command;

import shikabot.ui.TextUi;

public class FindCommand extends Command {

    private final String searchTerm;

    public FindCommand(String str) {
        this.searchTerm = str;
    }

    @Override
    public void execute() {
        TextUi.printMatchingTasks(taskList, searchTerm);
    }
}
