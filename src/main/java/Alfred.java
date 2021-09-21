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

    private void run() {
        Command command;
        do {
            String userInput = textUi.getUserInput();
            command = parser.parseCommand(userInput);
            runCommandProcedure(command);
        } while (!command.isExit());
    }

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
