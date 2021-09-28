package duke;

import duke.ErrorHandling.CommandException;
import duke.Parser.Parser;
import duke.Storage.Storage;
import duke.TaskList.TaskList;
import duke.Ui.Ui;
//Level A-JavaDoc
/**
 * Main Class of the Duke program
 * Handle the integration of classes to provide the necessary functions to the user
 */
public class Duke {

    private final TaskList tasks;
    private final Ui ui;

    /**
     * Constructor for Duke class
     * Creates an instance of all the necessary class for the program to run
     */
    public Duke(){
        ui = new Ui();
        Storage storage = new Storage();
        tasks = new TaskList(storage.load());
    }

    /**
     * Main function of Duke
     * Integrate the Ui class that handles taking in input of the user with
     * the Parser class that handles the response to the input
     * Keep running until the system is terminated by a string bye
     */
    public void run(){
        ui.Greetings();
        boolean isExit = false;
        do{
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Parser parser = new Parser(fullCommand, tasks);
                isExit = parser.isExit();
                if(!isExit) {
                    parser.handleInput();
                }
            }catch(CommandException e){
                ui.showError(e.getErrorMessage());
            }finally {
                ui.showLine();
            }
        }while (!isExit);
        ui.Farewell();
    }

    /**
     * Starts the programs
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
