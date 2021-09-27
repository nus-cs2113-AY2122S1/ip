package duke;

import duke.command.Command;
import duke.command.CommandResult;

import duke.ui.DukeInterface;
import duke.local.DataManager;
import duke.task.TaskManager;
import duke.parser.Parser;

import java.util.Scanner;

/**
 * <h1>Duke</h1>
 * The Duke program implements a command-line application
 * that allows users to keep track of their tasks.
 * <p>
 * Some inspiration for code abstraction and organisation were adapted
 * from the {@code addressbook-level2} Java application on GitHub.
 * <p>
 * The code for {@code addressbook-level2} can be found at the following link:
 * <a href="#{@link}">{@link https://github.com/se-edu/addressbook-level2}</a>
 *
 * @author Peh Zhenhao, Amos
 * @version 0.2
 */
public class Duke {

    private final Scanner in;
    private final DukeInterface dukeUi;
    private final DataManager dataManager;
    private TaskManager taskManager;
    private final Parser parser;

    private final String FILE_PATH = "data/duke.txt";

    /**
     * Sets up required objects needed for Duke to run.
     * Creates new instances of Scanner, DukeInterface, DataManager, TaskManager, Parser
     * and initializes any pre-existing tasks stored in duke.txt into {@code taskManager}.
     */
    public Duke() {
        in = new Scanner(System.in);
        dukeUi = new DukeInterface();
        dataManager = new DataManager(FILE_PATH);
        taskManager = new TaskManager();
        parser = new Parser();

        taskManager.setTasks(dataManager.getLoadedTaskList());
    }

    /**
     * Prints cursor for user and reads user input typed into the terminal.
     *
     * @return user's terminal input.
     */
    public String readInput() {
        dukeUi.printUserName();
        dukeUi.printCursor();
        String input = in.nextLine();
        return input;
    }

    /**
     * Executes user command and returns the appropriate command result.
     * If an exception occurs, returns a {@code CommandResult} object containing the error message.
     *
     * @param userCommand {@code Command} object to be executed.
     * @return the {@code CommandResult} object after executing the command.
     */
    public CommandResult runCommand(Command userCommand) {
        CommandResult commandResult = null;
        try {
            commandResult = userCommand.executeCommand();
        } catch (Exception e) {
            commandResult = new CommandResult(e.toString(), false, false);
        }
        return commandResult;
    }

    /**
     * Checks and saves any changes made to the user's tasklist to duke.txt.
     *
     * @param commandResult contains {@code isModified} variable to see if changes were made to the tasklist.
     */
    public void saveTaskListChangesIfAny(CommandResult commandResult) {
        if (commandResult.getIsModified() == true) {
            taskManager = commandResult.getTaskManager();
            dataManager.writeToFile(taskManager.getTasks());
        }
    }

    /**
     * Reads and executes user commands, and saves any changes made to the tasklist
     * until the {@code bye} command is entered by the user, which exits the program.
     */
    public void startDuke() {

        dukeUi.printLogo();
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
