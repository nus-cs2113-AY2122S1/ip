package duke;

import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.EmptyTimeFieldException;
import duke.parser.Parser;
import duke.task.Task;
import duke.task.TaskList;
import java.time.DateTimeException;
import java.util.Scanner;

/**
 * Chatbot program Duke that tracks user tasks.
 *
 * @author Leow Yuan Yang
 * @version 4.0
 * @since 2021-08-25
 */
public class Duke {

    private static final String FILE_PATH = "data/userData.txt";
    private static final String FILE_DIRECTORY = "data";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_FIND = "find";
    private static boolean isDone = false;
    private static Ui ui;
    private static TaskList tasks;
    private static Storage storage;

    public static void main(String[] args) {
        ui = new Ui();
        tasks = new TaskList();
        storage = new Storage(FILE_PATH, FILE_DIRECTORY, ui);
        storage.loadData(tasks);
        Scanner in = new Scanner(System.in);

        ui.printWelcomeMessage();
        do {
            try {
                Parser inputHandler = ui.readUserInput(in);
                executeCommand(inputHandler);
            } catch (EmptyDescriptionException exception) {
                ui.printEmptyDescriptionErrorMessage();
            } catch (EmptyTimeFieldException exception) {
                ui.printEmptyTimeFieldErrorMessage();
            } catch (DateTimeException exception) {
                ui.printWrongTimeFormatErrorMessage();
            } catch (Exception exception) {
                ui.printErrorMessage();
            }
        } while (!isDone);
    }

    /**
     * Executes the respective command.
     *
     * @param inputHandler The Parser object handling the user input.
     */
    private static void executeCommand(Parser inputHandler){
        switch (inputHandler.getCommand()) {
        case COMMAND_BYE:
            executeBye();
            break;
        case COMMAND_LIST:
            executeList();
            break;
        case COMMAND_DONE:
            executeDone(inputHandler.getTaskIndex());
            break;
        case COMMAND_TODO:
            executeTodo(inputHandler.getDescription());
            break;
        case COMMAND_DEADLINE:
            executeDeadline(inputHandler.getDescription(), inputHandler.getFormattedTimeField());
            break;
        case COMMAND_EVENT:
            executeEvent(inputHandler.getDescription(), inputHandler.getFormattedTimeField());
            break;
        case COMMAND_DELETE:
            executeDelete(inputHandler.getTaskIndex());
            break;
        case COMMAND_FIND:
            executeFind(inputHandler.getDescription());
            break;
        default:
            ui.printErrorMessage();
            break;
        }
    }

    /**
     * Execute the bye command.
     */
    private static void executeBye() {
        isDone = true;
        ui.printExitMessage();
    }

    /**
     * Execute the event command.
     *
     * @param description The event description.
     * @param timeField The date of event.
     */
    private static void executeEvent(String description, String timeField) {
        Task newTask = tasks.addEvent(description, timeField);
        storage.saveData(tasks.getTasks());
        ui.printAddTask(newTask, (tasks.getNumOfTasks()));
    }

    /**
     * Execute the deadline command.
     *
     * @param description The deadline description.
     * @param timeField The due date of the deadline.
     */
    private static void executeDeadline(String description, String timeField) {
        Task newTask = tasks.addDeadline(description, timeField);
        storage.saveData(tasks.getTasks());
        ui.printAddTask(newTask, (tasks.getNumOfTasks()));
    }

    /**
     * Execute the todo command.
     *
     * @param description The todo description.
     */
    private static void executeTodo(String description) {
        Task newTask = tasks.addTodo(description);
        storage.saveData(tasks.getTasks());
        ui.printAddTask(newTask, (tasks.getNumOfTasks()));
    }

    /**
     * Execute the done command.
     *
     * @param taskIndex The item index to be marked done.
     */
    private static void executeDone(int taskIndex) {
        Task doneTask = tasks.markDone(taskIndex);
        storage.saveData(tasks.getTasks());
        ui.printDoneTask(doneTask, taskIndex);
    }

    /**
     * Execute the delete command.
     *
     * @param taskIndex The item index to be deleted.
     */
    private static void executeDelete(int taskIndex) {
        Task deletedTask = tasks.deleteTask(taskIndex);
        storage.saveData(tasks.getTasks());
        ui.printDeleteTask(deletedTask);
    }

    /**
     * Execute the find command.
     *
     * @param description The String to match.
     */
    private static void executeFind(String description) {
        ui.printFindTask(tasks.getTasks(), description);
    }

    /**
     * Execute the list command.
     */
    private static void executeList() {
        ui.printTaskList(tasks.getTasks());
    }
}
