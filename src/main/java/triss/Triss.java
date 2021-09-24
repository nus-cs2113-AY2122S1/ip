package triss;

import triss.command.Command;
import triss.exception.TrissException;
import triss.task.Task;


public class Triss {

    /** Boolean to track if user has said "bye" */
    private boolean hasUserSaidBye;
    /** TaskList to store and edit tasks */
    private final TaskList tasklist;
    /** User interface to receive input and give output */
    private final Ui ui;
    /** Parser to read information from user input and stored data */
    private final Parser parser;
    /** Storage where tasks can be retrieved and stored */
    private final Storage storage;

    public Triss() {
        hasUserSaidBye = false;
        tasklist = new TaskList();
        ui = new Ui();
        parser = new Parser();
        storage = new Storage();
    }

    /**
     * Loops Triss into receiving user input and giving output messages.
     * It will only end once the user types "bye".
     */
    private void run() {
        // Print LOGO and welcome text
        ui.printWelcomeMessage();

        // While user has not said "bye", check for next line of input
        while (!hasUserSaidBye) {

            // Get the next line of input, and parse it to find the user's command (first word in input)
            ui.readUserInput();
            ui.printSeparatorLine();

            try {
                Command c = parser.parseUserCommand(ui.getUserInput());
                c.execute(ui,parser,tasklist,storage);
                hasUserSaidBye = c.isExit();
            } catch (TrissException e) {
                ui.printLine(e.getMessage());
            }

            storage.saveTasks();
            ui.printSeparatorLine();

        }
    }

    public static void main(String[] args) {
        new Triss().run();
    }

}
