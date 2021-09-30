import commands.Command;
import parser.Parser;
import storage.Storage;
import tasks.TaskList;

import TextUi.TextUi;

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
