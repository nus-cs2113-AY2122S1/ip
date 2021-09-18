package duke;

import duke.exception.ArgumentNotFoundException;
import duke.exception.InvalidCommandException;
import duke.task.TaskList;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;
import java.io.IOException;

public class Duke {

    private static final String USERNAME = "Newbie";

    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_DELETE = "delete";

    protected TaskList taskList;
    protected Storage store;
    protected Ui ui;

    protected boolean isRunning;

    public Duke(String filePath) {
        ui = new Ui(USERNAME);
        store = new Storage(filePath);
        taskList = new TaskList();
        setIsRunning(true);

        try {
            store.loadFile(taskList);
        } catch (IOException | IllegalArgumentException fileException) {
            ui.printInvalidFileInitialisationError();
            System.exit(1);
        }
    }

    public void run() {
        ui.printBanner();
        dukeMainLoop();
    }

    /**
     * Handles the user input and loop logic Calls handleCommand and terminates when isRunning is false
     */
    public void dukeMainLoop() {
        while (getIsRunning()) {
            //Printing user prompt
            ui.printPrompt();
            // Reading user input
            String userInput = ui.getUserInput();
            //Handling Exceptions
            try {
                handleCommand(userInput);
            } catch (ArgumentNotFoundException | NullPointerException errorMessage) {
                ui.printArgumentsError();
            } catch (NumberFormatException invalidParsing) {
                ui.printNumberError();
            } catch (IllegalArgumentException invalidArguments) {
                ui.printInvalidArguments(invalidArguments.getMessage());
            } catch (InvalidCommandException invalidCommand) {
                ui.printUnknownCommandError();
            } catch (IOException saveException) {
                ui.printInvalidFileError();
            }
        }
        ui.printGoodbye();
    }

    /**
     * Handles the user input and decide what actions to run
     *
     * @param command takes in a command from user
     * @throws ArgumentNotFoundException a specific delimiter was found in the arguments
     * @throws NumberFormatException     if the argument was parsed as an int but is not a number
     * @throws InvalidCommandException   the command doesn't exist
     * @throws IllegalArgumentException  the creation of arguments created for a task was not valid
     * @throws NullPointerException      no arguments were provided for a command
     * @throws IOException               when storing data to file has failed
     */
    public void handleCommand(String command)
            throws ArgumentNotFoundException, NumberFormatException,
            InvalidCommandException, IllegalArgumentException, NullPointerException,
            IOException {

        // Attempting to parse the command
        Parser parsed = new Parser(command);

        if (parsed.getCommand() == null) {
            ui.printCommandHelp();
            return;
        }

        switch (parsed.getCommand()) {
        case COMMAND_LIST:
            taskList.listTasks(ui);
            break;
        case COMMAND_BYE:
            setIsRunning(false);
            break;
        case COMMAND_DONE:
            int taskDoneIndex = parsed.getArgsAsIndex();
            taskList.markTaskAsDone(ui, taskDoneIndex);
            break;
        case COMMAND_DELETE:
            int taskDeleteIndex = parsed.getArgsAsIndex();
            taskList.deleteTask(ui, taskDeleteIndex);
            break;
        default:
            taskList.addTask(ui, parsed);
        }
        store.saveFile(taskList);
    }

    private void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    private boolean getIsRunning() {
        return isRunning;
    }

    public static void main(String[] args) {
        new Duke(null).run();
    }
}
