package duke;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.CommandException;
import duke.file.FileHandler;
import duke.parser.Parser;
import duke.parser.ParserException;
import duke.task.TaskManager;
import duke.ui.Ui;


public class Duke {

    private Ui ui;
    private FileHandler fileHandler;
    private TaskManager taskManager;

    private String fileName;
    private String fileDirectory;

    public Duke(String fileDirectory, String fileName) {
        this.fileName = fileName;
        this.fileDirectory = fileDirectory;
        ui = new Ui();
        taskManager = new TaskManager();

        //Load data from file into task list
        fileHandler = new FileHandler(fileDirectory);
        ui.printFileLoadingMessage(fileName);
        try {
            taskManager.processContentsFromFile(fileHandler.load(fileName));
        } catch (DukeException e) {
            //File name not found
            ui.printDukeExceptionMessage(e);
        }
        ui.printFileLoadingDoneMessage();
    }

    /**
     * Main function to run the Duke bot. Provided with hardcoded data file's directory and its name. This data file
     * stores saves task list from previous runs.
     *
     * @param args Arguments from console input
     */
    public static void main(String[] args) {
        new Duke("data", "duke.txt").run();
    }

    /**
     * Perform the execution of a command object that is given from the parser.
     *
     * @param command
     */
    private void executeCommand(Command command) {
        try {
            command.setTaskManager(taskManager);
            command.execute();
            ui.printLine();
            if (command.hasDataChange()) {
                fileHandler.writeToFile(fileName, taskManager.toString());
            }
        } catch (CommandException e) {
            //Fatal error, Task Manager does not exist
            ui.printCommandExceptionMessage(e);
        } catch (DukeException e) {
            //File IOException error
            ui.printDukeExceptionMessage(e);
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
                ui.printParserExceptionMessage(e);
            }
        } while (!ByeCommand.isExit(command));
        ui.printExitMessage();
    }

}
