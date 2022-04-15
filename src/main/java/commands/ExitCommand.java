package commands;

import ui.TextUi;

public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "bye";
    public static final String COMMAND_DESC = "Exits the program.";

    public void execute() {
        TextUi.showExitMessage();
    }

}
