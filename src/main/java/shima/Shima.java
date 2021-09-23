package shima;

import shima.command.Command;
import shima.design.Default;
import shima.exception.ShimaException;
import shima.parser.Parser;
import shima.storage.Storage;
import shima.task.TaskList;

import java.io.IOException;

public class Shima {
    private Default ui;
    private TaskList tasks;
    private Storage storage;

    public Shima(String filePath) {
        ui = new Default();
        storage = new Storage(filePath);
        tasks = new TaskList(storage);
        initiateToDoList(tasks);
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
                Command command = Parser.readCommand(tasks, storage);
                command.runCommand();
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
}
