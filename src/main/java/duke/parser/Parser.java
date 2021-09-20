package duke.parser;

import duke.command.Command;
import duke.command.DateCommand;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.IncorrectCommand;
import duke.command.ListCommand;
import duke.command.ToDoCommand;
import duke.exception.DateError;
import duke.exception.DeadlineCommandError;
import duke.exception.DeleteCommandError;
import duke.exception.DoneCommandError;
import duke.exception.DukeException;
import duke.exception.EventCommandError;
import duke.exception.FindCommandError;
import duke.exception.ToDoCommandError;

import java.time.LocalDate;

/**
 * Parser class gets the raw input from Logic class and then returns the appropriate command to be
 * executed to Logic class.
 *
 * @param "input" raw input from user.
 * @return appropriate command to be executed.
 */
public class Parser {

    /**
     * Returns a Command depending on the user input.
     *
     * @param userInput  user Input.
     * @return Command to be executed.
     * @throws DukeException  If userInput is in wrong format.
     */
    public Command parseCommand(String userInput) throws DukeException{
        String[] words = userInput.trim().split(" ", 2);  // split the input into command and arguments
        if (words.length == 0) {
            return new IncorrectCommand();
        }

        final String commandWord = words[0];
        final String arguments = userInput.replaceFirst(commandWord, "").trim();

        switch (commandWord) {
        case "todo":
        case "find":
            return prepareToDoOrFindCommand(commandWord, arguments);

        case "event":
        case "deadline":
            return prepareEventOrDeadlineCommand(commandWord, arguments);

        case "delete":
        case "done":
            return prepareDeleteOrDoneCommand(commandWord, arguments);

        case "date":
            return prepareDateCommand(arguments);

        case "list":
            return new ListCommand();

        case "bye":
            return new ExitCommand();

        default:
            return new IncorrectCommand();
        }
    }

    /**
     * Returns Date Command after formatting it.
     *
     * @param args  date taken from user input.
     * @return Date Command.
     * @throws DateError  If args does not follow yyyy-mm-dd format.
     */
    private Command prepareDateCommand(String args) throws DukeException{
        if (args.isEmpty()) {
            throw new DateError();
        }
        try {
            LocalDate dateKey = LocalDate.parse((args.trim()));
            return new DateCommand(dateKey);
        } catch (Exception e) {
            throw new DateError();
        }
    }

    /**
     * Returns ToDo or Find Command after formatting is done.
     *
     * @param command  command keyword to deterime if ToDo or Find command is called.
     * @param args description of task.
     * @return ToDo or Find Command.
     * @throws ToDoCommandError,FindCommandError  If user input is in wrong format.
     */
    private Command prepareToDoOrFindCommand(String command, String args) throws  DukeException{
        if (args.isEmpty()) {
            if (command.equals("todo")) {
                throw new ToDoCommandError();
            }
            throw new FindCommandError();
        }
        try {
            if (command.equals("todo")) {
                return new ToDoCommand(args.trim());
            }
            return new FindCommand(args.trim());
        } catch (Exception e) {
            if (command.equals("todo")) {
                throw new ToDoCommandError();
            }
            throw new FindCommandError();
        }
    }

    /**
     * Returns Event or Deadline Command after formatting is done.
     *
     * @param command  command keyword to deterime if Event or Deadline command is called.
     * @param args description of task and date.
     * @return Event or Deadline Command.
     * @throws EventCommandError,DeadlineCommandError  If user input is in wrong format.
     */
    private Command prepareEventOrDeadlineCommand(String command, String args) throws DukeException{
        String[] parts = args.split("/", 2);
         //Validate arg string format
        if (parts.length != 2 || parts[0].isEmpty() || parts[1].isEmpty()) {
            if(command.equals("event")){
                throw  new EventCommandError();
            }
            throw  new DeadlineCommandError();
        }
        if (command.equals("event") && isWrongEscapeWord(parts[1],"at")) {
            throw new EventCommandError();
        }
        if (command.equals("deadline") && isWrongEscapeWord(parts[1],"by")) {
            throw new DeadlineCommandError();
        }
        try {
            String date = getDate(parts[1]);
            LocalDate correctDateFormat = LocalDate.parse((date));
            if(command.equals("event")) {
                return new EventCommand(
                        parts[0].trim(),
                        correctDateFormat
                );
            }
            return new DeadlineCommand(
                    parts[0].trim(),
                    correctDateFormat
            );
        } catch (Exception e) {
            if (command.equals("event")) {
                throw  new EventCommandError();
            }
            throw  new DeadlineCommandError();
        }
    }

    /**
     * Returns Delete or Done Command after formatting is done.
     *
     * @param command  command keyword to deterime if Delete or Done command is called.
     * @param args index of list.
     * @return Delete or Done Command.
     * @throws DeleteCommandError,DoneCommandError  If user input is in wrong format.
     */
    private Command prepareDeleteOrDoneCommand(String command, String args) throws  DukeException{
        try {
            final int targetIndex = Integer.parseInt(args.split(" ")[0]);
            if (command.equals("delete")) {
                return new DeleteCommand(targetIndex);
            }
            return new DoneCommand(targetIndex);
        } catch (NumberFormatException nfe) {
            if (command.equals("delete")) {
                throw  new DeleteCommandError();
            }
            throw  new DoneCommandError();
        }
    }

    /**
     * Returns a string of the date of a task.
     *
     * @param input  input string.
     * @return String containing only the date of the task.
     * @throws DateError  If user input is in wrong format.
     */
    public String getDate(String input) throws DukeException{
        String date = input;
        int firstWhitespace = getFirstWhiteSpace(date);
        if (firstWhitespace == -1) {
            throw new DateError();
        }
        date = input.substring(firstWhitespace).trim();
        return date;
    }

    /**
     * Returns the position of the first whitespace in a string.
     *
     * @param input input String.
     * @return position of first whitespace in a string.
     */
    public int getFirstWhiteSpace(String input) {
        for (int index = 0; index < input.length(); index++) {
            //sees if character at that index is a whitespace
            if (Character.isWhitespace(input.charAt(index))) {
                return index;
            }
        }
        return -1;
    }

    /**
     * Returns true if the correct escape word is used.False otherwise
     * Eg. "event <task description> /by <date>" will return true as
     * the escape word is wrong and should be /at
     *
     * @param inputString the input String.
     * @param escapeWord either "by" or "at" depending on the command.
     * @return true or false on whether the wrong escape word is used.
     */
    public boolean isWrongEscapeWord(String inputString, String escapeWord) {
        if (inputString.substring(0,2).equals(escapeWord)) {
            return false;
        }
        return true;
    }
}