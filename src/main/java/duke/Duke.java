package duke;

import duke.task.TaskList;
import java.util.Objects;
import java.io.File;

/**
 * Main class that is called upon program execution
 */
public class Duke {
    public static final int COMMAND_INDEX = 0;
    public static final File myFile = new File("tasks.txt");
    private static Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;


    /**
     * Initialises Ui, Storage and TaskList. If there is a user file provided, the TaskList will copy all tasks
     * stored in the user file
     * @param myFile File containing tasks
     */
    public Duke(File myFile) {
        ui = new Ui();
        ui.printStart();
        storage = new Storage(myFile);

        if (!storage.newFileCreated) {
            tasks = new TaskList(storage.load());
        }
        else {
            tasks = new TaskList();
        }

        parser = new Parser(tasks, ui, storage);
    }


    /**
     * Command that is continuously run in a loop. Waits for a user input, and parses it into commands if valid.
     * Afterwards, the program will go back to waiting for the next user input. Input "bye" to exit program
     */
    public void run() {
        while (true) {
            ui.getNextLine();

            if (Objects.equals(ui.userInput.split(" ")[COMMAND_INDEX], "bye")) {
                ui.printEnd();
                return;
            }
            parser.parseInput(ui.userInput);
        }
    }


    /**
     * This function is called upon program execution.
     *
     * @param args System arguments that are added to the program
     */
    public static void main(String[] args) {
        new Duke(myFile).run();
    }
}