package shikabot.command;

import shikabot.ui.TextUi;

public class FindCommand extends Command {

    private final String SEARCHTERM;

    public FindCommand(String str) {
        this.SEARCHTERM = str;
    }

    @Override
    public void execute() {
        TextUi.printMatchingTasks(taskList, SEARCHTERM);
    }
}
