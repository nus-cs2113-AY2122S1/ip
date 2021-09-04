package kate;

import kate.command.Command;
import kate.exception.EmptyFieldException;
import kate.exception.InvalidCommandException;
import kate.exception.InvalidFieldException;
import kate.task.Deadline;
import kate.task.Event;
import kate.task.Task;
import kate.task.ToDo;

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
    private static final int TODO_LENGTH = 4;
    private static final int DEADLINE_LENGTH = 8;
    private static final int EVENT_LENGTH = 5;

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
            + "Please specify a task with: \n"
            + TEXT_INDENTATION + "\"" + COMMAND_TODO + "\"\n";
    private static final String FAILURE_MESSAGE_ADD_DEADLINE = TEXT_INDENTATION
            + "Please specify a task with: \n"
            + TEXT_INDENTATION + "\"" + COMMAND_DEADLINE + "\"\n";
    private static final String FAILURE_MESSAGE_ADD_EVENT = TEXT_INDENTATION
            + "Please specify a task with: \n"
            + TEXT_INDENTATION + "\"" + COMMAND_EVENT + "\"\n";
    private static final String FAILURE_MESSAGE_SET_DONE = TEXT_INDENTATION
            + "Please specify a task with: \n"
            + TEXT_INDENTATION + "\"" + COMMAND_DONE + "\"\n";
    private static final String FAILURE_MESSAGE_INVALID_COMMAND = TEXT_INDENTATION
            + "Please enter a valid command! \n"
            + TEXT_INDENTATION + "Type <help> for the list of commands\n";

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
        Scanner in = new Scanner(System.in);
        while (true) {
            String userInput = in.nextLine();
            try {
                Command command = extractCommand(userInput);

                switch (command) {
                case TODO:
                    addToDo(userInput);
                    break;
                case DEADLINE:
                    addDeadline(userInput);
                    break;
                case EVENT:
                    addEvent(userInput);
                    break;
                case LIST:
                    printTasks();
                    break;
                case DONE:
                    indicateDone(userInput);
                    break;
                case BYE:
                    return;
                case HELP:
                    printHelpPage();
                    break;
                default:
                    break;
                }
            } catch (InvalidCommandException e) {
                printMessage(FAILURE_MESSAGE_INVALID_COMMAND);
            }
        }
    }

    /**
     * Adds a general task to be done
     *
     * @param userInput A general task that user wants to add
     */
    private static void addToDo(String userInput) {
        try {
            String taskDescription = processToDoInput(userInput);
            tasks.add(new ToDo(taskDescription));
            printAddedTask();
        } catch (EmptyFieldException e) {
            printMessage(FAILURE_MESSAGE_ADD_TODO);
        }
    }

    /**
     * Processes the user input to extract description
     *
     * @param userInput Input provided by user
     * @return Task description for ToDo
     * @throws EmptyFieldException If task description is empty
     */
    private static String processToDoInput(String userInput) throws EmptyFieldException {
        String taskDescription = userInput.substring(TODO_LENGTH).strip();

        if (taskDescription.isEmpty()) {
            throw new EmptyFieldException();
        }
        return taskDescription;
    }

    /**
     * Adds a task that has a deadline
     *
     * @param userInput A task a user wants to add that has a deadline
     */
    private static void addDeadline(String userInput) {
        try {
            String[] infoArr = processDeadlineInput(userInput);
            String taskDescription = infoArr[0];
            String deadline = infoArr[1];

            tasks.add(new Deadline(taskDescription, deadline));
            printAddedTask();
        } catch (EmptyFieldException e) {
            printMessage(FAILURE_MESSAGE_ADD_DEADLINE);
        }
    }

    /**
     * Process the user input to extract description and deadline
     *
     * @param userInput Input provided by user
     * @return String array of description and deadline
     * @throws EmptyFieldException If description or deadline is empty
     */
    private static String[] processDeadlineInput(String userInput) throws EmptyFieldException {
        String taskInfo = userInput.substring(DEADLINE_LENGTH).strip();
        String[] infoArr = taskInfo.split(" /by ", 2);

        try {
            String taskDescription = infoArr[0].strip();
            String deadline = infoArr[1].strip();

            if (taskDescription.isEmpty() || deadline.isEmpty()) {
                throw new EmptyFieldException();
            }

            infoArr[0] = taskDescription;
            infoArr[1] = deadline;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyFieldException();
        }

        return infoArr;
    }

    /**
     * Adds a task that is an event
     *
     * @param userInput An event the user wants to add
     */
    private static void addEvent(String userInput) {
        try {
            String[] infoArr = processEventInput(userInput);
            String taskDescription = infoArr[0];
            String timeFrame = infoArr[1];

            tasks.add(new Event(taskDescription, timeFrame));
            printAddedTask();
        } catch (EmptyFieldException e) {
            printMessage(FAILURE_MESSAGE_ADD_EVENT);
        }
    }

    /**
     * Process the user input to extract description and time frame
     *
     * @param userInput Input provided by user
     * @return String array of description and time frame
     * @throws EmptyFieldException If description or time frame is empty
     */
    private static String[] processEventInput(String userInput) throws EmptyFieldException {
        String taskInfo = userInput.substring(EVENT_LENGTH).strip();
        String[] infoArr = taskInfo.split(" /at ", 2);

        try {
            String taskDescription = infoArr[0].strip();
            String timeframe = infoArr[1].strip();

            if (taskDescription.isEmpty() || timeframe.isEmpty()) {
                throw new EmptyFieldException();
            }

            infoArr[0] = taskDescription;
            infoArr[1] = timeframe;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyFieldException();
        }

        return infoArr;
    }

    /**
     * Marks task as done for a particular task number
     *
     * @param userInput Input provided by user to indicate task completion for a task
     */
    private static void indicateDone(String userInput) {
        try {
            Task curTask = processDoneInput(userInput);
            curTask.setDone();

            String doneMessage = TEXT_INDENTATION + "Nice! I've marked this task as done:\n"
                    + TEXT_INDENTATION + "  " + curTask.printTaskInfo() + "\n";
            printMessage(doneMessage);
        } catch (EmptyFieldException e) {
            printMessage(FAILURE_MESSAGE_SET_DONE);
        } catch (InvalidFieldException e) {
            printMessage(FAILURE_MESSAGE_SET_DONE);
        }
    }

    /**
     * Process user input to extract the object of the associated task number
     *
     * @param userInput Input provided by user
     * @return Task object of the provided task number
     * @throws EmptyFieldException   If task number provided is empty
     * @throws InvalidFieldException If task number provided is invalid
     */
    private static Task processDoneInput(String userInput) throws EmptyFieldException, InvalidFieldException {
        String[] inputArr = userInput.split(" ");

        try {
            int taskNumber = Integer.parseInt(inputArr[1]);

            if ((taskNumber > tasks.size()) || (taskNumber < 1) || inputArr.length != 2) {
                throw new InvalidFieldException();
            }

            int taskNumberIndex = taskNumber - 1;

            return tasks.get(taskNumberIndex);
        } catch (NumberFormatException e) {
            throw new InvalidFieldException();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyFieldException();
        }

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
     * Extracts the command from the user input
     *
     * @param userInput Input provide by user
     * @return Command object of the given valid command
     * @throws InvalidCommandException If command is invalid
     */
    private static Command extractCommand(String userInput) throws InvalidCommandException {
        String[] inputArr = userInput.split(" ");
        String givenCommand = inputArr[0].toUpperCase();
        for (Command command : Command.values()) {
            if (command.name().equals(givenCommand)) {
                return command;
            }
        }
        throw new InvalidCommandException();

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
