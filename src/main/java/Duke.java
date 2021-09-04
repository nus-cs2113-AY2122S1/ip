import java.util.Scanner;

public class Duke {

    private static final TaskManager TASK_MANAGER = new TaskManager();
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final InputParser PARSER = new InputParser();

    private static final String COMMAND_EXIT = "bye";
    private static final String COMMAND_LIST_TASKS = "list";
    private static final String COMMAND_CLEAR_TASKS = "clear";
    private static final String COMMAND_MARK_TASK_AS_DONE = "done";
    private static final String COMMAND_ADD_TODO = "todo";
    private static final String COMMAND_ADD_DEADLINE = "deadline";
    private static final String COMMAND_ADD_EVENT = "event";
    private static final String COMMAND_ECHO = "echo";
    private static final String COMMAND_HELP = "help";

    private static final String LOGO = " ██████████              █████\n"
            + "░░███░░░░███            ░░███\n"
            + " ░███   ░░███ █████ ████ ░███ █████  ██████\n"
            + " ░███    ░███░░███ ░███  ░███░░███  ███░░███\n"
            + " ░███    ░███ ░███ ░███  ░██████░  ░███████\n"
            + " ░███    ███  ░███ ░███  ░███░░███ ░███░░░\n"
            + " ██████████   ░░████████ ████ █████░░██████\n"
            + "░░░░░░░░░░     ░░░░░░░░ ░░░░ ░░░░░  ░░░░░░\n";

    private static final String GREET_MESSAGE = "Welcome to\n" + LOGO
            + "Hello there! I'm Duke, your very helpful personal assistant chat bot. \uD83D\uDE0A\n"
            + "Enter \"help\" to see what I can do for you!";
    private static final String EXIT_MESSAGE = "Bye! Have a great day ahead and see you again soon. \uD83D\uDE04";
    private static final String LIST_TASKS_MESSAGE = "Wow! I found these tasks in your list:";
    private static final String CLEAR_TASKS_MESSAGE = "Okay! Now your list is empty, you're FREE!";
    private static final String ADD_TASK_MESSAGE = "Yay! I have added the following task for you:";
    private static final String MARK_TASK_AS_DONE_MESSAGE = "Good job! You have finished the following:";
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

    private static final String ECHO_ERROR =
            "OH NO! I can't echo if you don't say anything...";
    private static final String TODO_ERROR =
            "OH NO! You need to provide a description for your todo...";
    private static final String DEADLINE_DESCRIPTION_ERROR =
            "OH NO! You need to provide a description for your deadline task...";
    private static final String DEADLINE_DATE_ERROR =
            "OH NO! You need to specify a due date for your deadline task...";
    private static final String EVENT_DESCRIPTION_ERROR =
            "OH NO! You need to provide a description for your event...";
    private static final String EVENT_DATE_ERROR =
            "OH NO! You need to specify a date and time for your event...";
    private static final String INVALID_COMMAND_ERROR =
            "Sorry... I did not understand that, can you try again?\n"
                    + "Or you can enter \"help\" to see what I can do for you!";
    private static final String DONE_MISSING_NUMBER_ERROR =
            "OH NO! You need to specify the task you want to mark as done...\n"
                    + "Enter \"list\" to check the task number!";
    private static final String DONE_NUMBER_FORMAT_ERROR = "OH NO! That wasn't a number...";
    private static final String DONE_NUMBER_NOT_FOUND_ERROR =
            "OH NO! The task number is invalid, I can't find any tasks matching that number...\n"
                    + "Enter \"list\" to check the task number!";

    private static final String DIVIDER = "____________________________________________________________";

    private void printDivider() {
        System.out.println(DIVIDER);
    }

    private void printTasksCount() {
        int currentTasksCount = TASK_MANAGER.getCurrentTasksCount();
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

    private void markTaskAsDone(int taskIndex) {
        Task finishedTask = TASK_MANAGER.markTaskAsDone(taskIndex);
        System.out.println(MARK_TASK_AS_DONE_MESSAGE + "\n" + finishedTask.toString());
        printDivider();
    }

    private void showErrorMessage(String message) {
        System.out.println(message);
        printDivider();
    }

    private boolean hasNullParameters(String[] inputArray) {
        return (inputArray.length < 2);
    }

    private boolean isValidTaskNumber(int taskNumber) {
        return (taskNumber <= TASK_MANAGER.getCurrentTasksCount() && taskNumber > 0);
    }

    private void parseUserInputString(String userInputString) throws DukeException {
        String command;
        String[] parsedUserInput;
        String[] params;
        int taskNumber;

        parsedUserInput = PARSER.separateCommand(userInputString);
        command = parsedUserInput[0].toLowerCase();
        switch (command) {
        case COMMAND_ECHO:
            if (hasNullParameters(parsedUserInput)) {
                throw new DukeException(ECHO_ERROR);
            }
            echo(parsedUserInput[1]);
            break;
        case COMMAND_MARK_TASK_AS_DONE:
            if (hasNullParameters(parsedUserInput)) {
                throw new DukeException(DONE_MISSING_NUMBER_ERROR);
            }
            try {
                taskNumber = Integer.parseInt(parsedUserInput[1]);
            } catch (NumberFormatException exception) {
                throw new DukeException(DONE_NUMBER_FORMAT_ERROR);
            }
            if (!isValidTaskNumber(taskNumber)) {
                throw new DukeException(DONE_NUMBER_NOT_FOUND_ERROR);
            }
            markTaskAsDone(taskNumber);
            break;
        case COMMAND_ADD_TODO:
            if (hasNullParameters(parsedUserInput)) {
                throw new DukeException(TODO_ERROR);
            }
            addTodo(parsedUserInput[1]);
            break;
        case COMMAND_ADD_DEADLINE:
            if (hasNullParameters(parsedUserInput)) {
                throw new DukeException(DEADLINE_DESCRIPTION_ERROR);
            }
            params = PARSER.separateDeadline(parsedUserInput[1]);
            if (hasNullParameters(params)) {
                throw new DukeException(DEADLINE_DATE_ERROR);
            }
            if (params[0].isEmpty()) {
                throw new DukeException(DEADLINE_DESCRIPTION_ERROR);
            }
            if (params[1].isEmpty()) {
                throw new DukeException(DEADLINE_DATE_ERROR);
            }
            addDeadline(params[0], params[1]);
            break;
        case COMMAND_ADD_EVENT:
            if (hasNullParameters(parsedUserInput)) {
                throw new DukeException(EVENT_DESCRIPTION_ERROR);
            }
            params = PARSER.separateEvent(parsedUserInput[1]);
            if (hasNullParameters(params)) {
                throw new DukeException(EVENT_DATE_ERROR);
            }
            if (params[0].isEmpty()) {
                throw new DukeException(EVENT_DESCRIPTION_ERROR);
            }
            if (params[1].isEmpty()) {
                throw new DukeException(EVENT_DATE_ERROR);
            }
            addEvent(params[0], params[1]);
            break;
        default:
            throw new DukeException(INVALID_COMMAND_ERROR);
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
                showErrorMessage(exception.message);
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
