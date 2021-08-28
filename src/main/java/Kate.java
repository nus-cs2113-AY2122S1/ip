import java.util.Scanner;
import java.util.ArrayList;

public class Kate {

    /**
     * Decorative prefixes to provide indentations and wrappers for the output message
     */
    private static final String TEXT_INDENTATION = "    ";
    private static final String LOGO_INDENTATION = "                    ";
    private static final String TEXT_WRAPPER = "================================="
            + "====================================\n";

    /**
     * A custom ASCII art logo for Project Kate
     */
    private static final String LOGO_KATE = LOGO_INDENTATION + " _  __     _         \n"
            + LOGO_INDENTATION + "| |/ /__ _| |_ ___   \n"
            + LOGO_INDENTATION + "| ' </ _` |  _/ -_)  \n"
            + LOGO_INDENTATION + "|_|\\_\\__,_|\\__\\___|  \n";

    private static final String GREET_MESSAGE = TEXT_INDENTATION
            + "This is Kate, your personal assistant ;)\n"
            + TEXT_INDENTATION + "How can I help you?\n";
    private static final String BYE_MESSAGE = TEXT_INDENTATION
            + "Leaving already? Oh well see you again soon!\n";

    /**
     * Length of the action commands inclusive of a space
     */
    private static final int TODO_LENGTH = 5;
    private static final int DEADLINE_LENGTH = 9;
    private static final int EVENT_LENGTH = 6;

    /**
     * Specific description on how to use the action commands
     * User inputs should be in location with square brackets
     */
    private static final String COMMAND_TODO = "todo [description]";
    private static final String COMMAND_DEADLINE = "deadline [description] /by [deadline]";
    private static final String COMMAND_EVENT = "event [description] /at [time frame]";
    private static final String COMMAND_DONE = "done [task number shown in list]";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_BYE = "bye";

    private static final String FAILURE_MESSAGE_ADD_TODO = TEXT_INDENTATION
            + "Please specify a task with \"" + COMMAND_TODO + "\"\n";
    private static final String FAILURE_MESSAGE_ADD_DEADLINE = TEXT_INDENTATION
            + "Please specify a task with \"" + COMMAND_DEADLINE + "\"\n";
    private static final String FAILURE_MESSAGE_ADD_EVENT = TEXT_INDENTATION
            + "Please specify a task with \"" + COMMAND_EVENT + "\"\n";
    private static final String FAILURE_MESSAGE_SET_DONE = TEXT_INDENTATION
            + "Please specify a task with \"" + COMMAND_DONE + "\"\n";

