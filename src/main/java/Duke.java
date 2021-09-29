import Commands.*;
import DukeClasses.Parser;
import DukeClasses.Storage;
import DukeClasses.TaskList;
import DukeClasses.Ui;
import Exceptions.EmptyTaskException;
import Exceptions.InvalidCommandException;
import Exceptions.UnknownCommandException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Entry point of the Duke application.
 * Initializes the application and starts the interaction with the user.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    //main function to process input

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.readData(filePath));
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("An error has occurred!");
        } catch (FileNotFoundException e) {
            ui.printFileNotFoundError();
            tasks = new TaskList(new ArrayList<>());
        }
    }

    /**
     * Main function that runs the entire duke program until termination
     */
    public void run() {
        ui.greetUser();

        String fullCommand;

        boolean isExit = false;

        //user input loop
        while (!isExit) {
            try {
                fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.executeCommand(tasks, ui, storage);
                isExit = c.isExit();
            } catch (UnknownCommandException | StringIndexOutOfBoundsException | InvalidCommandException e) {
                System.out.println(Ui.invalidTaskError);
            } catch (EmptyTaskException e) {
                System.out.println(Ui.emptyTaskError);
            } catch (IOException e) {
                System.out.println("An error has occurred!");
            }
        }
    }

    public static void main(String[] args)  {
        new Duke("Duke.txt").run();
    }
}
