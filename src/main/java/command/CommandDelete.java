package command;

import duke.DukeException;
import duke.Parser;
import duke.TaskList;

public class CommandDelete extends Command{
    private static final int FIRST_ARRAY_PARAMETER = 0;
    private static final int SECOND_ARRAY_PARAMETER = 1;

    private String[] words;
    private String[] descriptionInput;

    public CommandDelete(String[] words, String[] descriptionInput) {
        this.words = words;
        this.descriptionInput = descriptionInput;
    }

    /**
     * Delete a task corresponding to the index
     * @throws DukeException when there is no parameters after command
     */
    @Override
    public void run() throws DukeException {
        int taskNumber;
        Parser.checkDescription(words[FIRST_ARRAY_PARAMETER], descriptionInput);
        taskNumber = Integer.parseInt(words[SECOND_ARRAY_PARAMETER]);
        TaskList.deleteTask(taskNumber);
    }
}