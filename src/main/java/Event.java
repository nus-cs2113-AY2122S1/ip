public class Event extends TimedTask {
    private static final String PREPOSITION = "at";
    private static final Types type = Types.EVENT;

    public Event(String userInput) throws WrongNumberOfArgumentsException {
        super(userInput, PREPOSITION, type);
    }
}
