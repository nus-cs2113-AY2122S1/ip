package commands;

import exceptions.EventException;
import processors.TaskList;
import processors.UI;
import tasks.Event;

public class EventCommand extends Command {
    private static final String EVENT_KEYWORD = "/at";
    private static final Integer EVENT_DIVIDER = 6;
    private static final Integer ZERO = 0;
    private static final Integer TASK_DATE_DIVIDER = 4;

    public UI ui = new UI();

    /**
     * Function adds an Event task into the task list
     * @param taskList the list of tasks
     * @param line the input line from the user
     * @throws EventException when the input line is invalid
     */
    public void execute(TaskList taskList, String line) throws EventException {
        if (!line.contains(EVENT_KEYWORD)) {
            throw new EventException("Event Request Does Not Contain /at");
        }
        int dividerPosition = line.indexOf(EVENT_KEYWORD);

        String description = line.substring(EVENT_DIVIDER, dividerPosition).trim();
        int descriptionLength = description.length();
        if (descriptionLength == ZERO) {
            throw new EventException("Event Request Does Not Contain A Description");
        }

        if (taskList.isDateExist(line.length(), dividerPosition)) {
            throw new EventException("Event Request Does Not Contain A Date");
        }
        String date = line.substring(dividerPosition + TASK_DATE_DIVIDER);

        taskList.add(new Event(description, date));
        ui.printTaskMessage(taskList.getTask(taskList.getLastIndex()), taskList.size());
    }
}
