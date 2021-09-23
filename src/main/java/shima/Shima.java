package shima;

import shima.design.Default;
import shima.exception.ShimaException;
import shima.parser.Parser;
import shima.storage.Storage;
import shima.task.Task;
import shima.task.TaskList;

import java.io.IOException;
import java.util.ArrayList;

public class Shima {
    public static int longestTaskDescription = 0; //The length of the longest task description
    private TaskList tasks;
    private Storage storage;

    public Shima(String filePath) {
        Default ui = new Default();
        Storage storage = new Storage(filePath);
        TaskList tasks = new TaskList();
        initiateToDoList(tasks);
    }

    public static void main(String[] args) {
        new Shima("./shimaStorage.txt").runProgram();
    }

    /**
     * Starts reading input and execute actions accordingly
     */
    @SuppressWarnings("InfiniteLoopStatement")
    private void runProgram() {
        showWelcomeScreen();
        System.out.println("\nLet's start input your command:");
        while (true) {
            try {
                Parser.readCommand(tasks, storage);
            } catch (IOException ioException) {
                Default.showMessage("An unexpected error occurs when I was accessing the storage file :(");
                ioException.printStackTrace();
            }
        }
    }

    /**
     * Reads the saved to-do list at the start of the program
     *
     * @param tasks The array list that stores all the tasks
     */
    private void initiateToDoList(TaskList tasks) {
        try {
            storage.readFromStorage(tasks);
        } catch (IOException ioException) {
            System.out.println();
            Default.showMessage("Unfortunately somethings have messed up, I have received this information:");
            ioException.printStackTrace();
        } catch (ShimaException.StorageException storageException) {
            storage.handleStorageError(tasks);
        }
    }

    /**
     * Displays all the welcome screens and designs when the program starts
     */
    private void showWelcomeScreen() {
        Default.printLogo();
        Default.printWelcomeMessage();
        Default.printVersionDescription();
    }

}
