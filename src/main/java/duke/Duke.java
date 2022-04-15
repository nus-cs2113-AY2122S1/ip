package main.java.duke;

import main.java.duke.command.TaskList;
import main.java.duke.exceptions.DukeException;
import main.java.duke.exceptions.InvalidTaskNumberException;
import main.java.duke.parser.Parser;
import main.java.duke.storage.Storage;
import main.java.duke.ui.Ui;
import main.java.duke.task.Task;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

/** Integrates all the classes and methods to run Duke. */
public class Duke {
    public static boolean hasUserExited = false;
    public static ArrayList<Task> tasks = new ArrayList<>();
    public static String file = "data/duke.txt";
    private static TaskList taskList;
    private static Ui ui;

    /**
     * Constructs {@code Duke} to integrate all the dependent classes and run Duke successfully.
     *
     * @param filePath File path indicating the location of text file in the directory
     */
    public Duke(String filePath) {
        ui = new Ui();
        Storage storage = new Storage(filePath);
        taskList = new TaskList();
        Ui.sendWelcomeMessage();
        try {
            storage.readFile(file, TaskList.tasks);
        } catch (FileNotFoundException e) {
            ui.sendFileNotFoundMessage();
        }
        ui.printTasksQuantity(TaskList.tasks);
    }

    /**
     * Runs the full flow of Duke starting with the welcome message,
     * followed by running the execution of the user commands and sends the exit message
     * when the user inputs {@code bye}.
     */
    private void run() {
        Scanner in = new Scanner(System.in);
        while (!hasUserExited) {
            String fullCommand = ui.readCommand(in);
            String userCommand = Parser.getCommandWord(fullCommand);
            try {
                taskList.executeCommand(fullCommand, userCommand);
            }  catch (NumberFormatException e) {
                InvalidTaskNumberException.sendErrorMessage();
            } catch (DukeException e) {
                e.sendErrorMessage();
            } catch (IOException e) {
                Ui.sendIOExceptionMessage();
            } catch (InvalidTaskNumberException e) {
                InvalidTaskNumberException.sendErrorMessage();
            } catch (IndexOutOfBoundsException e) {
                if (userCommand.equals("done") || userCommand.equals("delete")) {
                    InvalidTaskNumberException.sendErrorMessage();
                }
                else {
                    ui.sendIndexOutOfBoundsMessage(userCommand);
                }
            }
        }
        ui.sendExitMessage();
    }

    public static void main(String[] args) {
        new Duke(file).run();
    }

}



