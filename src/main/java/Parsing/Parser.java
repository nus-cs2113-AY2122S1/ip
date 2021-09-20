package Parsing;

import Exception.DukeException;

/**
 * Denotes how User input is parsed (for useful information) to use in Duke
 */
public class Parser {

    protected static void checkDescription (String input) throws DukeException {
        if ( (input.contains("todo") || input.contains("deadline") || input.contains("event"))
                && input.split(" ").length == 1) {
            throw new DukeException("Description cannot be empty"); }
    }

    /**
     * Transforms User input into something that Duke can use.
     * Both invalid and valid commands will be transformed appropriately.
     *
     * @param input          raw User input
     * @return ParseInput    Object encapsulating different aspects of parsed User input
     * @throws DukeException thrown if Task description is empty, or User input is invalid (not understood)
     */
    public static ParseInput parse (String input) throws DukeException {
        ParseInput parseInput;

        //---------- Non-Insertion Checks ----------//
        if (input.equals("bye")) {
            parseInput = new ParseInput(ParseResult.BYE, input);
            return parseInput;
        }

        else if (input.equals("list")) {
            parseInput = new ParseInput(ParseResult.LIST, input);
            return parseInput;
        }

        else if (input.contains("done")) {
            parseInput = new ParseInput(ParseResult.DONE, input);
            return parseInput;
        }

        else if (input.contains("delete")) {
            parseInput = new ParseInput(ParseResult.DELETE, input);
            return parseInput;
        }

        else if (input.contains("find")) {
            parseInput = new ParseInput(ParseResult.FIND, input);
            return parseInput;
        }

        //---------- Insertion Checks ----------//
        else {
            String taskType = input.split(" ")[0];
            String descriptionAndTime = input.replaceAll(taskType, "");
            Parser.checkDescription(input);

            switch (taskType) {
            case "todo" :
                parseInput = new ParseInput(ParseResult.TODO, input, taskType, descriptionAndTime);
                return parseInput;

            case "deadline" :
                parseInput = new ParseInput(ParseResult.DEADLINE, input, taskType, descriptionAndTime);
                return parseInput;

            case "event" :
                parseInput = new ParseInput(ParseResult.EVENT, input, taskType, descriptionAndTime);
                return parseInput;

            default :
                throw new DukeException ("I don't understand, try again");
            }
        }
    }
}
