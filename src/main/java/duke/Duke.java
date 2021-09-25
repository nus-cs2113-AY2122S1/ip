package duke;

import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.EmptyTimeFieldException;
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
    private static final String ICON_TODO = "T";
    private static final String ICON_DEADLINE = "D";
    private static final String ICON_EVENT = "E";
    private static final String ICON_COMPLETEDTASK = "X";

    private static boolean isDone = false;
    private static Ui ui = new Ui();
    private static TaskList tasks = new TaskList();
    private static Storage storage = new Storage(FILE_PATH, FILE_DIRECTORY, ui);


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        storage.loadData(tasks);
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
        default:
            ui.printErrorMessage();
            break;
        }
    }

    private static void executeBye() {
        isDone = true;
        storage.saveData(tasks.getTasks());
        ui.printExitMessage();
    }

    private static void executeEvent(String description, String timeField) {
        Task newTask = tasks.addEvent(description, timeField);
        ui.printAddTask(newTask, (tasks.getNumOfTasks()));
    }

    private static void executeDeadline(String description, String timeField) {
        Task newTask = tasks.addDeadline(description, timeField);
        ui.printAddTask(newTask, (tasks.getNumOfTasks()));
    }

    private static void executeTodo(String description) {
        Task newTask = tasks.addTodo(description);
        ui.printAddTask(newTask, (tasks.getNumOfTasks()));
    }

    private static void executeDone(int taskIndex) {
        Task doneTask = tasks.markDone(taskIndex);
        ui.printDoneTask(doneTask, taskIndex);
    }

    private static void executeDelete(int taskIndex) {
        Task deletedTask = tasks.deleteTask(taskIndex);
        ui.printDeleteTask(deletedTask);
    }

    private static void executeList() {
        ui.printTaskList(tasks.getTasks());
    }
}
