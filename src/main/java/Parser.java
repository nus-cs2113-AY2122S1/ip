import java.util.Scanner;

/**
 * Has methods that takes in standard input by user and processes it to a format understandable
 * by the program
 */
public class Parser {
    public static final String COMMAND_DONE = "done";
    public static final int TASK_DATE_INDEX = 1;
    private static final int TODO_START_INDEX = 4;
    private static final int EVENT_START_INDEX = 5;
    private static final int DEADLINE_START_INDEX = 8;
    private static final int DONE_NUMBER_INDEX = 4;
    private static final int DELETE_NUMBER_INDEX = 6;
    private static final int FIND_START_INDEX = 4;
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_LIST_SHORT = "l";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_HELP = "help";
    private static final String COMMAND_HELP_SHORT = "h";
    private static final String COMMAND_FIND = "find";
    private static final int TASK_ISDONE_INT = 1;
    private static final int TASK_ISNOT_DONE_INT = 0;
    private static final int TASK_DETAILS_LETTER_INDEX = 0;
    private static final int TASK_DETAILS_DONE_INDEX = 1;
    private static final int TASK_DETAILS_DESCRIPTION_INDEX = 2;
    private static final int TASK_DETAILS_DATE_INDEX = 3;
    private static final Scanner in = new Scanner(System.in);
    private static String userInput;

    /**
     * Reads from standard input, trims it of leading and trailing whitespaces,
     * and sets the string userInput to that value
     */
    private static void setUserInput() {
        userInput = in.nextLine().trim();
    }


    /**
     * sets userInput
     * based on userInput, execute a command
     * loops indefinitely until isExit === true
     */
    public static void parseAndExecuteCommand() {
        boolean isExit = false;
        do {
            setUserInput();
            Ui.printDivider();

            if (userInput.equals(COMMAND_LIST_SHORT) || userInput.equals(COMMAND_LIST)) {
                Duke.taskList.listTasks();

            } else if (userInput.startsWith(COMMAND_TODO)) {
                Duke.taskList.addTaskPlusException(CommandEnum.TODO, userInput);

            } else if (userInput.startsWith(COMMAND_DEADLINE)) {
                Duke.taskList.addTaskPlusException(CommandEnum.DEADLINE, userInput);

            } else if (userInput.startsWith(COMMAND_EVENT)) {
                Duke.taskList.addTaskPlusException(CommandEnum.EVENT, userInput);

            } else if (userInput.startsWith(COMMAND_DONE)) {
                Duke.taskList.doneOrDeleteTaskPlusException(userInput, COMMAND_DONE);

            } else if (userInput.startsWith(COMMAND_DELETE)) {
                Duke.taskList.doneOrDeleteTaskPlusException(userInput, COMMAND_DELETE);

            } else if (userInput.startsWith(COMMAND_FIND)) {
                Duke.taskList.findTasksPlusException(userInput);

            } else if (userInput.equals(COMMAND_BYE)) {
                Ui.printlnTab("Bye. Hope to see you again soon!");
                Ui.printDivider();
                isExit = true;

            } else if (userInput.equals("")) { //empty command
                Ui.printlnTab("Please enter a command.");
                Ui.printDivider();

            } else if (userInput.equals(COMMAND_HELP_SHORT) || userInput.equals(COMMAND_HELP)) { //empty command
                Ui.printlnTab("List of commands:");
                Ui.printlnTab("1. l OR list ");
                Ui.printlnTab("2. todo [TASK DESCRIPTION]");
                Ui.printlnTab("3. deadline [TASK DESCRIPTION] /by [DATE_AND_OR_TIME]");
                Ui.printlnTab("4. event [TASK DESCRIPTION] /at [DATE_AND_OR_TIME]");
                Ui.printlnTab("5. delete [TASK NUMBER]");
                Ui.printlnTab("6. done [TASK NUMBER]");
                Ui.printlnTab("7. find [PART OF TASK DESCRIPTION]");
                Ui.printlnTab("8. h OR help");
                Ui.printlnTab("9. bye");
                Ui.printDivider();

            } else { //Invalid inputs
                Ui.printlnTab("☹ OOPS!!! I don't understand you ");
                Ui.printlnTab("Enter \"help\" or \"h\" for a list of commands");
                Ui.printDivider();
            }

        } while (!isExit);

    }

