package command;

import duke.DukeException;
import duke.Parser;
import duke.TaskList;

public class CommandDone extends Command{
    private static final int FIRST_ARRAY_PARAMETER = 0;
    private static final int SECOND_ARRAY_PARAMETER = 1;

    private String[] words;
    private String[] descriptionInput;

    public CommandDone(String[] words, String[] descriptionInput) {
        this.words = words;
        this.descriptionInput = descriptionInput;
    }
    @Override
    public void run() throws DukeException {
        Parser.checkDescription(words[FIRST_ARRAY_PARAMETER], descriptionInput);
        int taskNumber = Integer.parseInt(words[SECOND_ARRAY_PARAMETER]);
        TaskList.checkDoneTask(taskNumber);
    }
}
