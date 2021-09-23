package command;

import duke.Deadline;
import duke.DukeException;
import duke.Parser;
import duke.TaskList;

public class CommandDeadline extends Command{
    private static final int FIRST_ARRAY_PARAMETER = 0;
    private static final int SECOND_ARRAY_PARAMETER = 1;

    private String word;
    private String[] descriptionInput;

    public CommandDeadline(String word, String[] descriptionInput) {
        this.word = word;
        this.descriptionInput = descriptionInput;
    }

    /**
     * Create a deadline task in list
     * @throws DukeException if description input is incorrect
     */
    @Override
    public void run() throws DukeException {
        Parser.checkDescription(word, descriptionInput);
        Parser.checkTimeframe(descriptionInput);
        Deadline deadline = new Deadline(descriptionInput[FIRST_ARRAY_PARAMETER],
                Parser.parseDeadlineDate(descriptionInput[SECOND_ARRAY_PARAMETER]));
        TaskList.addTask(deadline);
    }


}
