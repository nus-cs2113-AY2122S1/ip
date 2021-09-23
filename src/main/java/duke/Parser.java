package duke;

import exception.DukeException;
import exception.EmptyTaskDescriptionException;
import exception.NoTaskFoundException;

import java.io.IOException;

public class Parser {


    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_DONE = "done";
    public static final String COMMAND_DELETE = "delete";

    public static boolean processCommand(String userInput) {
        try {
            if (userInput.equalsIgnoreCase(COMMAND_BYE)) {
                return false;
            } else if (userInput.equalsIgnoreCase(COMMAND_LIST)) {
                TaskList.list();
                return true;
            } else if (userInput.startsWith(COMMAND_DONE)) {
                TaskList.markTaskAsDone(userInput);
                return true;
            } else if (userInput.startsWith(COMMAND_DELETE)) {
                TaskList.deleteTask(userInput);
                return true;
            } else {
                TaskList.addTaskToList(userInput);
                return true;
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Could not save data to file");
        } catch (NoTaskFoundException e) {
            System.out.println(e.getMessage());
        } catch (EmptyTaskDescriptionException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

}
