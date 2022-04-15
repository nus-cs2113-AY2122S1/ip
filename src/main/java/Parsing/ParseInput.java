package Parsing;

/**
 * Denotes how parsed information from User input is represented in Duke
 *
 * A ParseInput object contains the following attributes
 * 1. parseResult (an ENUM mapping to valid commands that Duke understands)
 * 2. userInput (raw User input)
 * 3. taskType (valid only when User adds a Task; corresponding to Task, Deadline, Event)
 * 4. descriptionAndTime (valid only when User adds a Task; corresponds to the Task description (and time, if valid)
 */
public class ParseInput {

    public ParseResult parseResult;
    public String userInput;
    public String taskType;
    public String descriptionAndTime;

    public ParseInput (ParseResult parseResult, String userInput) {
        this.parseResult = parseResult;
        this.userInput = userInput;
    }

    public ParseInput (ParseResult parseResult, String userInput, String taskType, String descriptionAndTime) {
        this.parseResult = parseResult;
        this.userInput = userInput;
        this.taskType = taskType;
        this.descriptionAndTime = descriptionAndTime;
    }
}
