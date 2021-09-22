package commands;

import exceptions.DeleteException;
import processors.TaskList;
import processors.Ui;

public class DeleteCommand extends Command {
    private static final Integer ARRAY_INDEX_FINDER = 1;
    private static final Integer TASK_DESCRIPTION_DIVIDER = 1;
    private static final Integer TASK_DATE_DIVIDER = 4;
    private static final String DONE_DELETE_KEYWORD = " ";

    public Ui ui = new Ui();

    /**
     * Function deletes the task - from the list of tasks - specified by the user
     * @param taskList the list of tasks
     * @param line the input line from the user
     * @throws DeleteException when the input line is invalid
     */
    public void execute(TaskList taskList, String line) throws DeleteException {
        if (line.length() == TASK_DATE_DIVIDER) {
            throw new DeleteException("Request Does Not Contain A Number");
        }
        int dividerPosition = line.indexOf(DONE_DELETE_KEYWORD);

        String number = line.substring(dividerPosition + TASK_DESCRIPTION_DIVIDER);
        if (taskList.tryParse(number)) {
            throw new DeleteException("Request Has Invalid Number");
        }
        int numberInTaskArray = Integer.parseInt(number) - ARRAY_INDEX_FINDER;

        if (taskList.isIndexExist(numberInTaskArray)) {
            throw new DeleteException("Array Out Of Bonds");
        }

        ui.printDeleteMessage(taskList.getTask(numberInTaskArray), taskList.size());

        taskList.delete(numberInTaskArray);
    }
}
