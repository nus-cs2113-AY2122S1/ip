package xRoss;

import xRoss.exception.EmptyStringException;
import xRoss.task.Deadline;
import xRoss.task.Event;
import xRoss.task.Todo;

import java.util.Scanner;

/**
 * Represents implementation of xRoss chat bot.
 */
public class xRoss {

    /**
     * Main function to execute xRoss chat bot.
     *
     * @param args
     */
    public static void main(String[] args) {
        printWelcomeMessage();

        // TaskManager instance to keep track of all tasks
        TaskManager taskManager = new TaskManager();
        taskManager.readFromFile();

        // Add saved tasks to task manager

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
            } else if (inputLine.startsWith("delete")){
                printDeleteResponse(taskManager, inputLine);
            }else if (inputLine.startsWith("todo")) {
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

    /**
     * Prints a divider and new line to system output.
     */
    private static void printDividerLine() {
        System.out.println(
                "....................................................................................................."
        );
    }

    /**
     * Prints welcome message to system output.
     */
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

    /**
     * Prints exit message to system output.
     */
    private static void printExitMessage() {
        printDividerLine();
        System.out.println("\tBye!\n\tHave a nice day and I hope to see you again soon!\n");
        printDividerLine();
    }

    /**
     * Prints error messages for incorrect user commands to system output.
     *
     * @param s Denotes command and error type to print corresponding error message.
     */
    private static void printCommandErrorMessage(String s) {
        switch (s) {
        case "todo_format":
            System.out.println("\tWrong Todo command format: "
                    + "String argument expected for Todo name\n"
                    + "\tCorrect format as follows:\n"
                    + "\t\ttodo <name>\n");
            break;
        case "todo_empty_string":
            System.out.println("\tString argument for Todo name cannot be empty\n"
                    + "\tCorrect format as follows:\n"
                    + "\t\ttodo <name>\n");
            break;
        case "deadline_format":
            System.out.println("\tWrong Deadline command format: "
                    + "String argument expected for Deadline name and date/time\n"
                    + "\tCorrect format as follows:\n"
                    + "\t\tdeadline <name> /by <due by date/time>\n");
            break;
        case "deadline_empty_string":
            System.out.println(
                    "\tString argument for Deadline name and date/time cannot be empty\n"
                    + "\tCorrect format as follows:\n"
                    + "\t\tdeadline <name> /by <due by date/time>\n");
            break;
        case "event_format":
            System.out.println("\tWrong Event command format: "
                    + "String argument expected for Event name and date/time\n"
                    + "\tCorrect format as follows:\n"
                    + "\t\tevent <name> /at <date/time of event>\n");
            break;
        case "event_empty_string":
            System.out.println(
                    "\tString argument for Event name and date/time cannot be empty\n"
                    + "\tCorrect format as follows:\n"
                    + "\t\tevent <name> /at <date/time of event>\n");
            break;
        case "done":
            System.out.println("\tWrong Mark as Done command format: "
                    + "Integer argument expected for task to be marked as done\n"
                    + "\tCorrect format as follows:\n"
                    + "\t\tdone <valid task number>\n");
            break;
        case "delete":
            System.out.println("\tWrong Delete command format: "
                    + "Integer argument expected for task to be deleted\n"
                    + "\tCorrect format as follows:\n"
                    + "\t\tdelete <valid task number>\n");
            break;
        default:
        }
    }

    /**
     * Prints response to "list" command to system output.
     *
     * @param taskManager   Representation of current task list.
     */
    private static void printTaskListResponse(TaskManager taskManager) {
        printDividerLine();
        taskManager.printTasks();
        printDividerLine();
    }

    /**
     * Prints response to "done" command to system output.
     *
     * @param taskManager   Representation of current task list.
     * @param inputLine     Scanned system input.
     */
    private static void printDoneResponse(TaskManager taskManager, String inputLine) {
        printDividerLine();
        try {
            String[] taskNumberDone = inputLine.split("done ");

            taskManager.markAsDone(Integer.parseInt(taskNumberDone[1]));
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            printCommandErrorMessage("done");
        }
        printDividerLine();
    }

    /**
     * Prints response to "delete" command to system output.
     *
     * @param taskManager   Representation of current task list.
     * @param inputLine     Scanned system input.
     */
    private static void printDeleteResponse(TaskManager taskManager, String inputLine){
        printDividerLine();
        try {
            String[] taskNumberDone = inputLine.split("delete ");

            taskManager.deleteTask(Integer.parseInt(taskNumberDone[1]));
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            printCommandErrorMessage("delete");
        }
        printDividerLine();
    }

    /**
     * Prints response to "todo" command to system output.
     *
     * @param taskManager   Representation of current task list.
     * @param inputLine     Scanned system input.
     */
    private static void printTodoResponse(TaskManager taskManager, String inputLine) {
        printDividerLine();
        try {
            String[] newTodo = inputLine.split("todo ");

            taskManager.addTask(new Todo(newTodo[1].trim()));
        } catch (IndexOutOfBoundsException e) {
            printCommandErrorMessage("todo_format");
        } catch (EmptyStringException e) {
            printCommandErrorMessage("todo_empty_string");
        }
        printDividerLine();
    }

    /**
     * Prints response to "deadline" command to system output.
     *
     * @param taskManager   Representation of current task list.
     * @param inputLine     Scanned system input.
     */
    private static void printDeadlineResponse(TaskManager taskManager, String inputLine) {
        printDividerLine();
        try {
            String[] newDeadline = inputLine.split(" /by ");

            taskManager.addTask(new Deadline(newDeadline[0].substring(9).trim(), newDeadline[1].trim()));
        } catch (IndexOutOfBoundsException e) {
            printCommandErrorMessage("deadline_format");
        } catch (EmptyStringException e) {
            printCommandErrorMessage("deadline_empty_string");
        }
        printDividerLine();
    }

    /**
     * Prints response to "event" command to system output.
     *
     * @param taskManager   Representation of current task list.
     * @param inputLine     Scanned system input.
     */
    private static void printEventResponse(TaskManager taskManager, String inputLine) {
        printDividerLine();
        try {
            String[] newEvent = inputLine.split(" /at ");

            taskManager.addTask(new Event(newEvent[0].substring(6).trim(), newEvent[1].trim()));
        } catch (IndexOutOfBoundsException e) {
            printCommandErrorMessage("event_format");
        } catch (EmptyStringException e) {
            printCommandErrorMessage("event_empty_string");
        }
        printDividerLine();
    }

    /**
     * Prints echo of scanned user input if it is not recognized as a command
     */
    private static void printEcho(String inputLine) {
        printDividerLine();
        System.out.println("\t" + inputLine);
        System.out.println("\tI did not quite understand what you meant there, so I'll just echo your input...\n");
        printDividerLine();
    }
}
