package task;

public class Deadline extends TimedTask {
    private static final String PREPOSITION = "by";
    private static final Task.Types type = Task.Types.DEADLINE;

    public Deadline(String userInput) throws WrongNumberOfArgumentsException {
        super(userInput, PREPOSITION, type);
    }
}
