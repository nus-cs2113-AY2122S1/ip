package duke;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.parser.Parser.CommandType;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import storage.Storage;

import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public static final String FILE_PATH = "data/duke.txt";

    /**
     * Instantiates an instance of Duke
     *
     * @param filePath file path of the stored data
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList(storage.loadData());
    }

    public void run() {
        storage.readFile();
        ui.printWelcomeMessage();
        runUntilExitCommand();
        ui.printExitMessage();
    }

    public static void main(String[] args) {
        new Duke(FILE_PATH).run();
    }

    private void runUntilExitCommand() {
        CommandType commandType;
        do {
            Scanner in = new Scanner(System.in);
            String userInput = in.nextLine();
            commandType = Parser.getCommandType(userInput);
            try {
                Parser.processUserInput(commandType, userInput, taskList);
                storage.saveData(taskList);
            } catch(DukeException e) {
                ui.printInvalidCommand();
            }
        } while(commandType != CommandType.BYE);
    }
}