    /**
     * Remove command keyword from userInput string
     * eg. "todo clean room"  -> "clean room"
     *
     * @param commandType type of command
     * @param userInput   string read from standard input that user entered
     * @return strippedUserInput string without the command word at the start of string
     * @throws BlankDescriptionException if resulting strippedUserInput string is blank
     */
    public static String stripCommandWord(CommandEnum commandType, String userInput) throws BlankDescriptionException {
        String strippedUserInput = "";

        switch (commandType) {
        case TODO:
            strippedUserInput = userInput.substring(TODO_START_INDEX).strip();
            break;
        case DEADLINE:
            strippedUserInput = userInput.substring(DEADLINE_START_INDEX).strip();
            break;
        case EVENT:
            strippedUserInput = userInput.substring(EVENT_START_INDEX).strip();
            break;
        case DONE:
            strippedUserInput = userInput.substring(DONE_NUMBER_INDEX).strip();
            break;
        case DELETE:
            strippedUserInput = userInput.substring(DELETE_NUMBER_INDEX).strip();
            break;
        case FIND:
            strippedUserInput = userInput.substring(FIND_START_INDEX).strip();
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + commandType);
        }

        if (strippedUserInput.isBlank()) {
            throw new BlankDescriptionException();
        }
        return strippedUserInput;
    }

    /**
     * Extracts the task description and date from a stripped userInput string
     *
     * @param taskType                    only Deadline or Event allowed
     * @param userInputWithoutTaskCommand userInput string that has command word removed
     * @return taskDetails array that is { taskDescription, date }
     * @throws IncompleteInformationException if taskDetails array does not have length of 2 or * if any of its elements are empty
     */
    public static String[] getTaskDetails(CommandEnum taskType, String userInputWithoutTaskCommand) throws IncompleteInformationException {
        String[] taskDetails;
        //strip userInputWithoutTaskCommand prevents empty description / dates
        if (taskType == CommandEnum.DEADLINE) {
            taskDetails = userInputWithoutTaskCommand.strip().split("/by");
        } else { //if EVENT
            taskDetails = userInputWithoutTaskCommand.strip().split("/at");
        }

        /*
        taskDetails[] should have length of 2
        containing Task description (index 0) and Task date (index 1)
        special error case of length 2 when "/by timing" which is  invalid
        is checked by .isBlank()
         */
        if (taskDetails.length != 2
                || taskDetails[TaskList.TASK_DESCRIPTION_INDEX].isBlank()
                || taskDetails[TASK_DATE_INDEX].isBlank()) {
            throw new IncompleteInformationException();
        }

        for (int i = 0; i < 2; i++) {
            taskDetails[i] = taskDetails[i].strip();
        }
        return taskDetails;
    }

    /**
     * parses a line of text from DukeData/data.txt, adding tasks if any
     * ignores line if blank line
     *
     * @param line a line of text from DukeData/data.txt
     * @throws InvalidIntegerException if (isDoneInt != Integer.parseInt("1") && isDoneInt != Integer.parseInt("0")
     */
    static void parseStorageData(String line) throws InvalidIntegerException {
        if (line.isBlank()) {
            return;
        }
        String[] taskDetails = line.split(" \\| ");

        String taskLetter = taskDetails[TASK_DETAILS_LETTER_INDEX];
        String isDoneString = taskDetails[TASK_DETAILS_DONE_INDEX];

        int isDoneInt = Integer.parseInt(isDoneString);
        if (isDoneInt != TASK_ISDONE_INT && isDoneInt != TASK_ISNOT_DONE_INT) {
            throw new InvalidIntegerException();
        }
        boolean isDone = (isDoneInt == TASK_ISDONE_INT);

        String description = taskDetails[TASK_DETAILS_DESCRIPTION_INDEX];
        String date;

        switch (taskLetter) {
        case "T":
            Duke.taskList.addTodo(description, isDone);
            break;

        case "D":
            date = taskDetails[TASK_DETAILS_DATE_INDEX];
            String[] deadlineDetails = {description, date};

            Duke.taskList.addDeadline(deadlineDetails, isDone);
            break;

        case "E":
            date = taskDetails[TASK_DETAILS_DATE_INDEX];
            String[] eventDetails = {description, date};
            Duke.taskList.addEvent(eventDetails, isDone);
            break;
        default:
            throw new IllegalStateException();
        }
    }
}
