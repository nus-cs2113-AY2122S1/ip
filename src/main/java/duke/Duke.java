package duke;

import duke.command.Command;
import duke.command.CommandResult;

import duke.ui.DukeInterface;
import duke.local.DataManager;
import duke.task.TaskManager;
import duke.parser.Parser;

import java.util.Scanner;

public class Duke {

    private final Scanner in;
    private final DukeInterface dukeUi;
    private final DataManager dataManager;
    private TaskManager taskManager;
    private final Parser parser;

    private final String FILE_PATH = "data/duke.txt";

    public Duke() {
        in = new Scanner(System.in);
        dukeUi = new DukeInterface();
        dataManager = new DataManager(FILE_PATH);
        taskManager = new TaskManager();
        parser = new Parser();

        dukeUi.printLogo();
        taskManager.setTasks(dataManager.getLoadedTaskList());
    }

    public String readInput() {
        dukeUi.printUserName();
        dukeUi.printCursor();
        String input = in.nextLine();
        return input;
    }

    public CommandResult runCommand(Command userCommand) {
        CommandResult commandResult = null;
        try {
            commandResult = userCommand.executeCommand();
        } catch (Exception e) {
            commandResult = new CommandResult(e.toString(), false, false);
        }
        return commandResult;
    }

    public void saveTaskListChangesIfAny (CommandResult commandResult) {
        if (commandResult.getIsModified() == true) {
            taskManager = commandResult.getTaskManager();
            dataManager.writeToFile(taskManager.getTasks());
        }
    }

    public void startDuke() {

        dukeUi.printWelcomeMessage();

        Command userCommand;
        CommandResult commandResult = null;

        do {

            String userInput = readInput();

            userCommand = parser.parseCommand(taskManager, userInput);

            commandResult = runCommand(userCommand);

            saveTaskListChangesIfAny(commandResult);

            dukeUi.printDukeMessage(commandResult.getDukeMessage());

        } while (commandResult.getIsExited() != true);

    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.startDuke();
    }

}
