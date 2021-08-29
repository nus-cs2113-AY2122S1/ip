import java.util.Scanner;

public class Duke {
    private static final String COMMAND_EXIT_WORD = "bye";
    private static final String COMMAND_LIST_WORD = "list";
    private static final int MAX_TASKS_NUMBER = 100;
    private static final Scanner INPUT_COMMAND = new Scanner(System.in);

    public static void main(String[] args) {
        showWelcomeMessage();
        Task[] tasksList = new Task[MAX_TASKS_NUMBER];
        int taskCount = 0;
        executeCommand(tasksList, taskCount);
    }

    public static void executeCommand(Task[] tasksList, int taskCount) {
        String inputLine;
        do {
            inputLine = INPUT_COMMAND.nextLine();
            switch (inputLine) {
            case COMMAND_LIST_WORD:
                printTasks(tasksList, taskCount);
                break;
            case COMMAND_EXIT_WORD:
                exitProgram();
            default:
                taskCount = addTask(tasksList, taskCount, inputLine);
            }
        } while (!inputLine.equals(COMMAND_EXIT_WORD));
    }

    public static void printTasks(Task[] tasksList, int taskCount) {
        if (taskCount == 0) {
            System.out.println("No task added yet!");
        } else {
            for (int i = 1; i < taskCount + 1; i++) {
                System.out.println("  " + i + ". " + tasksList[i-1].getTaskName());
            }
        }
    }

    public static int addTask(Task[] tasksList, int taskCount, String inputLine) {
        tasksList[taskCount] = new Task(inputLine);
        taskCount++;
        System.out.println("  Task added: " + inputLine);
        System.out.println("  Anything else?");
        return taskCount;
    }

    public static void exitProgram() {
        System.out.println("  Bye! Have a nice day and hope to see you again!");
        System.exit(0);
    }

    public static void showWelcomeMessage() {
        String logo = "▒▒▒▒▒▒▒█▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀█\n"
                + "▒▒▒▒▒▒▒█░▒▒▒▒▒▒▒▓▒▒▓▒▒▒▒▒▒▒░█\n"
                + "▒▒▒▒▒▒▒█░▒▒▓▒▒▒▒▒▒▒▒▒▄▄▒▓▒▒░█░▄▄\n"
                + "▒▒▄▀▀▄▄█░▒▒▒▒▒▒▓▒▒▒▒█░░▀▄▄▄▄▄▀░░█\n"
                + "▒▒█░░░░█░▒▒▒▒▒▒▒▒▒▒▒█░░░░░░░░░░░█\n"
                + "▒▒▒▀▀▄▄█░▒▒▒▒▓▒▒▒▓▒█░░░█▒░░░░█▒░░█\n"
                + "▒▒▒▒▒▒▒█░▒▓▒▒▒▒▓▒▒▒█░░░░░░░▀░░░░░█\n"
                + "▒▒▒▒▒▄▄█░▒▒▒▓▒▒▒▒▒▒▒█░░█▄▄█▄▄█░░█\n"
                + "▒▒▒▒█░░░█▄▄▄▄▄▄▄▄▄▄█░█▄▄▄▄▄▄▄▄▄█\n"
                + "▒▒▒▒█▄▄█░░█▄▄█░░░░░░█▄▄█░░█▄▄█\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("  ____________________________________________________________");
        System.out.println("           This is your chatbot assistant, Neko Duke! :)");
        System.out.println("                What can I do for you today?");
        System.out.println("  ____________________________________________________________\n");
    }
}
