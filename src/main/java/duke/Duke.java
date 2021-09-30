package duke;

import duke.command.Command;
import duke.parser.Parser;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.storage.TaskList;
import duke.text.Text;
import duke.ui.Ui;

public class Duke extends Text {

    private TaskList taskList;
    private final Storage storage;
    private final Ui ui;

    /**
     * A constructor to set up ui, taskList and storage.
     *
     * @param filePath pathway of storage file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            taskList = new TaskList(storage.openFile());
        } catch (DukeException e) {
            Ui.printWithLine(e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Executes the duke program and setting up the parser.
     * Takes in user input until exit command is input from user.
     */
    public void run() {
        Parser parseCommands = new Parser(taskList, ui, storage);
        ui.printIntroduction();
        String userInput = ui.getUserCommand();

        Command userCommand;
        while (true) {
            try {
                userCommand = parseCommands.parseCommand(userInput);
                userCommand.execute();
                storage.saveFile(taskList);
            } catch (DukeException e) {
                Ui.printWithLine(e.getMessage());
            }
            userInput = ui.getUserCommand();
        }
    }

    public static void main (String[] args) {
        new Duke(FILE_PATH).run();
    }
}