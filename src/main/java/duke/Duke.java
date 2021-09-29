package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import java.security.InvalidParameterException;

public class Duke {

    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    /* User input is seperated by an empty space */
    public static final String USER_INPUT_SEPERATOR = " ";

    /* User command list */
    public static final String USER_COMMAND_LIST = "LIST";
    public static final String USER_COMMAND_TODO = "TODO";
    public static final String USER_COMMAND_DEADLINE = "DEADLINE";
    public static final String USER_COMMAND_EVENT = "EVENT";
    public static final String USER_COMMAND_DONE = "DONE";
    public static final String USER_COMMAND_BYE = "BYE";
    public static final String USER_COMMAND_HELP = "HELP";
    public static final String USER_COMMAND_DELETE = "DELETE";

    /* A nicely formatted line */
    private static final String LINE = "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=";
    private static final String WELCOME_MESSAGE = "Hello! I'm Gaben.\n"
            + "Steam sales is coming! Prepare your wallet.\n"
            + "Anyways, what can I do for you today?";
    private static final String EXIT_MESSAGE = "Thank you for using Gaben.\n"
            + "Remember to keep your wallet stacked before using me again!";
    private static final String HELP_MESSAGE = "The following commands accepted are: "
            + "LIST (Show the list of task)\n"
            + "TODO <description> (Create a task with todo tag)\n"
            + "DEADLINE <description> /by <date and time> (Create a task with deadline tag)\n"
            + "EVENT <description> /at <date and time> (Create a task with event tag)\n"
            + "DONE <index of task> (To mark indicated task as completed)\n"
            + "BYE (End program)\n"
            + "HELP (List out available commands)";

    public Duke(String filePath){
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadData());
        } catch (DukeException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }

    public void run(){
        ui.printWelcomeMessage();
        // Boolean to allow continuous listening of user input
        boolean isExit = false;
        // Listen for user input and do commands given by user till user wants to exit program
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printHorizontalLines();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.printErrorMessage(e.getMessage());
            }finally {
                ui.printHorizontalLines();
            }
        }
    }


    public static void main(String[] args) throws InvalidParameterException {
        new Duke("data/tasks.txt").run();
    }
}
