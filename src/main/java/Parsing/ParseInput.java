package Parsing;

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
