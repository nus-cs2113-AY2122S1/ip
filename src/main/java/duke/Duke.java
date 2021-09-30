package duke;

import duke.tasks.DukeException;
import duke.tasks.TaskList;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import duke.commands.Command;

/**
 * Duke is a your friendly note taking bot.
 * Duke keeps track of all your tasks in a reader-friendly format,
 * even after you close the application
 *
 * @author  Dmitri Yam
 * @version 1.0
 * Cool @since   2021-09-26
 */

public class Duke {

    private TaskList taskList = new TaskList();
    private Ui ui;
    private Storage storage;
    /**
     * Initialises a new object Duke
     *
     * @param path Location of data file to store tasks
     * @throws IOException Throws IOException when there is an error
     */
    public Duke(String path) throws IOException {
        this.ui = new Ui();
        ui.hello();
        this.storage = new Storage(path);
        try {
            taskList = storage.read();
        }catch(IOException e){
            System.out.println(e);
        }
    }
    protected void run() throws IOException {
        String userInput;
        boolean exit = true;
        while (exit) {
            try {
                userInput = ui.readCommand();
                Command c = Parser.parse(userInput, taskList, ui, storage);
                c.run();
                exit = c.exit();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }catch (DateTimeParseException e){
                System.out.println("Please use the correct format! [EVENT NAME] /by [YEAR-MONTH-DAY] [TIME]");
            }
        }
    }

    public static void main (String[]args) throws IOException {
        Duke d = new Duke("data/data.txt");
        d.run();
    }
}
