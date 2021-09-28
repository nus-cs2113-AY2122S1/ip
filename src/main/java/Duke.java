

import java.util.ArrayList;
import java.util.Scanner;

import duke.task.*;
import duke.exception.DukeException;
import duke.Ui.Ui;
import duke.Parser.Parser;
import duke.Storage.Storage;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    /**
     * Key components of Duke program
     */
    private static Ui ui;
    private static Parser parser;
    private static Storage storage;
    private static TaskList tasks;

    /**
     * Initialize the components of Duke
     */
    public static void initializeProgram() {
        ui = new Ui();
        tasks = new TaskList(ui);
        parser = new Parser(ui);
        storage = new Storage();
        storage.openTaskFromFile(tasks);
    }

    public static void main(String[] args) {

        initializeProgram();
        ui.printDukeLogo();
        ui.printGreet();
        String userInput;
        Scanner in = new Scanner(System.in);
        userInput = in.nextLine();

        while (!userInput.equals("bye")) {
            parser.processCommand(userInput,tasks);
            userInput = in.nextLine();
        }
        ui.printBye();
    }
}