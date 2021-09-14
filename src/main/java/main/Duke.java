package main;

import exceptions.DukeException;
import parser.Parser;
import task.TaskManager;
import ui.UI;
import command.Command;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Duke {

    private final TaskManager taskManager;
    private final UI ui;
    private final Parser parser;
    private File dataFile;

    private static final String ROOT_DIR = System.getProperty("user.dir");
    private static final String DATA_DIR = "data";
    private static final String DATA_FILE = "taro.txt";
    public static final Path DATA_DIR_PATH = Paths.get(ROOT_DIR, "src", DATA_DIR);
    public static final Path DATA_FILE_PATH = Paths.get(ROOT_DIR, "src", DATA_DIR, DATA_FILE);

    /**
     * Constructor for main.Duke (renamed to taro) that initializes the key properties
     */
    public Duke() {
        ui = new UI();
        taskManager = new TaskManager();
        parser = new Parser();
    }

    public static void main(String[] args) {
        new Duke().startDuke();
    }

    /**
     * Greets the user by showing the logo and prepares to take user input to perform commands
     */
    private void startDuke() {
        ui.printLogo();
        ui.greetUser();
        getDataFile();
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
                currentCommand.execute(taskManager, ui);
            } catch (DukeException e) {
                ui.printString(e.getMessage());
            }
        }
    }

    private void getDataFile() {
        try {
            dataFile = new File(String.valueOf(DATA_FILE_PATH));
            if(dataFile.getParentFile().mkdirs()) {
                ui.printString("a new directory \"data\" was created to save your data");
            }
            if (dataFile.createNewFile()) {
                ui.printString("a new file \"data/taro.txt\" was created to save your data");
            } else {
                try {
                    taskManager.loadFileContent(dataFile);
                    ui.printString("your saved data has been loaded");
                } catch (FileNotFoundException e) {
                    ui.printString("there was an error retrieving your saved data");
                }
            }
        } catch (IOException e) {
            ui.printString("there was an error retrieving your saved data");
        }
    }

}
