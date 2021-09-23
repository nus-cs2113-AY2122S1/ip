package command;

import duke.DukeException;
import duke.Parser;
import duke.Ui;

public class CommandFind extends Command{

    private static final int FIRST_ARRAY_PARAMETER = 0;

    private String word;
    private String[] descriptionInput;

    public CommandFind(String word, String[] descriptionInput) {
        this.word = word;
        this.descriptionInput = descriptionInput;
    }

    /**
     * Find all tasks that contains the substring given by the user
     * @throws DukeException when there is no input after find
     */
    @Override
    public void run() throws DukeException {
        Parser.checkDescription(word, descriptionInput);
        Ui.printFilteredDateTimedTask(descriptionInput[FIRST_ARRAY_PARAMETER]);
    }
}
