package kate;

import kate.command.Command;
import kate.exception.FileCorruptedException;
import kate.exception.InvalidCommandException;
import kate.parser.Parser;
import kate.storage.Storage;
import kate.tasklist.TaskList;
import kate.ui.KateUI;

import java.io.IOException;

public class Kate {

    private TaskList tasks;
    private KateUI ui;
    private Storage storage;

    public Kate() {
        ui = new KateUI();
        storage = new Storage();
        tasks = new TaskList();
    }

    public static void main(String[] args) {
        new Kate().startKate();
    }

    /**
     * Retrieve and process user inputs depending on the commands
     */
    private void startKate() {

        try {
            storage.loadDataFile(ui, tasks);
        } catch (IOException | FileCorruptedException e) {
            return;
        }

        ui.printGreetMessage();
        while (true) {
            String userInput = ui.getInput();
            try {
                Command command = Parser.extractCommand(userInput);

                switch (command) {
                case TODO:
                    tasks.addToDo(ui, storage, userInput);
                    break;
                case DEADLINE:
                    tasks.addDeadline(ui, storage, userInput);
                    break;
                case EVENT:
                    tasks.addEvent(ui, storage, userInput);
                    break;
                case LIST:
                    ui.printTasks(tasks);
                    break;
                case DONE:
                    tasks.indicateDone(ui, storage, userInput);
                    break;
                case DELETE:
                    tasks.deleteTask(ui, storage, userInput);
                    break;
                case BYE:
                    ui.printByeMessage();
                    return;
                case HELP:
                    ui.printHelpPage();
                    break;
                default:
                    break;
                }
            } catch (InvalidCommandException e) {
                ui.printInvalidCommandMessage();
            }
        }
    }
}
