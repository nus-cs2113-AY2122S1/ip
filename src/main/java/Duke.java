import commands.Command;
import parser.Parser;
import storage.Storage;
import tasks.TaskList;

import ui.TextUi;

public class Duke {
    private TextUi textUi;
    private Storage storage;
    private Parser parser;
    private TaskList taskList;

    public Duke() {
        taskList = new TaskList();
        textUi = new TextUi();
        parser = new Parser();
        storage = new Storage(taskList);
        storage.initTaskList();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Prints the welcome message.
     * Reads the user command and executes it, until the user issues the exit command.
     */
    public void run() {
        TextUi.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = textUi.getUserInput();
            Command command = parser.parseCommand(fullCommand);
            command.setTaskList(taskList);
            command.execute();
            isExit = command.isExit();
        }
    }
}
