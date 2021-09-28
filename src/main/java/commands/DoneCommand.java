package commands;

import exceptions.DoneException;
import processors.TaskList;
import processors.UI;

public class DoneCommand extends Command {
    private static final Integer DONE_LENGTH = 4;
    private static final Integer TASK_DESCRIPTION_DIVIDER = 1;
    private static final Integer ARRAY_INDEX_FINDER = 1;
    private static final String DONE_DELETE_KEYWORD = " ";

    public UI ui = new UI();

    /**
     * Function sets the isDone attribute of task in the task list to true
     * @param taskList the list of tasks
     * @param line the input line from the user
     * @throws DoneException when the input line is invalid
     */
    public void execute(TaskList taskList, String line) throws DoneException {
        if (line.length() == DONE_LENGTH) {
            throw new DoneException("Done Request Does Not Contain A Number");
        }
        int dividerPosition = line.indexOf(DONE_DELETE_KEYWORD);

        String number = line.substring(dividerPosition + TASK_DESCRIPTION_DIVIDER);
        if (taskList.tryParse(number)) {
            throw new DoneException("Done Request Has Invalid Number");
        }
        int numberInTaskArray = Integer.parseInt(number) - ARRAY_INDEX_FINDER;

        if (taskList.isNotOutOfBounds(numberInTaskArray)) {
            throw new DoneException("Index Of Task Does Not Exist");
        }

        taskList.getTask(numberInTaskArray).setIsDone();

        ui.printDoneMessage(taskList.getTask(numberInTaskArray));
    }
}
