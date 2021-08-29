import java.util.Scanner;

public class Duke {

    private static final TaskManager TASK_MANAGER = new TaskManager();
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final InputParser PARSER = new InputParser();

    private static final String COMMAND_EXIT = "bye";
    private static final String COMMAND_LIST_TASKS = "list";
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
    private static final String INVALID_COMMAND_MESSAGE =
            "Sorry... I did not understand that, can you try again? >.<\n"
            + "Enter \"help\" to see what I can do for you!";
    private static final String LIST_TASKS_MESSAGE = "Wow! I found these tasks in your list:";
    private static final String ADD_TASK_MESSAGE = "Yay! I have added the following task for you:";
    private static final String MARK_TASK_AS_DONE_MESSAGE = "Good job! You have finished the following:";
    private static final String HELP_MESSAGE =
            "Below is the list of commands and input formats I am currently able to understand:\n"
            + "1. list - Lists all your current tasks.\n"
            + "2. todo [task description] - Adds a task to your list.\n"
            + "3. deadline [task description] /by [deadline] - Adds a task with a deadline to your list.\n"
            + "4. event [event description] /at [date and time] - Adds an upcoming event to your list.\n"
            + "5. done [task number] - Marks the task as done. Use the list to check the task number!\n"
            + "6. echo [input] - Echoes whatever your input is.\n"
            + "7. bye - Stop talking to me and exit the program.\n"
            + "I am currently not able to handle erroneous inputs myself... So please be careful in what you type!";
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

    private void showInvalidCommandMessage() {
        System.out.println(INVALID_COMMAND_MESSAGE);
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

    private void addDeadline(String taskDescription, String deadline) {
        Task addedTask = TASK_MANAGER.addDeadline(taskDescription, deadline);
        System.out.println(ADD_TASK_MESSAGE + "\n" + addedTask.toString());
        printTasksCount();
        printDivider();
    }

    private void addEvent(String taskDescription, String dateAndTime) {
        Task addedTask = TASK_MANAGER.addEvent(taskDescription, dateAndTime);
        System.out.println(ADD_TASK_MESSAGE + "\n" + addedTask.toString());
        printTasksCount();
        printDivider();
    }

    private void listTasks() {
        System.out.println(LIST_TASKS_MESSAGE);
        TASK_MANAGER.listTasks();
        printDivider();
    }

    private void markTaskAsDone(int taskIndex) {
        Task finishedTask = TASK_MANAGER.markTaskAsDone(taskIndex);
        System.out.println(MARK_TASK_AS_DONE_MESSAGE + "\n" + finishedTask.toString());
        printDivider();
    }

    public void execute() {

        String userInputString;
        String[] parsedUserInput;
        String command;
        String[] params;

        greet();
        while (true) {
            userInputString = SCANNER.nextLine();

            if (userInputString.equalsIgnoreCase(COMMAND_EXIT)) {
                exit();
                break;
            }
            if (userInputString.equalsIgnoreCase(COMMAND_LIST_TASKS)) {
                listTasks();
                continue;
            }
            if (userInputString.equalsIgnoreCase(COMMAND_HELP)) {
                showHelpMessage();
                continue;
            }

            parsedUserInput = PARSER.separateCommand(userInputString);
            command = parsedUserInput[0].toLowerCase();

            switch (command) {
            case COMMAND_ECHO:
                echo(parsedUserInput[1]);
                break;
            case COMMAND_MARK_TASK_AS_DONE:
                markTaskAsDone(Integer.parseInt(parsedUserInput[1]));
                break;
            case COMMAND_ADD_TODO:
                addTodo(parsedUserInput[1]);
                break;
            case COMMAND_ADD_DEADLINE:
                params = PARSER.separateDeadline(parsedUserInput[1]);
                addDeadline(params[0], params[1]);
                break;
            case COMMAND_ADD_EVENT:
                params = PARSER.separateEvent(parsedUserInput[1]);
                addEvent(params[0], params[1]);
                break;
            default:
                showInvalidCommandMessage();
                break;
            }
        }
    }
}
