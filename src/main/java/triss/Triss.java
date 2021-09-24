package triss;

import triss.command.Command;
import triss.exception.TrissException;
import triss.task.Task;


public class Triss {

    /** TaskList to store and edit tasks */
    private final TaskList tasklist;
    /** User interface to receive input and give output */
    private final Ui ui;
    /** Parser to read information from user input and stored data */
    private final Parser parser;
    /** Storage where tasks can be retrieved and stored */
    private final Storage storage;

    public Triss() {
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
        // Setup boolean to track if user has said "bye"
        boolean hasUserSaidBye = false;

        // While user has not said "bye", check for next line of input and execute
        while (!hasUserSaidBye) {
            try {
                ui.readUserInput();
                ui.printSeparatorLine();
                Command c = parser.parseUserCommand(ui.getUserInput());
                c.execute(ui,parser,tasklist,storage);
                hasUserSaidBye = c.isExit();
            } catch (TrissException e) {
                ui.printLine(e.getMessage());
            } finally {
                storage.saveTasks();
                ui.printSeparatorLine();
            }
        }
    }

    public static void main(String[] args) {
        new Triss().run();
    }

}
