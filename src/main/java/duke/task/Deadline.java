package duke.task;

import duke.exception.WrongNumberOfArgumentsException;

class Deadline extends TimedTask {
    private static final String PREPOSITION = "by";
    private static final Task.Types type = Task.Types.DEADLINE;

    Deadline(String userInput) throws WrongNumberOfArgumentsException {
        super(userInput, PREPOSITION, type);
    }
}
