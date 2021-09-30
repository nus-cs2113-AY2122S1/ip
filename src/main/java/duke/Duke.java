package duke;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.CommandException;
import duke.command.CommandResult;
import duke.storage.FileHandler;
import duke.parser.Parser;
import duke.parser.ParserException;
import duke.task.TaskManager;
import duke.task.TaskManagerException;
import duke.ui.Ui;

/**
 * Duke main class, the program should start running from here as the main.
 */
public class Duke {

    private Ui ui;
    private FileHandler fileHandler;
    private TaskManager taskManager;

    private String fileName;
    private String fileDirectory;

    /**
     * Constructor that initialise components the duke need to use as well as load data from given file into task list.
     *
     * @param fileDirectory File directory that contains the text file of saved task list if any
     * @param fileName      Supplied filename of the text file of saved task list
     */
    public Duke(String fileDirectory, String fileName) {
        this.fileName = fileName;
        this.fileDirectory = fileDirectory;
        ui = new Ui();
        taskManager = new TaskManager();

        //Load data from file into task list
        fileHandler = new FileHandler(fileDirectory);
        ui.printFileLoadingMessage(fileName);
        try {
            taskManager.processContentsFromFile(fileHandler.load(fileName), ui);
        } catch (DukeException e) {
            //File name not found
            ui.printMessage(e.toString());
        }
        ui.printFileLoadingDoneMessage();
    }

    /**
     * Main function to run the Duke bot. Provided with hardcoded data file's directory and its name. This data file
     * stores saved task list from previous runs.
     *
     * @param args Arguments from console input (not used)
     */
    public static void main(String[] args) {
        new Duke("data", "duke.txt").run();
    }

    /**
     * Perform the execution of a command object that is validated and given by the parser. The parser is a command
     * handler that ensures the user input are valid existing commands of duke. Upon changes detected to the task list,
     * the current task list will be overwritten on to the provided filename text file in order to save its contents.
     *
     * @param command Command object that was translated from user input
     */
    private void executeCommand(Command command) {
        try {
            command.setTaskManager(taskManager);
            CommandResult result = command.execute();
            ui.printMessage(result.getFeedbackToUser());
            if (command.hasDataChange()) {
                fileHandler.writeToFile(fileName, taskManager.toString());
            }
        } catch (CommandException e) {
            //Fatal error, Task Manager does not exist
            ui.printMessage(e.toString());
        } catch (DukeException e) {
            //File IOException error
            ui.printMessage(e.toString());
        } catch (TaskManagerException e) {
            ui.printMessage(e.toString());
        } catch (NullPointerException e) {
            //Fatal error, command input is null
            ui.printMessage(e.getMessage());
        }
    }

    /**
     * Main function to get user inputs and parse in through a parser to determine the correctness of the command. Upon
     * receiving invalid commands, format of command will be printed unless command does not even exist in which it will
     * print an invalid command error.
     */
    private void run() {
        ui.printWelcomeMessage();
        Parser parser = new Parser();
        Command command = null;
        do {
            try {
                command = parser.parseCommand(ui.getUserInput());
                executeCommand(command);
            } catch (ParserException e) {
                ui.printMessage(e.toString());
            }
        } while (!ByeCommand.isExit(command));
        ui.printExitMessage();
    }

}
