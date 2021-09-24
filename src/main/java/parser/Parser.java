package parser;

import exceptions.JimException;
import ui.Ui;
import commands.*;

public class Parser {

    private static final Ui ui = new Ui();

    public static Command parseCommand(String input) throws JimException {
        Command c;
        String inputToParse = input.toLowerCase();
        if (inputToParse.startsWith("todo")) {
            c = new AddTodo(input);
        } else if (inputToParse.startsWith("deadline")) {
            c = new AddDeadline(input);
        } else if (inputToParse.startsWith("event")) {
            c = new AddEvent(input);
        }else if (inputToParse.startsWith("done")) {
            c = new MarkDone(input);
        } else if (inputToParse.equals("list")) {
            c = new List();
        } else if (inputToParse.startsWith("remove")) {
            c = new RemoveTask(input);
        } else if (inputToParse.equals("clear database")) {
            c = new ClearDatabase();
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
