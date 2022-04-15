package duke;

import duke.parser.Parser;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

import java.util.Scanner;

public class Duke {

    /**
     * Constants for user input Commands
     */
    private static final String COMMAND_EXIT = "bye";
    private static final String COMMAND_LIST_TASKS = "list";
    private static final String COMMAND_MARK_AS_DONE = "done";
    private static final String COMMAND_ADD_TODO = "todo";
    private static final String COMMAND_ADD_DEADLINE = "deadline";
    private static final String COMMAND_ADD_EVENT = "event";
    private static final String COMMAND_DELETE_TASK = "delete";
    private static final String COMMAND_FIND = "find";
    private static final String COMMAND_UPCOMING_TASKS = "upcoming";

    /**
     * The input scanner for the program
     */
    public static final Scanner SCANNER_INPUT = new Scanner(System.in);

    /**
     * Stores the most recent input from the user in String format
     */
    public static String strInput = "";

    /**
     * "false" by default. "true" when session with duke is to be ended.
     */
    public static boolean isSessionEnding = false;

    private static Ui ui;
    private static Parser parser;
    private static TaskList taskList;

    public static void main(String[] args) {
        initDuke();
        ui.printGreetingMessage();
        runMainLoop();
    }

    /**
     * Contains the main loop of the program that continues to read inputs from
     * the user and perform the requested operations by the user. This continues
     * until the user requests to terminate the program.
     */
    private static void runMainLoop() {
        while (!isSessionEnding) {
            strInput = SCANNER_INPUT.nextLine();
            String command = parser.extractCommand(strInput);
            switch (command) {
            case COMMAND_EXIT:
                isSessionEnding = true;
                ui.printGoodbyeMessage();
                break;

            case COMMAND_LIST_TASKS:
                taskList.showTaskList();
                break;

            case COMMAND_MARK_AS_DONE:
                taskList.markTaskAsDone(strInput);
                break;

            case COMMAND_ADD_TODO:
                taskList.addTodo(strInput);
                break;

            case COMMAND_ADD_DEADLINE:
                taskList.addDeadline(strInput);
                break;

            case COMMAND_ADD_EVENT:
                taskList.addEvent(strInput);
                break;

            case COMMAND_DELETE_TASK:
                taskList.deleteTask(strInput);
                break;

            case COMMAND_FIND:
                taskList.showSearchList(strInput);
                break;

            case COMMAND_UPCOMING_TASKS:
                taskList.showUpcoming(strInput);
                break;

            default:
                ui.printInvalidCommand();
                break;
            }
        }
    }

    /**
     * Initializes the required objects to run the program and loads the save
     * file(if present) into this instance of the program.
     */
    private static void initDuke() {
        taskList = new TaskList();
        ui = new Ui();
        parser = new Parser();
        Storage storage = new Storage();
        //load storage data into the task list
        taskList.updateTaskList(storage.loadFile());
    }
    
}
