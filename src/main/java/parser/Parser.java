package parser;

import exceptions.JimException;
import commands.*;
import ui.Ui;

import java.util.Locale;

/**
 * Represents the command parser.
 */
public class Parser {
    private Ui ui = new Ui();
    /**
     * Parses the user input and returns the command that is being called.
     *
     * @param input The user input.
     * @return Command being called to be executed.
     * @throws JimException If input does not call any command.
     */
    public static Command parseCommand(String input) throws JimException {
        Command c;
        String[] inputToParse = input.split(" ");
        String commandWord = inputToParse[0].toLowerCase();
        switch (commandWord) {
        case "todo":
            c = new AddTodoTask(input);
            break;
        case "deadline":
            c = new AddDeadlineTask(input);
            break;
        case "event":
            c = new AddEventTask(input);
            break;
        case "done":
            c = new MarkTaskAsDone(input);
            break;
        case "list":
            c = new ListCommand();
            break;
        case "remove":
            c = new RemoveTask(input);
            break;
        case "find":
            c = new FindTaskCommand(input);
            break;
        case "clear":
            c = new ClearDatabaseCommand();
            break;
        case "birthday":
            c = new BirthdayCommand();
            break;
        case "help":
            c = new HelpCommand();
            break;
        case "bye":
            c = new ExitCommand();
            break;
        default:
            throw new JimException();
        }
        return c;
    }

}
