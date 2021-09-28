package commands;

import exceptions.UncheckException;
import processors.TaskList;
import processors.UI;

public class UncheckCommand extends Command {
    private static final Integer UNCHECK_LENGTH = 7;
    private static final Integer TASK_DESCRIPTION_DIVIDER = 1;
    private static final Integer ARRAY_INDEX_FINDER = 1;
    private static final String UNCHECK_KEYWORD = " ";

    public UI ui = new UI();

    public void execute(TaskList taskList, String line) throws UncheckException {
        if (line.length() == UNCHECK_LENGTH) {
            throw new UncheckException("Uncheck Request Does Not Contain A Number");
        }
        int dividerPosition = line.indexOf(UNCHECK_KEYWORD);

        String number = line.substring(dividerPosition + TASK_DESCRIPTION_DIVIDER);
        if (taskList.tryParse(number)) {
            throw new UncheckException("Uncheck Request Has Invalid Number");
        }
        int numberInTaskArray = Integer.parseInt(number) - ARRAY_INDEX_FINDER;

        if (taskList.isNotOutOfBounds(numberInTaskArray)) {
            throw new UncheckException("Index Of Task Does Not Exist");
        }

        taskList.getTask(numberInTaskArray).setIsFalse();

        ui.printUncheckMessage(taskList.getTask(numberInTaskArray));
    }
}
