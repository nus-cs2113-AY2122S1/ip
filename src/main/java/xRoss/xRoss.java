package xRoss;

import xRoss.exception.EmptyStringException;
import xRoss.task.Deadline;
import xRoss.task.Event;
import xRoss.task.Todo;

import java.util.Scanner;

public class xRoss {

    // main method for xRoss chat bot
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

    // prints a divider line and new line to command line output
    private static void printDividerLine() {
        System.out.println(
                "....................................................................................................."
        );
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

    // prints command error messages
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

    // prints response to list command
    private static void printTaskListResponse(TaskManager taskManager) {
        printDividerLine();
        taskManager.printTasks();
        printDividerLine();
    }

    // prints response to done command
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

    // prints response to delete command
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

    // prints response to todo command
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

    // prints response to deadline command
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

    // prints response to event command
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

    // prints response is command is not recognized
    private static void printEcho(String inputLine) {
        printDividerLine();
        System.out.println("\t" + inputLine);
        System.out.println("\tI did not quite understand what you meant there, so I'll just echo your input...\n");
        printDividerLine();
    }
}
