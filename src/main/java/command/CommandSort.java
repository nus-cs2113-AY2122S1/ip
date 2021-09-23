package command;

import duke.DukeException;
import duke.Parser;
import duke.Ui;

public class CommandSort extends Command{

    private static final int FIRST_ARRAY_PARAMETER = 0;

    private String word;
    private String[] descriptionInput;

    public CommandSort(String word, String[] descriptionInput) {
        this.word = word;
        this.descriptionInput = descriptionInput;
    }

    @Override
    public void run() throws DukeException {
        Parser.checkDescription(word, descriptionInput);
        sortRequiredList(descriptionInput[FIRST_ARRAY_PARAMETER]);
    }

    /**
     * Check if the keyword given by the user is correct
     * Format: sort 'keyword'
     * e.g. sort time
     * @param keyword
     * @throws DukeException when the keyword is incompatible
     */
    public static void sortRequiredList(String keyword) throws DukeException {
        if (keyword.equals("time")) {
            Ui.printSortedDateTimedTask();
        } else {
            throw new DukeException("No such option for sort");
        }
    }
}
