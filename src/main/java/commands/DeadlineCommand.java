package commands;

import exceptions.DeadlineException;
import processors.TaskList;
import processors.Ui;
import tasks.Deadline;

public class DeadlineCommand extends Command {
    private static final String DEADLINE_KEYWORD = "/by";
    private static final Integer DEADLINE_DIVIDER = 9;
    private static final Integer ZERO = 0;
    private static final Integer TASK_DATE_DIVIDER = 4;

    public Ui ui = new Ui();

    public void execute(TaskList taskList, String line) throws DeadlineException {
        if (!line.contains(DEADLINE_KEYWORD)) {
            throw new DeadlineException("Deadline Request Does Not Contain /by");
        }
        int dividerPosition = line.indexOf(DEADLINE_KEYWORD);

        String description = line.substring(DEADLINE_DIVIDER, dividerPosition).trim();
        int descriptionLength = description.length();
        if (descriptionLength == ZERO) {
            throw new DeadlineException("Deadline Request Does Not Contain A Description");
        }

        if (taskList.isDateExist(line.length(), dividerPosition)) {
            throw new DeadlineException("Deadline Request Does Not Contain A Date");
        }
        String date = line.substring(dividerPosition + TASK_DATE_DIVIDER);

        taskList.add(new Deadline(description, date));

        ui.printTaskMessage(taskList.getTask(taskList.getLastIndex()), taskList.size());
    }
}
