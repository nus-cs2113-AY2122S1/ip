import java.util.Scanner;

public class Duke {
    private static final String COMMAND_EXIT_WORD = "bye";
    private static final String COMMAND_LIST_WORD = "list";
    private static final String COMMAND_DONE_WORD = "done";
    private static final String COMMAND_TODO_WORD = "todo";
    private static final String COMMAND_DEADLINE_WORD = "deadline";
    private static final String COMMAND_EVENT_WORD = "event";
    private static final int MAX_TASKS_NUMBER = 100;
    private static final Scanner INPUT_COMMAND = new Scanner(System.in);

    public static void main(String[] args) {
        showWelcomeMessage();
        Task[] tasksList = new Task[MAX_TASKS_NUMBER];
        int taskCount = 0;
        executeCommand(tasksList, taskCount);
        exitProgram();
    }

    public static void executeCommand(Task[] tasksList, int taskCount) {
        String inputLine;
        do {
            inputLine = INPUT_COMMAND.nextLine();
            if (inputLine.startsWith(COMMAND_TODO_WORD)) {
                taskCount = addToDo(tasksList, taskCount, inputLine);
            } else if (inputLine.startsWith(COMMAND_DEADLINE_WORD)) {
                taskCount = addDeadline(tasksList, taskCount, inputLine);
            } else if (inputLine.startsWith(COMMAND_EVENT_WORD)) {
                taskCount = addEvent(tasksList, taskCount, inputLine);
            } else if (inputLine.equals(COMMAND_LIST_WORD)) {
                printTasks(tasksList, taskCount);
            } else if (inputLine.startsWith(COMMAND_DONE_WORD)) {
                markAsDone(tasksList, inputLine);
            } else if (inputLine.equals(COMMAND_EXIT_WORD)) {
                continue;
            } else {
                System.out.println("Sorry, command is not recognized. Please input it correctly.");
            }
        } while (!inputLine.equals(COMMAND_EXIT_WORD));
    }

    private static void markAsDone(Task[] tasksList, String inputLine) {
        int taskCompletedIndex = Integer.parseInt(inputLine.split("done ")[1])-1;
        tasksList[taskCompletedIndex].setDone(true);
        System.out.print("  Congratulations! You have completed the task:");
        System.out.println(" " + tasksList[taskCompletedIndex]);
    }

    private static int addEvent(Task[] tasksList, int taskCount, String inputLine) {
        String description = inputLine.split(" /at ")[0];
        String date = inputLine.split(" /at ")[1];
        tasksList[taskCount] = new Event(description, date);
        System.out.println("  Ok, I've added this task:");
        System.out.println("  " + tasksList[taskCount]);
        taskCount++;
        System.out.printf("  Now you have %d tasks in the list. Anything else?\n", taskCount);
        return taskCount;
    }

    public static int addDeadline(Task[] tasksList, int taskCount, String inputLine) {
        String description = inputLine.split(" /by ")[0];
        String date = inputLine.split(" /by ")[1];
        tasksList[taskCount] = new Deadline(description, date);
        System.out.println("  Ok, I've added this task:");
        System.out.println("  " + tasksList[taskCount]);
        taskCount++;
        System.out.printf("  Now you have %d tasks in the list. Anything else?\n", taskCount);
        return taskCount;
    }

    public static int addToDo(Task[] tasksList, int taskCount, String inputLine) {
        tasksList[taskCount] = new ToDo(inputLine);
        System.out.println("  Ok, I've added this task:");
        System.out.println("  " + tasksList[taskCount]);
        taskCount++;
        System.out.printf("  Now you have %d tasks in the list. Anything else?\n", taskCount);
        return taskCount;
    }

    public static void printTasks(Task[] tasksList, int taskCount) {
        for (int i = 1; i < taskCount + 1; i++) {
            System.out.println("  " + i + "." + tasksList[i - 1]);
        }
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
