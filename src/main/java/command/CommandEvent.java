package command;

import duke.DukeException;
import duke.Event;
import duke.Parser;
import duke.TaskList;

public class CommandEvent extends Command {
    private static final int FIRST_ARRAY_PARAMETER = 0;
    private static final int SECOND_ARRAY_PARAMETER = 1;

    private String word;
    private String[] descriptionInput;

    public CommandEvent(String word, String[] descriptionInput) {
        this.word = word;
        this.descriptionInput = descriptionInput;
    }

    /**
     * Create an event task and put it in list
     * @throws DukeException when incorrect parameters is passed
     */
    @Override
    public void run() throws DukeException {
        Parser.checkDescription(word, descriptionInput);
        Parser.checkTimeframe(descriptionInput);
        Event event = new Event(descriptionInput[FIRST_ARRAY_PARAMETER],
                Parser.parseEventDate(descriptionInput[SECOND_ARRAY_PARAMETER]));
        TaskList.addTask(event);
    }


}
