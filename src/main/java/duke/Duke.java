package duke;

import duke.commands.Command;
import duke.commands.CommandResult;
import duke.commands.ExitCommand;
import duke.exceptions.DukeException;
import duke.storage.DataManager;
import duke.ui.DukeUi;
import duke.parser.Parser;
import java.util.Scanner;

public class Duke {

    private static final DukeUi UI = new DukeUi();
    private static final Parser PARSER = new Parser();
    private static final DataManager DATA_MANAGER = new DataManager();
    private static final Scanner SCANNER = new Scanner(System.in);

    private void loadStorage() {
        try {
            DATA_MANAGER.loadStorageToTaskList();
        } catch (DukeException exception) {
            UI.showErrorMessage(exception);
        }
    }

    private void updateStorage() {
        try {
            DATA_MANAGER.loadTaskListToStorage();
        } catch (DukeException exception) {
            UI.showErrorMessage(exception);
        }
    }

    private String readUserInput() {
        return SCANNER.nextLine().trim();
    }

    private void executeUserInputs() {

        while (true) {
            try {
                String userInputString = readUserInput();
                Command command = PARSER.parseCommand(userInputString);
                CommandResult result = command.executeCommand();
                UI.showFeedbackToUser(result);
                if (command instanceof ExitCommand) {
                    break;
                }
            } catch (DukeException exception) {
                UI.showErrorMessage(exception);
            } finally {
                updateStorage();
            }
        }
    }

    public void run() {
        UI.showGreetMessage();
        loadStorage();
        executeUserInputs();
    }
}
