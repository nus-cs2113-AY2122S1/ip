package duke.task;

import duke.Parser;
import duke.exception.WrongNumberOfArgumentsException;

abstract class TimedTask extends Task {
    private String dateTime;

    TimedTask(String description, String dateTime, Type type) {
        super(description, type);
        this.dateTime = dateTime;
    }

    TimedTask(boolean isDone, String description, String dateTime, Type type) {
        super(isDone, description, type);
        this.dateTime = dateTime;
    }

    private static String[] parseUserInput(Type taskType, String userInput) throws WrongNumberOfArgumentsException {
        String[] inputs = userInput.split(Parser.WHITESPACE_REGEX + '/' + taskType.PREPOSITION + Parser.WHITESPACE_REGEX);
        if (inputs.length != 2) {
            throw new WrongNumberOfArgumentsException(inputs[0], taskType.PREPOSITION);
        }
        return inputs;
    }

    public static TimedTask newTimedTask(Type taskType, String userInput) throws WrongNumberOfArgumentsException {
        String[] parsedInputs = parseUserInput(taskType, userInput);
        TimedTask createdTask;
        if (taskType == Type.EVENT) {
            return new Event(parsedInputs[0], parsedInputs[1]);
        }
        return new Deadline(parsedInputs[0], parsedInputs[1]);
    }

    @Override
    public String toString() {
        return String.format("%s (%s: %s)", super.toString(), type.PREPOSITION, dateTime);
    }

    @Override
    String getFormattedString() {
        return super.getFormattedString() + '|' + dateTime;
    }

}
