package duke.parser;

import duke.commands.*;
import duke.commands.Command;
import duke.exception.DukeEmptyParaException;
import duke.exception.DukeException;
import duke.exception.InvalidInputException;
import duke.task.Deadline;
import duke.task.Events;
import duke.task.Task;
import duke.task.ToDos;

public class Parser {

    public static Command parse(String fullCommand) throws DukeException {
        String command = fullCommand.contains(" ") ? fullCommand.split(" ")[0] : fullCommand;

        switch (command) {
            case "deadline":
            case "event":
            case "todo":
                return new AddCommand(parseTask(command, fullCommand));
            case "bye":
                return new ExitCommand();
            case "delete":
                return new DeleteCommand(parseIndex(command, fullCommand));
            case "done":
                return new DoneCommand(parseIndex(command, fullCommand));
            case "find":
                return new FindCommand(parseKeyword(fullCommand));
            case "help":
                return new HelpCommand();
            case "list":
                return new ListCommand();
            default:
                throw new InvalidInputException("I'm sorry, but I don't know what that means");
        }
    }

    public static int parseIndex(String command, String fullCommand) throws DukeEmptyParaException {
        int i = fullCommand.indexOf(" ");
        if(i == -1){
            throw new DukeEmptyParaException("There should be an index of task to " + command);
        }

        return Integer.parseInt(fullCommand.substring(i + 1));
    }

    public static String parseKeyword(String fullCommand) throws DukeEmptyParaException {
        int i = fullCommand.indexOf(" ");
        if(i == -1){
            throw new DukeEmptyParaException("The keyword to find cannot be empty");
        }

        return fullCommand.substring(i + 1);
    }

    public static Task parseTask(String command, String fullCommand) throws DukeException {
        int i = fullCommand.indexOf(" ");
        String taskDetails = " ";
        if(i != -1){
            taskDetails = fullCommand.substring(i + 1);
        }

        if(taskDetails.isBlank()) {
            // the string is empty or contains only white space
            throw new DukeException("The description of a " + command + " cannot be empty");
        } else {
            switch (command){
                case "todo":
                    return new ToDos(taskDetails);
                case "deadline":
                    return parseDeadline(taskDetails);
                case "event":
                    return parseEvent(taskDetails);
            }
        }
        return null;
    }

    public static Deadline parseDeadline(String taskDetails) throws DukeException {
        int i = taskDetails.indexOf(" /by ");
        if ( i == -1) {
            throw new DukeException("There should be a \"/by\" in the deadline");
        }

        String description = getDescription(i, taskDetails);
        String by = getTime(i, taskDetails);
        return new Deadline(description, by);

    }

    public static Events parseEvent(String taskDetails) throws DukeException {
        int i = taskDetails.indexOf(" /at ");
        if ( i == -1) {
            throw new DukeException("There should be a \"/at\" in the event");
        }

        String description = getDescription(i, taskDetails);
        String at = getTime(i, taskDetails);
        return new Events(description, at);
    }

    public static String getDescription(int index, String taskDetails){
        return taskDetails.substring(0, index);
    }

    public static String getTime(int index, String taskDetails){
        final int KEYWORD_LENGTH = 5;
        return taskDetails.substring(index + KEYWORD_LENGTH);
    }


}
