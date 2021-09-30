package main;

import commands.*;
import exceptions.JimException;
import parser.Parser;
import ui.Ui;
import storage.Storage;
import tasklist.TaskList;

public class Jim {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    public Jim() {
        tasks = new TaskList();
        ui = new Ui();
        storage = new Storage(tasks);
        storage.initJim();
    }

    /**
     * Shows welcome message. Takes in user command and executes that command (if possible, shows error if not)
     * Taking and executing user command will loop until isExit is true, where the function terminates.
     */
    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userCommand = ui.readCommand();
                Command command = Parser.parseCommand(userCommand);
                command.execute(tasks, ui, storage);
                isExit = command.getIsExit();
            } catch (JimException e) {
                ui.showInvalidCommandMessage();
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showWrongCommandFormatMessage();
            }
        }
    }

    public static void main(String[] args) {
        new Jim().run();
    }
}