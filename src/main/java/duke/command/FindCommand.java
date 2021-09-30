package duke.command;

import duke.ui.Ui;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.Task;

/**
 * Represents the command to find tasks based on a keyword
 */
public class FindCommand extends Command{

    private static final int FIND_SUBSTRING_INDEX = 5;
    
    private String input;

    public FindCommand(String input) {
        this.input = input.toLowerCase();
    }

    /**
     * Executes the command to find tasks based on a keyword
     *
     * @param list The tasklist instance to handle interactions with the ArrayList of task
     * @param ui The ui instance to handle interactions with the user
     * @param storage The storage instance to handle interactions with the text file
     */
    @Override
     public void execute(TaskList list, Ui ui, Storage storage){
        try {
            String keyword = input.substring(FIND_SUBSTRING_INDEX).trim();
            if (keyword.length() == 0) {
                throw new DukeException();
            }
            boolean isFound = false;
            Task task;
            for (int i = 0; i < list.size(); i += 1) {
                task = list.getList().get(i);
                if (task.getName().toLowerCase().contains(keyword)) {
                    if (!isFound) {
                        ui.printFoundTaskMessage();
                        isFound = true;
                    }
                    ui.printTask(task, i+1);
                }
            }
            if (!isFound) {
                ui.printNoTaskFoundMessage();
            } else {
                ui.printLine();
            }
        } catch (StringIndexOutOfBoundsException | DukeException e) {
            ui.printWrongCommandFormatMessage();
        }
     }

    /**
     * @return returns true if the command to exit the application is given
     */
    @Override
    public boolean isExit() {
        return false;
    }
}