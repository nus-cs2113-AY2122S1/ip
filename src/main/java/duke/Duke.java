package duke;

import java.awt.*;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
            parser = new Parser(tasks);
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Automatic text file creation initiated master!");
        } catch (IOException e) {
            System.out.print("There is an error in your input master! Please check it out!");
        }
    }

    public void sendCommand() {
        String line;
        ui.greetUser();
        boolean isExit = false;
        while (!isExit) {
            try {
                ui.promptUser();
                line = ui.sendInput();
                ui.printLine();
                parser.checkCommand(line);
                isExit = parser.isBye;
            } catch (NumberFormatException e) {
                System.out.println(Ui.NUMBER_FORMAT_EXCEPTION);
            } catch (DukeException e) {
                //Catching DukeException errors
            } catch (IOException e) {
                System.out.print("There is an error in your input master! Please check it out!");
            } catch (DateTimeParseException e) {
                System.out.println("Correct date format please!");
            }
        }
        ui.sayBye();
    }

    public static void main(String[] args) throws DukeException, IOException {
        new Duke("Tasks.txt").sendCommand();
    }
}