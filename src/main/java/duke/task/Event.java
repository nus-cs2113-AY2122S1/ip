package duke.task;

import duke.exception.WrongNumberOfArgumentsException;

class Event extends TimedTask {
    private static final String PREPOSITION = "at";
    private static final Task.Types type = Task.Types.EVENT;

    Event(String userInput) throws WrongNumberOfArgumentsException {
        super(userInput, PREPOSITION, type);
    }
}
