package duke;

import commands.ExitCommand;
import commands.CommandResult;
import commands.Command;
import commands.PrintOptions;

import constants.Message;
import parser.Parser;
import storage.Storage;
import task.Task;
import tasklist.TaskList;
import ui.Ui;

import java.io.IOException;

public class Duke {

    private final Storage storage;
    private final Ui ui;
    private final TaskList tasks;

    public Duke() {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList();
        ui.printStartingMessage();
        ui.printMessage(Message.GETTING_TASK);
        try {
            storage.loadTextFile(tasks);
            ui.printMessage(Message.DONE);
        } catch (IndexOutOfBoundsException error) {
            ui.printMessage(Message.INCORRECT_FORMAT);
            System.exit(1);

        } catch (IOException error) {
            ui.printMessage(Message.DEFAULT_ERROR_MESSAGE);
            System.exit(1);

        } catch (DukeException error) {
            ui.printMessage(Message.FILE_NOT_CREATED);
            ui.printMessage(Message.DONE);

        }
    }

    //@@se-edu vincentlauhl-reused
    //Reused from https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/Main.java
    //with minor modifications
    public void run(){
        Command command = null;
        while (!(command instanceof ExitCommand)){
            String input = ui.getUserCommand();
            command = new Parser().parseInput(input);
            CommandResult result = executeCommand(command);
            ui.showResult(result);
        }
        exitProcess();
    }

    private void exitProcess() {
        try {
            storage.saveTasksToFile(tasks);
        } catch (IOException error) {
            ui.printMessage(Message.IO_EXCEPTION_MESSAGE);
        }
        System.exit(0);
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    private CommandResult executeCommand(Command command) {
        String errorMessage;
        try {
            command.passTaskList(tasks);
            return command.execute();

        } catch (IndexOutOfBoundsException error) {
            errorMessage = Message.getSensibleRange(Task.getTotalTasks());

        } catch (NumberFormatException error) {
            errorMessage = Message.PROMPT_NUMBER;

        } catch (DefaultException error) {
            errorMessage = Message.DEFAULT_ERROR_MESSAGE;

        } catch (DukeException error) {
            errorMessage = Message.TYPE_SUITABLE_COMMAND_MESSAGE;

        }
        return new CommandResult(errorMessage, PrintOptions.DEFAULT);
    }
}
