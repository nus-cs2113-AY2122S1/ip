public class Deadline extends TimedTask {
    private static final String PREPOSITION = "by";
    private static final Types type = Types.DEADLINE;

    public Deadline(String userInput) {
        super(userInput, PREPOSITION, type);
    }
}
