import java.util.Scanner;

public class xRoss {

    // prints a divider line and new line to command line output
    private static void printDividerLine() {
        System.out.println("..........................................................................................");
    }

    // prints welcome message
    private static void printWelcomeMessage() {
        String logo = "        _____\n"
                + "       |  __ \\ _  __  __\n"
                + " _  _  |    _// \\|  \\|  \\\n"
                + "\\ \\/ / | |\\ \\| | |\\ \\\\ \\\n"
                + "/_/\\_\\ |_| \\_\\\\_/\\__|\\__|\n";
        System.out.println("Hello from\n" + logo);

        printDividerLine();
        System.out.println("\tHello! I'm xRoss, your personal chat bot assistant!\n\tWhat can I do for you today?\n");
        printDividerLine();
    }

    // prints exit message
    private static void printExitMessage() {
        printDividerLine();
        System.out.println("\tBye!\n\tHave a nice day and I hope to see you again soon!\n");
        printDividerLine();
    }

    private static void printTaskError(String s){
        switch (s) {
        case "todo":
            System.out.println("\tWrong Todo command format.\n"
                    + "\tCorrect format as follows:\n"
                    + "\t\ttodo <name>\n");
            break;
        case "deadline":
            System.out.println("\tWrong Deadline command format.\n"
                    + "\tCorrect format as follows:\n"
                    + "\t\tdeadline <name> /by <due by date/time>\n");
            break;
        case "event":
            System.out.println("\tWrong Event command format.\n"
                    + "\tCorrect format as follows:\n"
                    + "\t\tevent <name> /at <date/time of event>\n");
            break;
        case "done_format":
            System.out.println("\tWrong Mark as Done command format.\n"
                    + "\tCorrect format as follows:\n"
                    + "\t\tdone <valid task number>\n");
            break;
        case "done_alpha":
            System.out.println("\tWrong Mark as Done command argument.(Integer expected)\n"
                    + "\tCorrect format as follows:\n"
                    + "\t\tdone <valid task number>\n");
            break;
        default:
            return;
        }
    }

    public static void main(String[] args) {
        printWelcomeMessage();

        // TaskManager instance to keep track of all tasks
        TaskManager taskManager = new TaskManager();

        // setting up variable and scanner for user input
        String inputLine;
        Scanner in = new Scanner(System.in);

        // boolean value on whether
        boolean continueLoop = true;

        while (continueLoop) {
            inputLine = in.nextLine();
            if (inputLine.equals("bye")) {
                continueLoop = false;
            } else if (inputLine.equals("list")) {
                printTaskListResponse(taskManager);
            } else if (inputLine.startsWith("done")) {
                printDoneResponse(taskManager, inputLine);
            } else if (inputLine.startsWith("todo")) {
                printTodoResponse(taskManager, inputLine);
            } else if (inputLine.startsWith("deadline")) {
                printDeadlineResponse(taskManager, inputLine);
            } else if (inputLine.startsWith("event")) {
                printEventResponse(taskManager, inputLine);
            } else {
                printEcho(inputLine);
            }
        }

        printExitMessage();
    }

    private static void printTaskListResponse(TaskManager taskManager) {
        printDividerLine();
        taskManager.printTasks();
        printDividerLine();
    }

    private static void printDoneResponse(TaskManager taskManager, String inputLine) {
        printDividerLine();
        try{
            taskManager.markAsDone(Integer.parseInt(inputLine.substring(5)));
        } catch (IndexOutOfBoundsException e){
            printTaskError("done_format");
        } catch (NumberFormatException e){
            printTaskError("done_alpha");
        }
        printDividerLine();
    }

    private static void printTodoResponse(TaskManager taskManager, String inputLine) {
        printDividerLine();
        try{
            String[] newTodo = inputLine.split("todo ");

            taskManager.addTask(new Todo(newTodo[1]));
        } catch(IndexOutOfBoundsException e){
            printTaskError("todo");
        }
        printDividerLine();
    }

    private static void printDeadlineResponse(TaskManager taskManager, String inputLine) {
        printDividerLine();
        try {
            String[] newDeadline = inputLine.split(" /by ");

            taskManager.addTask(new Deadline(newDeadline[0].substring(9), newDeadline[1]));
        } catch (IndexOutOfBoundsException e){
            printTaskError("deadline");
        }
        printDividerLine();
    }

    private static void printEventResponse(TaskManager taskManager, String inputLine) {
        printDividerLine();
        try {
            String[] newEvent = inputLine.split(" /at ");

            taskManager.addTask(new Event(newEvent[0].substring(6), newEvent[1]));
        } catch (IndexOutOfBoundsException e){
            printTaskError("event");
        }
        printDividerLine();
    }

    private static void printEcho(String inputLine) {
        printDividerLine();
        System.out.println("\t" + inputLine);
        System.out.println("\tI did not quite understand what you meant there, so I'll just echo your input...\n");
        printDividerLine();
    }
}
