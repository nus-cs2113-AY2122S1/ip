package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Parses the input given by the user and execute the appropriate command
 */
public class Parser {
    protected String command;
    protected String description;
    protected String time;
    protected int count = 0;

    /**
     * Handles the error and prints the error statements
     *
     * @param e the error
     */
    public void errorCatch(DukeException e) {
        switch (e.error) {
        case "unknownCommand":
            Ui.unknownCommandError();
            break;
        case "nullTodo":
            Ui.emptyDescriptionError();
            break;
        case "nullTime":
            Ui.emptyTimeError();
            break;
        default:
            Ui.unknownError();
            break;
        }
    }

    /**
     * Checks if any of the arguments are left blank
     *
     * @param includeTime if there is a need to check the time
     * @throws DukeException If any argument is left blank
     */
    public void errorCheck(boolean includeTime) throws DukeException{
        if (description==null) {
            throw new DukeException("nullTodo");
        } else if (time==null && includeTime) {
            throw new DukeException("nullTime");
        }
    }

    /**
     * Converts the string into its individual arguments
     *
     * @param input the raw input by the user
     * @throws StringIndexOutOfBoundsException if description is left blank with time
     */
    public void parseCommand(String input) throws StringIndexOutOfBoundsException {
        this.description = null;
        this.time = null;
        String temp;
        if (input.contains(" ")) {
            this.command = input.substring(0, input.indexOf(" "));
            temp = input.substring(input.indexOf(" ") + 1);
            if (temp.contains("/")) {
                description = input.substring(input.indexOf(" ") + 1, input.indexOf("/") - 1);
                time = input.substring(input.indexOf("/") + 4);
            } else {
                description = temp;
            }
        } else {
            this.command = input;
        }
    }

    /**
     * Runs the command and catches any exceptions
     *
     * @param tasklist the current list of tasks
     */
    public int executeCommand(TaskList tasklist) {
        try {
            return (runCommand(tasklist));
        } catch (DukeException e){
            errorCatch(e);
        } catch (NullPointerException e) {
            Ui.emptyListError();
        } catch (StringIndexOutOfBoundsException e) {
            Ui.noItemDoneError();
        } catch (NumberFormatException e) {
            Ui.noIndexError();
        } catch (IndexOutOfBoundsException e) {
            Ui.noItemError();
        } catch (DateTimeParseException e) {
            Ui.dateFormatError();
        }
        return 0;
    }

    /**
     * Runs the command and returns if program should continue
     *
     * @param tasklist the current list of tasks
     * @return 0 to continue, -1 to terminate
     * @throws DukeException if any of the arguments are left blank or task not found
     */
    public int runCommand(TaskList tasklist) throws DukeException {
        switch(command){
        case "list":
            tasklist.list();
            break;
        case "done":
            errorCheck(false);
            tasklist.done(description);
            break;
        case "todo":
            errorCheck(false);
            count++;
            tasklist.addTodo(description, count);
            break;
        case "deadline":
            errorCheck(true);
            try {
                LocalDate.parse(time);
                count++;
                tasklist.addDeadline(description, time, count);
            } catch (DateTimeParseException e) {
                Ui.dateFormatError();
            }
            break;
        case "event":
            errorCheck(true);
            try {
                LocalDate.parse(time);
                count++;
                tasklist.addEvent(description, time, count);
            } catch (DateTimeParseException e) {
                Ui.dateFormatError();
            }
            break;
        case "delete":
            errorCheck(false);
            count--;
            tasklist.delete(description, count);
            break;
        case "find":
            errorCheck(false);
            tasklist.find(description);
            break;
        case "bye":
            Ui.end();
            return -1;
        default:
            throw new DukeException("unknownCommand");
        }
        return 0;
    }
}
