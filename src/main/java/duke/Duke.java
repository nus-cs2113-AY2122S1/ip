package duke;
import static duke.Parser.inputSort;
import static duke.Ui.start;

/**
 * Main class of Duke bot.
 *
 * @author pragyan01
 */
public class Duke {

    /**
     * Main method of Duke bot.
     */
    public static void main(String[] args) throws DukeException {
        start();
        inputSort();
    }
}

