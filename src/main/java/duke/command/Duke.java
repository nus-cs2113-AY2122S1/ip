package duke.command;

import duke.Ui;
import duke.Parser;
import duke.task.Storage;
import duke.task.TaskList;

import java.io.IOException;

public class Duke {
    private Ui ui;
    private Storage storage;
    TaskList taskList;

    public Duke(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            taskList = new TaskList(storage.load(), storage);
        } catch (IOException e) {
            ui.showLoadingError(e);
            taskList = new TaskList(storage);
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                String[] parsedFullCommand = Parser.parse(fullCommand);
                execute(taskList, parsedFullCommand);
                isExit = checkIsExit(parsedFullCommand[0]);
            } catch (Exception e) {
                ui.showLoadingError(e);
            }
        }
        ui.showBye();
    }

    public boolean checkIsExit(String commandWord) {
        if (commandWord.equals("bye")) {
            return true;
        } else {
            return false;
        }
    }

    public void execute(TaskList taskList, String[] parsedFullCommand) {
        String commandWord = parsedFullCommand[0];
        switch (commandWord) {
        case "list":
            if (parsedFullCommand.length == 1) {
                taskList.listAllTasks();
            } else {
                ui.showLoadingError();
            }
            break;
        case "done":
            if (parsedFullCommand.length == 2) {
                String indexToBeMarkedDone = parsedFullCommand[1];
                taskList.markAsDone(indexToBeMarkedDone);
            } else {
                ui.showLoadingError();
            }
            break;
        case "todo":
        case "deadline":
        case "event":
            taskList.addTask(parsedFullCommand);
            break;
        case "delete":
            if (parsedFullCommand.length == 2) {
                String indexToBeDeleted = parsedFullCommand[1];
                taskList.deleteTask(indexToBeDeleted);
            } else {
                ui.showLoadingError();
            }
            break;
        case "bye":
            break;
        default:
            ui.showLoadingError();
            break;
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}