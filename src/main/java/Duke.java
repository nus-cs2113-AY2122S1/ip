import java.util.Scanner;

public class Duke {

    private static final String COMMAND_EXIT = "bye";
    private static final String COMMAND_LIST_TASKS = "list";
    private static final String COMMAND_MARK_TASK_AS_DONE = "done";
    private static final String COMMAND_ADD_TODO = "todo";
    private static final String COMMAND_ADD_DEADLINE = "deadline";
    private static final String COMMAND_ADD_EVENT = "event";
    private static final int MAX_COUNT = 100;

    private static final String LOGO =
            " ██████████              █████\n"
                    + "░░███░░░░███            ░░███\n"
                    + " ░███   ░░███ █████ ████ ░███ █████  ██████\n"
                    + " ░███    ░███░░███ ░███  ░███░░███  ███░░███\n"
                    + " ░███    ░███ ░███ ░███  ░██████░  ░███████\n"
                    + " ░███    ███  ░███ ░███  ░███░░███ ░███░░░\n"
                    + " ██████████   ░░████████ ████ █████░░██████\n"
                    + "░░░░░░░░░░     ░░░░░░░░ ░░░░ ░░░░░  ░░░░░░\n";

    private static final String DIVIDER = "____________________________________________________________";
    private static final String GREET_MESSAGE = "Welcome to\n" + LOGO + "What can I do for you?";
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";

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

    //initialise tasks array and itemCount tracker
    static Task[] tasks = new Task[MAX_COUNT];
    static int itemCount = 0;

    private void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < itemCount; i++) {
            System.out.printf("%d.%s", i + 1, tasks[i]);
            System.out.print("\n");
        }
        printDivider();
    }

    private void echoTask(int index) {
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks[index]);
    }

    private void taskCountMessage(int count) {
        if (count == 0) {
            System.out.print("You have not added any tasks");
        } else if (count == 1) {
            System.out.print("Now you have 1 task in the list\n");
        } else {
            System.out.printf("Now you have %d tasks in the list\n", count);
        }
    }

    private void markAsDoneMessage(int index) {
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(tasks[index]);
    }

    private void addTodo(String message) {
        String[] removeActionMessage = message.split(" ", 2);
        tasks[itemCount] = new Todo(removeActionMessage[1]);
        echoTask(itemCount);
        itemCount++;
        taskCountMessage(itemCount);
        printDivider();
    }

    private void addDeadline(String message) {
        String[] removeActionMessage = message.split(" ", 2);
        String[] params = removeActionMessage[1].split("/", 2);
        tasks[itemCount] = new Deadline(params[0], params[1]);
        echoTask(itemCount);
        itemCount++;
        taskCountMessage(itemCount);
        printDivider();
    }

    private void addEvent(String message) {
        String[] removeActionMessage = message.split(" ", 2);
        String[] params = removeActionMessage[1].split("/", 2);
        tasks[itemCount] = new Event(params[0], params[1]);
        echoTask(itemCount);
        itemCount++;
        taskCountMessage(itemCount);
        printDivider();
    }

    private void markTaskAsDone(String message) {
        String[] params = message.split(" ");
        int index = Integer.parseInt(params[1]) - 1;
        tasks[index].setDone();
        markAsDoneMessage(index);
        printDivider();
    }

    private void invalidUserInputMessage() {
        //TODO improve on invalid input message
        System.out.println("wrong command");
        printDivider();
    }

    public void run() {
        greet();
        //initialise variables for scanning
        String userInput;
        Scanner scanner = new Scanner(System.in);

        //while user input != bye, case insensitive
        while (true) {
            userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase(COMMAND_EXIT)) {
                exit();
                break;
            }

            if (userInput.equalsIgnoreCase(COMMAND_LIST_TASKS)) {
                listTasks();
                continue;
            }

            if (userInput.startsWith(COMMAND_ADD_TODO)) {
                addTodo(userInput);
                continue;
            }

            if (userInput.startsWith(COMMAND_ADD_DEADLINE)) {
                addDeadline(userInput);
                continue;
            }
            if (userInput.startsWith(COMMAND_ADD_EVENT)) {
                addEvent(userInput);
                continue;
            }

            if (userInput.startsWith(COMMAND_MARK_TASK_AS_DONE)) {
                markTaskAsDone(userInput);
                continue;
            }
            //if code reach here, then command given is invalid
            invalidUserInputMessage();
        }
    }
}
