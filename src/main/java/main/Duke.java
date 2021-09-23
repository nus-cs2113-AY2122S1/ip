package main;

import exceptions.DukeException;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.UI;
import command.Command;


public class Duke {

    private final TaskList taskList;
    private final UI ui;
    private final Parser parser;
    private final Storage storage;

    /**
     * Constructor for main.Duke (renamed to taro) that initializes the key properties
     */
    public Duke() {
        ui = new UI();
        taskList = new TaskList();
        parser = new Parser();
        storage = new Storage();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Greets the user by showing the logo and prepares to take user input to perform commands
     */
    private void run() {
        ui.printLogo();
        ui.greetUser();
        storage.getDataFile(taskList, ui);
        handleCommands();
    }

    /**
     * Handles the main functionality of duke by using the ui object to read from standard input and then pass the
     * input to the parser to return an executable command object. After the command is executed, the user can enter
     * more commands until "bye" is entered. An invalid command prints an error message to standard output.
     */
    private void handleCommands() {
        while (true) {
            String userInput = ui.readUserInput();
            try {
                Command currentCommand = parser.parseInput(userInput);
                currentCommand.execute(taskList, ui, storage);
            } catch (DukeException e) {
                ui.printString(e.getMessage());
            }
        }
    }

}
