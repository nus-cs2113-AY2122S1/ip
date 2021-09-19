package Parsing;

import java.io.FileNotFoundException;

import Exception.DukeException;


public class Parser {

    protected static void checkDescription (String input) throws DukeException {
        if ( (input.contains("todo") || input.contains("deadline") || input.contains("event"))
                && input.split(" ").length == 1) {
            throw new DukeException("Description cannot be empty"); }
    }


    public static ParseInput parse (String input) throws DukeException, FileNotFoundException {
        ParseInput parseinputPair;

        //---------- Query Checks ----------//
        if (input.equals("bye")) {
            parseinputPair = new ParseInput(ParseResult.BYE, input);
            return parseinputPair;
        }

        else if (input.contains("done")) {
            parseinputPair = new ParseInput(ParseResult.DONE, input);
            return parseinputPair;
        }

        else if (input.equals("list")) {
            parseinputPair = new ParseInput(ParseResult.LIST, input);
            return parseinputPair;
        }

        else if (input.contains("delete")) {
            parseinputPair = new ParseInput(ParseResult.DELETE, input);
            return parseinputPair;
        }

        //---------- addTask Checks ----------//
        else {
            String taskType = input.split(" ")[0];
            String descriptionAndTime = input.replaceAll(taskType, "");
            Parser.checkDescription(input);

            switch (taskType) {
            case "todo" :
                parseinputPair = new ParseInput(ParseResult.TODO, input, taskType, descriptionAndTime);
                return parseinputPair;

            case "deadline" :
                parseinputPair = new ParseInput(ParseResult.DEADLINE, input, taskType, descriptionAndTime);
                return parseinputPair;

            case "event" :
                parseinputPair = new ParseInput(ParseResult.EVENT, input, taskType, descriptionAndTime);
                return parseinputPair;

            default :
                throw new DukeException ("I don't understand, try again");
            }
        }
    }
}
