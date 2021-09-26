import commands.Command;
import commands.CommandResult;
import commands.ExitCommand;
import common.DukeException;
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
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskManager();
        }
    }

    public void run() {
        Command c;
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                c = new Parser().parseCommand(fullCommand);
                CommandResult result = executeCommand(c);
                tasks.setTaskList(result.getRelevantTasks());
                storage.save(tasks.getTaskList());
                ui.showResultToUser(result);
                isExit = ExitCommand.isExit(c);
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        exit();
    }

    private CommandResult executeCommand(Command command) {
        try {
            command.setData(tasks);
            return command.execute();
        } catch (Exception e) {
            ui.showError(e.getMessage());
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
