package duke;

import duke.exceptions.DukeException;
import duke.exceptions.ExceptionChecker;
import duke.task.Task;
import duke.task.TaskManager;
import duke.util.InputParser;
import java.util.Scanner;

public class Duke {

    private static final TaskManager TASK_MANAGER = new TaskManager();
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final InputParser PARSER = new InputParser();
    private static final ExceptionChecker CHECKER = new ExceptionChecker();

    private static final String COMMAND_EXIT = "bye";
    private static final String COMMAND_LIST_TASKS = "list";
    private static final String COMMAND_CLEAR_TASKS = "clear";
    private static final String COMMAND_MARK_TASK_DONE = "done";
    private static final String COMMAND_ADD_TODO = "todo";
    private static final String COMMAND_ADD_DEADLINE = "deadline";
    private static final String COMMAND_ADD_EVENT = "event";
    private static final String COMMAND_ECHO = "echo";
    private static final String COMMAND_HELP = "help";

    private static final String LOGO = " ______        _\n" +
            "(______)      | |\n"
            + " _     _ _   _| |  _ _____\n"
            + "| |   | | | | | |_/ ) ___ |\n"
            + "| |__/ /| |_| |  _ (| ____|\n"
            + "|_____/ |____/|_| \\_)_____)\n";

    private static final String GREET_MESSAGE = "Welcome to\n" + LOGO
            + "Hello there! I'm Duke, your very helpful personal assistant chat bot. \uD83D\uDE0A\n"
            + "Enter \"help\" to see what I can do for you!";
    private static final String EXIT_MESSAGE = "Bye! Have a great day ahead and see you again soon. \uD83D\uDE04";
    private static final String LIST_TASKS_MESSAGE = "Wow! I found these tasks in your list:";
    private static final String CLEAR_TASKS_MESSAGE = "Okay! Now your list is empty, you're FREE!";
    private static final String ADD_TASK_MESSAGE = "Yay! I have added the following task for you:";
    private static final String MARK_TASK_DONE_MESSAGE = "Good job! You have finished the following:";
    private static final String HELP_MESSAGE =
            "Below is the list of commands and input formats I am currently able to understand:\n"
                    + "1. list - Lists all your current tasks.\n"
                    + "2. clear - Clears all your existing tasks in your list.\n"
                    + "3. todo [task description] - Adds a task to your list.\n"
                    + "4. deadline [task description] /by [due date] - Adds a task with a due date to your list.\n"
                    + "5. event [event description] /at [date and time] - Adds an upcoming event to your list.\n"
                    + "6. done [task number] - Marks the task as done. Use the list to check the task number!\n"
                    + "7. echo [input] - Echoes whatever your input is.\n"
                    + "8. bye - Stop talking to me and exit the program.";

    private static final String DIVIDER = "____________________________________________________________";

    private void printDivider() {
        System.out.println(DIVIDER);
    }

    private void printTasksCount() {
        int currentTasksCount = TaskManager.getCurrentTasksCount();
        System.out.println("You have " + currentTasksCount + " tasks in your list now!");
    }

    private void greet() {
        System.out.println(GREET_MESSAGE);
        printDivider();
    }

    private void exit() {
        System.out.println(EXIT_MESSAGE);
        printDivider();
    }

    private void echo(String input) {
        System.out.println(input);
        printDivider();
    }

    private void showHelpMessage() {
        System.out.println(HELP_MESSAGE);
        printDivider();
    }

    private void addTodo(String taskDescription) {
        Task addedTask = TASK_MANAGER.addTodo(taskDescription);
        System.out.println(ADD_TASK_MESSAGE + "\n" + addedTask.toString());
        printTasksCount();
        printDivider();
    }

    private void addDeadline(String taskDescription, String taskDue) {
        Task addedTask = TASK_MANAGER.addDeadline(taskDescription, taskDue);
        System.out.println(ADD_TASK_MESSAGE + "\n" + addedTask.toString());
        printTasksCount();
        printDivider();
    }

    private void addEvent(String taskDescription, String eventDateTime) {
        Task addedTask = TASK_MANAGER.addEvent(taskDescription, eventDateTime);
        System.out.println(ADD_TASK_MESSAGE + "\n" + addedTask.toString());
        printTasksCount();
        printDivider();
    }

    private void listTasks() {
        System.out.println(LIST_TASKS_MESSAGE);
        TASK_MANAGER.listTasks();
        printDivider();
    }

    private void clearAllTasks() {
        TASK_MANAGER.clearAllTasks();
        System.out.println(CLEAR_TASKS_MESSAGE);
        printDivider();
    }

    private void markTaskDone(int taskIndex) {
        Task finishedTask = TASK_MANAGER.markTaskDone(taskIndex);
        System.out.println(MARK_TASK_DONE_MESSAGE + "\n" + finishedTask.toString());
        printDivider();
    }

    private void showErrorMessage(String message) {
        System.out.println(message);
        printDivider();
    }

    private void parseUserInputString(String userInputString) throws DukeException {
        String command;
        String[] userInputArray;
        String param;
        String[] params;
        int taskNumber;

        userInputArray = PARSER.separateCommand(userInputString);
        command = userInputArray[0].toLowerCase();
        switch (command) {
        case COMMAND_ECHO:
            param = CHECKER.retrieveEchoParameter(userInputArray);
            echo(param);
            break;
        case COMMAND_MARK_TASK_DONE:
            taskNumber = CHECKER.retrieveDoneParameter(userInputArray);
            markTaskDone(taskNumber);
            break;
        case COMMAND_ADD_TODO:
            param = CHECKER.retrieveTodoParameter(userInputArray);
            addTodo(param);
            break;
        case COMMAND_ADD_DEADLINE:
            params = CHECKER.retrieveDeadlineParameters(userInputArray);
            addDeadline(params[0], params[1]);
            break;
        case COMMAND_ADD_EVENT:
            params = CHECKER.retrieveEventParameters(userInputArray);
            addEvent(params[0], params[1]);
            break;
        default:
            CHECKER.throwInput();
        }
    }

    private void handleUserInput(String userInputString) {

        switch (userInputString.toLowerCase()) {
        case COMMAND_LIST_TASKS:
            listTasks();
            break;
        case COMMAND_CLEAR_TASKS:
            clearAllTasks();
            break;
        case COMMAND_HELP:
            showHelpMessage();
            break;
        default:
            try {
                parseUserInputString(userInputString);
            } catch (DukeException exception) {
                showErrorMessage(exception.getMessage());
            }
            break;
        }
    }

    public void execute() {

        String userInputString;

        greet();
        while (true) {
            userInputString = SCANNER.nextLine().trim();

            if (userInputString.equalsIgnoreCase(COMMAND_EXIT)) {
                exit();
                break;
            }
            handleUserInput(userInputString);
        }
    }
}
