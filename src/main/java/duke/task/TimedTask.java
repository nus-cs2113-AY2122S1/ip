package duke.task;

import duke.Message;
import duke.exception.WrongNumberOfArgumentsException;

abstract class TimedTask extends Task {
    private String dateTime;
    private final String preposition;

    TimedTask(String userInput, String preposition, Types type) throws WrongNumberOfArgumentsException {
        //TODO: Is using constructor class to parse inputs good OOP?
        super(type);
        String[] inputs = userInput.split(Message.WHITESPACE_REGEX + '/' + preposition + Message.WHITESPACE_REGEX);
        if (inputs.length != 2) {
            throw new WrongNumberOfArgumentsException(type.toString(), preposition);
        }
        this.setDescription(inputs[0]);
        this.dateTime = inputs[1];
        this.preposition = preposition;
    }

    TimedTask(boolean isDone, String description, String dateTime, String preposition, Types type) {
        //TODO: Is using constructor class to parse inputs good OOP?
        super(isDone, description, type);
        this.dateTime = dateTime;
        this.preposition = preposition;
    }

    @Override
    public String toString() {
        //Append preposition and datetime
        return String.format("%s (%s: %s)", super.toString(), preposition, dateTime);
    }

    @Override
    String getFormattedString(){
        return super.getFormattedString() + '|' + dateTime;
    }

}
