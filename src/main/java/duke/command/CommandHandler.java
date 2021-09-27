package duke.command;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.tasklist.TaskList;

import java.io.IOException;
import java.util.ArrayList;

public class CommandHandler {
    public static final String DEADLINE_SEPARATOR = "/by ";
    public static final String EVENT_SEPARATOR = "/at ";
    public static final String TASK_SEPARATOR = " ";

    Ui ui;
    ArrayList<Task> tasks;

    public CommandHandler() {
        ui = new Ui();
    }

    /**
     * Carries out instructions based on input and commands received
     * @param command command detected in input
     * @param input user input
     * @param tasksList task list editor containing list of tasks
     * @param storage file editor
     */
    public void handleCommand(CommandType command, String input, TaskList tasksList, Storage storage) {
        String[] words = input.split(" ");
        tasks = tasksList.getTasks();

        switch (command) {
        case LIST_TASKS:
            if (tasks.size() == 0) {
                ui.printNoTasksInListMessage();
            } else {
                ui.printTaskList(tasks);
            }
            break;

        case MARK_DONE:
            try {
                //get 1-indexed task number and convert to 0-index
                int taskNumber = Integer.parseInt(words[1]) - 1;
                if (tasks.get(taskNumber).isDone()) {
                    ui.printTaskDoneMessage();
                } else {
                    tasksList.markDone(taskNumber);
                    ui.printMarkedAsDone(tasks, taskNumber);
                }
            } catch (IndexOutOfBoundsException e) {
                ui.printTaskNumberOutOfBoundsMessage();
            } catch (NumberFormatException e) {
                ui.printInvalidTaskNumberMessage();
            }
            break;

        case ADD_TODO:
            try {
                int indexOfTask = input.indexOf(TASK_SEPARATOR);
                String description = ui.getTrimmedSubstring(input, indexOfTask, input.length());
                if (description.isBlank()) {
                    ui.printNoTaskNameMessage();
                } else {
                    Todo todo = new Todo(description);
                    tasksList.addTask(todo);
                    saveTasks(tasks, storage);
                    ui.printAddedTask(tasks, todo);
                }
            } catch (IndexOutOfBoundsException e) {
                ui.printNoTaskNameMessage();
            }
            break;

        case ADD_DEADLINE:
            try {
                int indexOfTask = input.indexOf(TASK_SEPARATOR);
                int indexOfEventDate = input.indexOf(DEADLINE_SEPARATOR);
                String description = ui.getTrimmedSubstring(input, indexOfTask, indexOfEventDate);
                String by = ui.getTrimmedSubstring(input,
                        indexOfEventDate + DEADLINE_SEPARATOR.length(),
                        input.length());
                if (description.isBlank() || by.isBlank()) {
                    ui.printNoDeadlineMessage();
                } else {
                    Deadline deadline = new Deadline(description, by);
                    tasksList.addTask(deadline);
                    saveTasks(tasks, storage);
                    ui.printAddedTask(tasks, deadline);
                }
            } catch (IndexOutOfBoundsException e) {
                ui.printNoDeadlineMessage();
            }
            break;

        case ADD_EVENT:
            try {
                int indexOfTask = input.indexOf(TASK_SEPARATOR);
                int indexOfEventDate = input.indexOf(EVENT_SEPARATOR);
                String description = ui.getTrimmedSubstring(input, indexOfTask, indexOfEventDate);
                String at = ui.getTrimmedSubstring(input,
                        indexOfEventDate + EVENT_SEPARATOR.length(),
                        input.length());
                if (description.isBlank() || at.isBlank()) {
                    ui.printNoEventMessage();
                } else {
                    Event event = new Event(description, at);
                    tasksList.addTask(event);
                    saveTasks(tasks, storage);
                    ui.printAddedTask(tasks, event);
                }
            } catch (IndexOutOfBoundsException e) {
                ui.printNoEventMessage();
            }
            break;

        case DELETE_TASK:
            try {
                int taskNumber = Integer.parseInt(words[1]) - 1;
                Task task = tasksList.deleteTask(taskNumber);
                saveTasks(tasks, storage);
                ui.printDeletedTask(tasks, task);
            } catch (IndexOutOfBoundsException e) {
                ui.printTaskNumberOutOfBoundsMessage();
            } catch (NumberFormatException e) {
                ui.printInvalidTaskNumberMessage();
            }
            break;

        case HELP:
            ui.printHelpMessage();
            break;

        case GREETING:
            ui.printGreetingMessage();
            break;

        case EXIT:
            ui.printExitMessage();
            break;

        case DEFAULT:
            ui.printInvalidCommandMessage();
            break;

        case FIND:
            ui.printMatchingTasks(tasks, words[1]);
            break;

        default:
            ui.printUnknownErrorMessage();
            break;
        }
    }

    /**
     * Save tasks in list to a file
     * @param tasks list of tasks
     * @param storage file editor
     */
    public void saveTasks(ArrayList<Task> tasks, Storage storage) {
        try {
            storage.saveListToFile(tasks);
        } catch (IOException e) {
            ui.printFileSaveError(e);
        }
    }
}
