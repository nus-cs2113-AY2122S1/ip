package parser;

import exceptions.JimException;
import commands.*;

public class Parser {

    public static Command parseCommand(String input) throws JimException {
        Command c;
        String inputToParse = input.toLowerCase();
        if (inputToParse.startsWith("todo")) {
            c = new AddTodoTask(input);
        } else if (inputToParse.startsWith("deadline")) {
            c = new AddDeadlineTask(input);
        } else if (inputToParse.startsWith("event")) {
            c = new AddEventTask(input);
        } else if (inputToParse.startsWith("done")) {
            c = new MarkTaskAsDone(input);
        } else if (inputToParse.equals("list")) {
            c = new ListCommand();
        } else if (inputToParse.startsWith("remove")) {
            c = new RemoveTask(input);
//        } else if (inputToParse.startsWith("find")) {
//            c = new FindTaskCommand(input);
        } else if (inputToParse.equals("clear database")) {
            c = new ClearDatabaseCommand();
        } else if (inputToParse.contains("birthday")) {
            c = new BirthdayCommand();
        } else if (inputToParse.equals("help")) {
            c = new HelpCommand();
        } else if (inputToParse.equals("bye")) {
            c = new ExitCommand();
        } else {
            throw new JimException();
        }
        return c;
    }
}
