package xRoss.ui;

import xRoss.TaskManager;
import xRoss.exception.EmptyStringException;
import xRoss.task.Deadline;
import xRoss.task.Event;
import xRoss.task.Todo;

import java.time.format.DateTimeParseException;

/**
 * Represents interaction with user
 */
public class Ui {

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
    public static void printWelcomeMessage() {
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
    public static void printExitMessage() {
        printDividerLine();
        System.out.println("\tBye!\n\tHave a nice day and I hope to see you again soon!\n");
        printDividerLine();
    }

    /**
     * Prints error messages for incorrect user commands to system output.
     *
     * @param s Denotes command and error type to print corresponding error message.
     */
    public static void printCommandErrorMessage(String s) {
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
        case "find":
            System.out.println("\tWrong Find command format: "
                    + "String argument cannot be empty\n"
                    + "\tCorrect format as follows:\n"
                    + "\t\tfind <expression to find>\n");
        case "date_format":
            System.out.println("\tWrong Date/Time format: "
                    + "Date/Time argument expected in the form of DD-MM-YYYY HHmm\n");
            break;
        default:
        }
    }

    /**
     * Prints response to "list" command to system output.
     *
     * @param taskManager Representation of current task list.
     */
    public static void printTaskListResponse(TaskManager taskManager) {
        printDividerLine();
        taskManager.printTasks();
        printDividerLine();
    }

    /**
     * Prints response to "done" command to system output.
     *
     * @param taskManager Representation of current task list.
     * @param inputLine   Scanned user input.
     */
    public static void printDoneResponse(TaskManager taskManager, String inputLine) {
        printDividerLine();
        try {
            String[] taskNumberDone = inputLine.split("done ");

            taskManager.markAsDone(Integer.parseInt(taskNumberDone[1]));
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            printCommandErrorMessage("done");
        } finally {
            printDividerLine();
        }
    }

    /**
     * Prints response to "find" command to system output.
     *
     * @param taskManager Representation of current task list.
     * @param inputLine   Scanned user input.
     */
    public void printFindResponse(TaskManager taskManager, String inputLine) {
        printDividerLine();
        try {
            String[] expToFind = inputLine.split("find ");

            taskManager.findTasksWithExp(expToFind[1].trim());
        } catch (IndexOutOfBoundsException | EmptyStringException e) {
            printCommandErrorMessage("find");
        } finally {
            printDividerLine();
        }
    }

    /**
     * Prints response to "delete" command to system output.
     *
     * @param taskManager Representation of current task list.
     * @param inputLine   Scanned user input.
     */
    public static void printDeleteResponse(TaskManager taskManager, String inputLine) {
        printDividerLine();
        try {
            String[] taskNumberDone = inputLine.split("delete ");

            taskManager.deleteTask(Integer.parseInt(taskNumberDone[1]));
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            printCommandErrorMessage("delete");
        } finally {
            printDividerLine();
        }
    }

    /**
     * Prints response to "todo" command to system output.
     *
     * @param taskManager Representation of current task list.
     * @param inputLine   Scanned user input.
     */
    public static void printTodoResponse(TaskManager taskManager, String inputLine) {
        printDividerLine();
        try {
            String[] newTodo = inputLine.split("todo ");

            taskManager.addTask(new Todo(newTodo[1].trim()));
        } catch (IndexOutOfBoundsException e) {
            printCommandErrorMessage("todo_format");
        } catch (EmptyStringException e) {
            printCommandErrorMessage("todo_empty_string");
        } finally {
            printDividerLine();
        }
    }

    /**
     * Prints response to "deadline" command to system output.
     *
     * @param taskManager Representation of current task list.
     * @param inputLine   Scanned user input.
     */
    public static void printDeadlineResponse(TaskManager taskManager, String inputLine) {
        printDividerLine();
        try {
            String[] newDeadline = inputLine.split(" /by ");

            taskManager.addTask(new Deadline(newDeadline[0].substring(9).trim(), newDeadline[1].trim()));
        } catch (IndexOutOfBoundsException e) {
            printCommandErrorMessage("deadline_format");
        } catch (EmptyStringException e) {
            printCommandErrorMessage("deadline_empty_string");
        } catch (DateTimeParseException e) {
            printCommandErrorMessage("date_format");
        } finally {
            printDividerLine();
        }
    }

    /**
     * Prints response to "event" command to system output.
     *
     * @param taskManager Representation of current task list.
     * @param inputLine   Scanned user input.
     */
    public static void printEventResponse(TaskManager taskManager, String inputLine) {
        printDividerLine();
        try {
            String[] newEvent = inputLine.split(" /at ");

            taskManager.addTask(new Event(newEvent[0].substring(6).trim(), newEvent[1].trim()));
        } catch (IndexOutOfBoundsException e) {
            printCommandErrorMessage("event_format");
        } catch (EmptyStringException e) {
            printCommandErrorMessage("event_empty_string");
        } catch(DateTimeParseException e){
            printCommandErrorMessage("date_format");
        } finally {
            printDividerLine();
        }
    }

    /**
     * Prints echo of scanned user input if it is not recognized as a command
     */
    public static void printEcho(String inputLine) {
        printDividerLine();
        System.out.println("\t" + inputLine);
        System.out.println("\tI did not quite understand what you meant there, so I'll just echo your input...\n");
        printDividerLine();
    }
}
