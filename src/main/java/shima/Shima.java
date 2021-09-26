package shima;

import shima.command.Command;
import shima.design.UserInterface;
import shima.exception.ShimaException;
import shima.parser.Parser;
import shima.storage.Storage;
import shima.task.TaskList;

import java.io.IOException;

public class Shima {
    public static final String STORAGE_IO_ERROR_MSG = "An unexpected error occurs when I was accessing the storage file :(";
    private UserInterface ui;
    private TaskList tasks;
    private Storage storage;

    /**
     * Constructor for Shima class
     *
     * @param filePath The file path of the storage file
     */
    public Shima(String filePath) {
        ui = new UserInterface(); //Displays welcome screen
        storage = new Storage(filePath, ui); //Initializes storage class object
        tasks = new TaskList(storage, ui); //Initializes the task list class object
        initiateToDoList(tasks); //Retrieves data from storage file
    }

    public static void main(String[] args) {
        Shima shima = new Shima("./shimaStorage.txt");
        shima.runProgram();
    }

    /**
     * Starts reading input and execute actions accordingly
     */
    @SuppressWarnings("InfiniteLoopStatement")
    private void runProgram() {
        System.out.println("\nLet's start input your command:");
        while (true) {
            try {
                Command command = Parser.readCommand(tasks, storage, ui);
                command.runCommand();
            } catch (IOException ioException) {
                ui.showMessage(STORAGE_IO_ERROR_MSG);
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
            ui.showMessage(STORAGE_IO_ERROR_MSG);
            ioException.printStackTrace();
        } catch (ShimaException.StorageException storageException) {
            storage.handleStorageError(tasks);
        }
    }
}
