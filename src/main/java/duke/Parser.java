package duke;

/* Importing local files from other packages */

import exception.DukeException;
import exception.EmptyTaskDescriptionException;
import exception.NoTaskFoundException;
import ui.Ui;

import java.io.IOException;

public class Parser {

    /* Initializing Strings with Duke commands */
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_DONE = "done";
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_FIND = "find";
    public static final String COMMAND_HELP = "help";


    /**
     * Returns true if the command is not bye and the program should continue for the next iteration as well. Returns false otherwise.
     *
     * @param userInput UserInput stores the command entered by the user.
     * @return true if the command should continue for the next iteration. Returns false otherwise.
     */
    public static boolean processCommand(String userInput) {
        try {
            if (userInput.startsWith(COMMAND_BYE)) {
                return false;
            } else if (userInput.startsWith(COMMAND_LIST)) {
                TaskList.list();
                return true;
            } else if (userInput.startsWith(COMMAND_DONE)) {
                TaskList.markTaskAsDone(userInput);
                return true;
            } else if (userInput.startsWith(COMMAND_DELETE)) {
                TaskList.deleteTask(userInput);
                return true;
            } else if (userInput.startsWith(COMMAND_FIND)) {
                TaskList.callFindTasks(userInput);
                return true;
            } else if (userInput.startsWith(COMMAND_HELP)) {
                TaskList.help();
                return true;
            } else {
                TaskList.addTaskToList(userInput);
                return true;
            }
        } catch (DukeException e) {
            Ui.printLine();
            System.out.println(e.getMessage());
        } catch (IOException e) {
            Ui.printLine();
            System.out.println("Could not save data to file");
        } catch (NoTaskFoundException e) {
            Ui.printLine();
            System.out.println(e.getMessage());
        } catch (EmptyTaskDescriptionException e) {
            Ui.printLine();
            System.out.println(e.getMessage());
        }
        return true;
    }

}
