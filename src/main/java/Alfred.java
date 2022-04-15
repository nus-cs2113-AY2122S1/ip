import alfred.command.Command;
import alfred.exception.FileErrorException;
import alfred.parser.Parser;
import alfred.storage.Storage;
import alfred.storage.TaskEncoder;
import alfred.task.TaskList;
import alfred.ui.TextUi;

public class Alfred {
    private TextUi textUi;
    private Storage storage;
    private Parser parser;
    private String filePath;
    private TaskList taskList;

    /**
     * This constructor is used to set up the Alfred chat-bot by instantiating a new
     * TextUi, Storage (with the specified filePath), and Parser. It will attempt to
     * either load an existing storage file, or create a new one.
     * @param filePath This is the filePath specified for Storage creation
     */
    public Alfred(String filePath) {
        this.textUi = new TextUi();
        this.storage = new Storage(filePath);
        this.parser = new Parser();
        this.filePath = filePath;
        try {
            taskList = storage.load();
        } catch (FileErrorException e) {
            TextUi.fileErrorMessage();
        }
    }

    /**
     * This method retrieves user input via the TextUi class, parses it, and executes
     * the deciphered command. This method can be exited using the "bye" exit command.
     */
    private void run() {
        Command command;
        do {
            String userInput = textUi.getUserInput();
            command = parser.parseCommand(userInput);
            runCommandProcedure(command);
        } while (!command.isExit());
    }

    /**
     * This method sets the TaskList context for commands to execute in before executing
     * them. It will also save the TaskList locally using the initial filePath after
     * every command execution.
     * @param command The parsed command to be executed
     */
    private void runCommandProcedure(Command command) {
        try {
            command.setTaskList(taskList);
            command.execute();
            TaskEncoder.saveAllTasks(filePath, taskList);
        } catch (FileErrorException e) {
            TextUi.fileErrorMessage();
        }
    }

    public static void main(String[] args) {
        new Alfred("data/alfred.txt").run();
    }
}
