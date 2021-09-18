package duke;

import duke.commands.Command;
import duke.commands.CommandResult;
import duke.commands.ExitCommand;
import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.parser.Parser;
import java.util.Scanner;

public class Duke {

    private Ui ui = new Ui();
    private Storage storage = new Storage();
    private Scanner scanner = new Scanner(System.in);

    private void loadStorage() {
        try {
            storage.loadStorageToTaskList();
        } catch (DukeException exception) {
            ui.showErrorMessage(exception);
        }
    }

    private void updateStorage() {
        try {
            storage.loadTaskListToStorage();
        } catch (DukeException exception) {
            ui.showErrorMessage(exception);
        }
    }

    private String readUserInput() {
        return scanner.nextLine().trim();
    }

    private void executeUserInputs() {

        while (true) {
            try {
                String userInputString = readUserInput();
                Command command = Parser.parseCommand(userInputString);
                CommandResult result = command.executeCommand();
                ui.showFeedbackToUser(result);
                if (command instanceof ExitCommand) {
                    break;
                }
            } catch (DukeException exception) {
                ui.showErrorMessage(exception);
            } finally {
                updateStorage();
            }
        }
    }

    public void run() {
        ui.showGreetMessage();
        loadStorage();
        executeUserInputs();
    }
}
