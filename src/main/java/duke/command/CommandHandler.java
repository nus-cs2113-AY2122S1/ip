package duke.command;

import duke.output.OutputHandler;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;

public class CommandHandler {

    public static final String DEADLINE_SEPARATOR = "/by ";
    public static final String EVENT_SEPARATOR = "/at ";
    private static final String TASK_SEPARATOR = " ";

    /**
     * Print messages and changes the task list according to the commands given.
     * @param command The command entered by the user
     * @param input The input string read from the Scanner object
     * @param tasks The list of tasks
     */
    public void handleCommand(Command command, String input, ArrayList<Task> tasks) {

        OutputHandler outputHandler = new OutputHandler();
        String[] inputTokens = input.split(" ");

        switch (command) {
        case LIST_TASKS:
            if (tasks.size() == 0) {
                outputHandler.printNoTasksInListMessage();
            } else {
                outputHandler.printTaskList(tasks);
            }
            break;

        case MARK_DONE:
            try {
                //get 1-indexed task number and convert to 0-index
                int taskNumber = Integer.parseInt(inputTokens[1]) - 1;
                if (tasks.get(taskNumber).isDone()) {
                    outputHandler.printTaskDoneMessage();
                } else {
                    outputHandler.markAsDone(tasks, taskNumber);
                }
            } catch (IndexOutOfBoundsException e) {
                outputHandler.printTaskNumberOutOfBoundsMessage();
            } catch (NumberFormatException e) {
                outputHandler.printInvalidTaskNumberMessage();
            }
            break;

        case ADD_TODO:
            try {
                int indexOfTask = input.indexOf(TASK_SEPARATOR);
                String description = outputHandler.getTrimmedSubstring(input, indexOfTask, input.length());
                if (description.isBlank()) {
                    outputHandler.printNoTaskNameMessage();
                } else {
                    Todo todo = new Todo(description);
                    tasks.add(todo);
                    outputHandler.addTask(tasks, todo);
                }
            } catch (IndexOutOfBoundsException e) {
                outputHandler.printNoTaskNameMessage();
            }
            break;

        case ADD_DEADLINE:
            try {
                int indexOfTask = input.indexOf(TASK_SEPARATOR);
                int indexOfEventDate = input.indexOf(DEADLINE_SEPARATOR);
                //extract task and due date
                String description = outputHandler.getTrimmedSubstring(input, indexOfTask, indexOfEventDate);
                String by = outputHandler.getTrimmedSubstring(input,
                        indexOfEventDate + DEADLINE_SEPARATOR.length(),
                        input.length());
                if (description.isBlank() || by.isBlank()) {
                    outputHandler.printNoDeadlineMessage();
                } else {
                    Deadline deadline = new Deadline(description, by);
                    tasks.add(deadline);
                    outputHandler.addTask(tasks, deadline);
                }
            } catch (IndexOutOfBoundsException e) {
                outputHandler.printNoDeadlineMessage();
            }
            break;

        case ADD_EVENT:
            try {
                int indexOfTask = input.indexOf(TASK_SEPARATOR);
                int indexOfEventDate = input.indexOf(EVENT_SEPARATOR);
                //extract task and due date
                String description = outputHandler.getTrimmedSubstring(input, indexOfTask, indexOfEventDate);
                String at = outputHandler.getTrimmedSubstring(input,
                        indexOfEventDate + EVENT_SEPARATOR.length(),
                        input.length());
                if (description.isBlank() || at.isBlank()) {
                    outputHandler.printNoEventMessage();
                } else {
                    Event event = new Event(description, at);
                    tasks.add(event);
                    outputHandler.addTask(tasks, event);
                }
            } catch (IndexOutOfBoundsException e) {
                outputHandler.printNoEventMessage();
            }
            break;

        case DELETE_TASK:
            try {
                int taskNumber = Integer.parseInt(inputTokens[1]) - 1;
                outputHandler.deleteTask(tasks, taskNumber);
            } catch (IndexOutOfBoundsException e) {
                outputHandler.printTaskNumberOutOfBoundsMessage();
            } catch (NumberFormatException e) {
                outputHandler.printInvalidTaskNumberMessage();
            }
            break;

        case HELP:
            outputHandler.printHelpMessage();
            break;

        case GREETING:
            outputHandler.printGreetingMessage();
            break;

        case EXIT:
            outputHandler.printExitMessage();
            break;

        case DEFAULT:
            outputHandler.printInvalidCommandMessage();
            break;

        default:
            outputHandler.printUnknownErrorMessage();
            break;
        }
    }
}
