package duke;

import duke.commands.Command;

/**
 * This is a application that simulates a todo list. It is able to read, write and delete items,
 * as well as mark items in the list as completed.
 *
 * @author Lim Kay Yun
 * @version v0.1
 * @since 21-9-2021
 */
public class Duke {
    private Storage storage;
    private final Ui ui;
    private TaskList tasks;

    /**
     * Represent the startup of the application. The user interface and storage used to store file data
     * is instantiated here. If the file that is used to store data cannot be read, the program will show
     * an error message and will terminate with exit code -1
     *
     * @param filePath relative path where the file used to store the data of the task list is located at
     */
    public Duke(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            System.exit(-1);
        }
    }

    /**
     * Responsible for listening for input from user and execute commands depending on the input string
     * from the users. If there is any error while attempting to parse the input string or while executing
     * the command, a message associated with the error will be printed and another input will be prompted
     */
    public void run(){
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (Exception ex) {
                ui.showExceptionMessage(ex);
            }
        }
    }

    /**
     * Create an instance of the application and run the application. The application will only terminate
     * if the terminate command is executed or if there is an error loading the file
     *
     * @param args the variable argument field is not used in this program
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