    /**
     * Initialise an array list of tasks
     */
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        printGreetMessage();
        startKate();
        printByeMessage();
    }

    /**
     * Retrieve and process user inputs depending on the commands
     */
    private static void startKate() {
        while (true) {
            Scanner in = new Scanner(System.in);
            String userInput = in.nextLine();

            if (userInput.toUpperCase().equals("BYE")) {
                break;
            } else if (userInput.toUpperCase().startsWith("TODO ")) {
                addToDo(userInput);
            } else if (userInput.toUpperCase().startsWith("DEADLINE ")) {
                addDeadline(userInput);
            } else if (userInput.toUpperCase().startsWith("EVENT ")) {
                addEvent(userInput);
            } else if (userInput.toUpperCase().equals("LIST")) {
                printTasks();
            } else if (userInput.toUpperCase().startsWith("DONE ")) {
                indicateDone(userInput);
            } else {
                printHelpPage();
            }
        }
    }

    /**
     * Marks task as done for a particular task number
     *
     * @param userInput Input provided by user to indicate task completion for a task
     */
    private static void indicateDone(String userInput) {
        String[] inputArr = userInput.split(" ");
        boolean isValid = intChecker(inputArr, tasks.size());

        if (!isValid) {
            return;
        }

        int taskNumber = Integer.parseInt(inputArr[1]);
        int taskNumberIndex = taskNumber - 1;
        Task curTask = tasks.get(taskNumberIndex);
        curTask.setDone();

        String doneMessage = TEXT_INDENTATION + "Nice! I've marked this task as done:\n"
                + TEXT_INDENTATION + "  " + curTask.printTaskInfo() + "\n";
        printMessage(doneMessage);
    }

    /**
     * Adds a task that is an event
     *
     * @param userInput An event the user wants to add
     */
    private static void addEvent(String userInput) {
        String taskInfo = userInput.substring(EVENT_LENGTH).strip();
        boolean isEmptyField = emptyFieldChecker(taskInfo, " /at ");

        if (isEmptyField) {
            printMessage(FAILURE_MESSAGE_ADD_EVENT);
            return;
        }

        String[] infoArr = taskInfo.split(" /at ");
        String taskDescription = infoArr[0].strip();
        String timeFrame = infoArr[1].strip();

        tasks.add(new Event(taskDescription, timeFrame));
        printAddedTask();
    }

    /**
     * Adds a task that has a deadline
     *
     * @param userInput A task a user wants to add that has a deadline
     */
    private static void addDeadline(String userInput) {
        String taskInfo = userInput.substring(DEADLINE_LENGTH).strip();
        boolean isEmptyField = emptyFieldChecker(taskInfo, " /by ");

        if (isEmptyField) {
            printMessage(FAILURE_MESSAGE_ADD_DEADLINE);
            return;
        }

        String[] infoArr = taskInfo.split(" /by ");
        String taskDescription = infoArr[0].strip();
        String deadline = infoArr[1].strip();

        tasks.add(new Deadline(taskDescription, deadline));
        printAddedTask();
    }

    /**
     * Adds a general task to be done
     *
     * @param userInput A general task that user wants to add
     */
    private static void addToDo(String userInput) {
        String taskDescription = userInput.substring(TODO_LENGTH).strip();

        if (taskDescription.isEmpty()) {
            printMessage(FAILURE_MESSAGE_ADD_TODO);
            return;
        }

        tasks.add(new ToDo(taskDescription));
        printAddedTask();
    }

    public static void printGreetMessage() {
        System.out.println(LOGO_KATE);
        printMessage(GREET_MESSAGE);
    }

    public static void printByeMessage() {
        printMessage(BYE_MESSAGE);
    }

    /**
     * Prints a general message that is nicely formatted
     *
     * @param msg A string input to be formatted and printed
     */
    public static void printMessage(String msg) {
        String formattedMsg = "";
        formattedMsg += TEXT_WRAPPER + msg + TEXT_WRAPPER;
        System.out.println(formattedMsg);
    }

    /**
     * Prints a confirmation that a task has been added
     */
    public static void printAddedTask() {
        String formattedMsg = "";
        formattedMsg += TEXT_WRAPPER + TEXT_INDENTATION + "Okay, I have added this task!\n"
                + TEXT_INDENTATION + "  " + getAddedTask().printTaskInfo() + "\n"
                + TEXT_INDENTATION + "You currently have (" + tasks.size()
                + ") tasks in your list :)\n" + TEXT_WRAPPER;
        System.out.println(formattedMsg);
    }

    /**
     * Prints all the tasks entered by the user
     */
    public static void printTasks() {
        String formattedMsg = "";
        formattedMsg += TEXT_WRAPPER + TEXT_INDENTATION + "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); ++i) {
            Task curTask = tasks.get(i);
            int numberedBullets = i + 1;
            formattedMsg += TEXT_INDENTATION + numberedBullets + ". "
                    + curTask.printTaskInfo() + "\n";
        }
        formattedMsg += TEXT_WRAPPER;
        System.out.println(formattedMsg);
    }

    /**
     * Retrieves the task added most recently
     *
     * @return Most recently added Task object
     */
    private static Task getAddedTask() {
        int lastElementIndex = tasks.size() - 1;
        return tasks.get(lastElementIndex);
    }

    /**
     * Checks whether given task number is an integer
     *
     * @param inputArr Input by the user
     * @param size     Size of the ArrayList tasks
     * @return Boolean value of whether input is an integer
     */
    public static boolean intChecker(String[] inputArr, int size) {
        try {
            int taskNumber = Integer.parseInt(inputArr[1]);
            if ((taskNumber > size) || (taskNumber < 1) || inputArr.length != 2) {
                printMessage(TEXT_INDENTATION + "Please input a valid task number!\n");
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            printMessage(TEXT_INDENTATION + "Please input an integer as your task number!\n");
        } catch (ArrayIndexOutOfBoundsException e) {
            printMessage(FAILURE_MESSAGE_SET_DONE);
            return false;
        }
        return false;
    }

    /**
     * Checks whether user input contains empty fields
     *
     * @param userInput Input by the user
     * @param delim     Delimiter used to split the string
     * @return Boolean input whether user provided empty fields
     */
    public static boolean emptyFieldChecker(String userInput, String delim) {
        String[] inputArr = userInput.split(delim, 2);
        try {
            String firstArrayField = inputArr[0];
            String secondArrayField = inputArr[1];
            if (firstArrayField.isEmpty() || secondArrayField.isEmpty()) {
                return true;
            }
            return false;
        } catch (ArrayIndexOutOfBoundsException e) {
            return true;
        }
    }

    /**
     * Prints the list of all available commands
     * Only prints when user key in an invalid command
     */
    public static void printHelpPage() {
        String helpText = TEXT_INDENTATION + "Please enter only the following commands: \n"
                + TEXT_INDENTATION + "- " + COMMAND_TODO + "\n"
                + TEXT_INDENTATION + "- " + COMMAND_DEADLINE + "\n"
                + TEXT_INDENTATION + "- " + COMMAND_EVENT + "\n"
                + TEXT_INDENTATION + "- " + COMMAND_DONE + "\n"
                + TEXT_INDENTATION + "- " + COMMAND_LIST + "\n"
                + TEXT_INDENTATION + "- " + COMMAND_BYE + "\n";
        printMessage(helpText);
    }
}
