public class CommandFind extends Command{

    private static final int FIRST_ARRAY_PARAMETER = 0;

    private String word;
    private String[] descriptionInput;

    public CommandFind(String word, String[] descriptionInput) {
        this.word = word;
        this.descriptionInput = descriptionInput;
    }

    @Override
    public void run() throws DukeException {
        Parser.checkDescription(word, descriptionInput);
        Ui.printFilteredDateTimedTask(descriptionInput[FIRST_ARRAY_PARAMETER]);
    }
}
