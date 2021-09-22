package commands;

import exceptions.DoneException;
import processors.TaskList;
import processors.Ui;

public class DoneCommand extends Command {
    private static final Integer DONE_LENGTH = 4;
    private static final Integer TASK_DATE_DIVIDER = 4;
    private static final Integer TASK_DESCRIPTION_DIVIDER = 1;
    private static final Integer ARRAY_INDEX_FINDER = 1;
    private static final String DONE_DELETE_KEYWORD = " ";

    public Ui ui = new Ui();

    public void execute(TaskList taskList, String line) throws DoneException {
        if (line.length() == TASK_DATE_DIVIDER) {
            throw new DoneException("Request Does Not Contain A Number");
        }

        if (line.length() == DONE_LENGTH) {
            throw new DoneException("Done Request Does Not Contain A Number");
        }
        int dividerPosition = line.indexOf(DONE_DELETE_KEYWORD);

        String number = line.substring(dividerPosition + TASK_DESCRIPTION_DIVIDER);
        if (taskList.tryParse(number)) {
            throw new DoneException("Done Request Has Invalid Number");
        }
        int numberInTaskArray = Integer.parseInt(number) - ARRAY_INDEX_FINDER;

        if (taskList.isIndexExist(numberInTaskArray)) {
            throw new DoneException("Index Of Task Does Not Exist");
        }

        taskList.getTask(numberInTaskArray).setIsDone();

        ui.printDoneMessage(taskList.getTask(numberInTaskArray));
    }
}
