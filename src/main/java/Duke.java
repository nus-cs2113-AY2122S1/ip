import java.util.Scanner;

public class Duke {

    private static final String COMMAND_EXIT = "bye";
    private static final String COMMAND_LIST_TASKS = "list";
    private static final String COMMAND_MARK_TASK_AS_DONE = "done";
    private static final String COMMAND_ADD_TODO = "todo";
    private static final String COMMAND_ADD_DEADLINE = "deadline";
    private static final String COMMAND_ADD_EVENT = "event";

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
            + "Enter a task to add it to your list!";
    private static final String EXIT_MESSAGE = "Bye! Have a great day ahead and see you again soon. \uD83D\uDE04";
    private static final String INVALID_COMMAND_MESSAGE = "Sorry... I did not understand that, can you try again? >.<";
    private static final String LIST_TASKS_MESSAGE = "Wow! I found these tasks in your list:";
    private static final String ADD_TASK_MESSAGE = "Yay! I have added the following task for you: ";
    private static final String MARK_TASK_AS_DONE_MESSAGE = "Good job! You have finished the following:";
    private static final String DIVIDER = "____________________________________________________________";

    private static final TaskManager TASK_MANAGER = new TaskManager();
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final InputParser PARSER = new InputParser();

    private void printDivider() {
        System.out.println(DIVIDER);
    }

    private void greet() {
        System.out.println(GREET_MESSAGE);
        printDivider();
    }

    private void exit() {
        System.out.println(EXIT_MESSAGE);
        printDivider();
    }

    private void echo(String command) {
        System.out.println(command);
        printDivider();
    }

    private void showInvalidCommandMessage() {
        System.out.println(INVALID_COMMAND_MESSAGE);
        printDivider();
    }

    private void addTodo(String taskDescription) {
        TASK_MANAGER.addTodo(taskDescription);
        System.out.println(ADD_TASK_MESSAGE + taskDescription);
        printDivider();
    }

    private void addDeadline(String taskDescription, String deadline) {
        TASK_MANAGER.addDeadline(taskDescription, deadline);
        System.out.println(ADD_TASK_MESSAGE + taskDescription);
        printDivider();
    }

    private void addEvent(String taskDescription, String dateAndTime) {
        TASK_MANAGER.addEvent(taskDescription, dateAndTime);
        System.out.println(ADD_TASK_MESSAGE + taskDescription);
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
        String[] userInputComponents;
        String command;
        String taskDescription;
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

            userInputComponents = PARSER.separateCommand(userInputString);
            command = userInputComponents[0].toLowerCase();

            switch (command) {
            case COMMAND_MARK_TASK_AS_DONE:
                taskDescription = userInputComponents[1];
                markTaskAsDone(Integer.parseInt(taskDescription));
                break;
            case COMMAND_ADD_TODO:
                taskDescription = userInputComponents[1];
                addTodo(taskDescription);
                break;
            case COMMAND_ADD_DEADLINE:
                taskDescription = userInputComponents[1];
                params = PARSER.separateDeadline(taskDescription);
                addDeadline(params[0], params[1]);
                break;
            case COMMAND_ADD_EVENT:
                taskDescription = userInputComponents[1];
                params = PARSER.separateEvent(taskDescription);
                addEvent(params[0], params[1]);
                break;
            default:
                showInvalidCommandMessage();
                break;
            }
        }
    }
}
