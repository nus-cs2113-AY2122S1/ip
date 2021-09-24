import commands.Command;
import commands.CommandResult;
import commands.ExitCommand;
import parser.Parser;
import storage.Storage;
import task.*;
import ui.Ui;

public class Duke {
    private final Storage storage;
    private TaskManager tasks;
    private final Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskManager(storage.load());
        } catch (Storage.StorageOperationException e) {
            tasks = new TaskManager();
        }
    }

    public void run() {
        Command command;
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String userCommandText = ui.readCommand();
            ui.showLine();
            command = new Parser().parseCommand(userCommandText);
            CommandResult result = executeCommand(command);
            tasks.setTaskList(result.getRelevantTasks());
            storage.save(tasks.getTaskList());
            ui.showResultToUser(result);
            isExit = ExitCommand.isExit(command);

        }
        exit();
    }

    private CommandResult executeCommand(Command command) {
        try {
            command.setData(tasks);
            return command.execute();
        } catch (Exception e) {
            ui.showToUser(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    private void exit() {
        System.exit(0);
    }
}
