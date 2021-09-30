package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Parser {

    private static final String BYE = "bye";
    private static final String HELLO= "hello";
    private static final String HI = "hi";
    private static final String YO = "yo";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final String BY= "/by";
    private static final String AT = "/at";
    private static final String ALL_DAY = "/d";
    private static final String DONE = "done";
    private static final String DELETE = "delete";
    private static final String LIST = "list";
    private static final String HELP = "help";
    private static final String FIND = "find";

    /**
     * Parses input from user to execute the correct and desired commands of the user.
     *
     * @param input User input to terminal.
     */
    public static void parse(Scanner input)  {
        boolean isExit = false;

        do {
            try {
                String firstWord = input.next();
                String remainingWords = input.nextLine();

                String taskType = parseFirstWord(firstWord);
                String taskName = parseTaskName(remainingWords);
                String taskDetails = parseTaskDetails(remainingWords);

                switch (taskType) {
                case BYE:
                    isExit = parseBye();
                    break;
                case HELLO:
                case HI:
                case YO:
                    parseGreetings();
                    break;
                case LIST:
                    parseList();
                    break;
                case TODO:
                    parseTodo(taskName);
                    break;
                case DEADLINE:
                    parseDeadline(taskName, taskDetails);
                    break;
                case EVENT:
                    parseEvent(taskName, taskDetails);
                    break;
                case DONE:
                    parseDone(taskName);
                    break;
                case DELETE:
                    parseDelete(taskName);
                    break;
                case FIND:
                    parseFind(taskName);
                    break;
                case HELP:
                    parseHelp(remainingWords);
                    break;
                default:
                    parseDefault(firstWord, remainingWords);
                    break;
                }
            } catch (DukeException e) {
                Ui.showMissingTextError();
            }
        } while (!isExit);
    }

    /**
     * Extracts out the first word of user input (typically the command word taskType).
     *
     * @param text User input.
     * @return String text in lower case and without whitespaces.
     */
    private static String parseFirstWord(String text) {
        String[] words = text.split(" ");
        return words[0].toLowerCase();
    }

    /**
     * Converts the commands `/by`, `/at`, and `/d` to lower case, in case the user types in capital letters.
     * This is to allow commands to not be case-sensitive for a more flexible use.
     *
     * @param text User input.
     * @return String text with commands in lower case.
     */
    private static String parseSecondaryCommand(String text) {
        int slashIndex1;
        int slashIndex2;
        String secondaryCommand1;
        String secondaryCommand2 = "";
        String textFirstHalf;
        String textSecondHalf;

        if (!text.isEmpty()) {
            text = text.substring(1);
        }

        boolean haveSecondaryCommand =
                text.contains(BY) || text.contains("/BY") || text.contains("/By") || text.contains("/bY") ||
                text.contains(AT) ||text.contains("/AT") ||text.contains("/At") || text.contains("/aT");

        if (haveSecondaryCommand) {
            slashIndex1 = text.indexOf("/", 0);
        } else {
            return text;
        }

        secondaryCommand1 = text.substring(slashIndex1, slashIndex1 + 3).toLowerCase();
        textFirstHalf = text.substring(0, slashIndex1 - 1);
        textSecondHalf = text.substring(slashIndex1 + 4);
        haveSecondaryCommand = textSecondHalf.contains(ALL_DAY) || textSecondHalf.contains("/D");

        if (haveSecondaryCommand) {
            slashIndex2 = text.indexOf("/", slashIndex1 + 1);
            secondaryCommand2 = text.substring(slashIndex2, slashIndex2 + 2).toLowerCase();
            textSecondHalf = text.substring(slashIndex1 + 4, slashIndex2 - 1);
        }
        text = textFirstHalf + secondaryCommand1 + textSecondHalf + secondaryCommand2;
        return text;
    }

    /**
     * Extracts out the description of task that user wants to add.
     *
     * @param text User input.
     * @return String description of task.
     */
    protected static String parseTaskName(String text) {
        text = parseSecondaryCommand(text);

        if (text.contains(BY)) {
            String[] words = text.split(BY);
            text = words[0];
        } else if (text.contains(AT)) {
            String[] words = text.split(AT);
            text = words[0];
        }
        return text;
    }

    /**
     * Extracts out the time details fo task that user wants to add.
     *
     * @param text User input.
     * @return String details of task.
     */
    private static String parseTaskDetails(String text) throws DukeException {
        text = parseSecondaryCommand(text);

        if (text.contains(BY)) {
            String[] words = text.split(BY);
            if (words.length != 2) {
                throw new DukeException("Missing task details.");
            }
            text = words[1];

        } else if (text.contains(AT)) {
            String[] words = text.split(AT);
            if (words.length != 2) {
                throw new DukeException("Missing task details.");
            }
            text = words[1];
        }
        return text;
    }

    /**
     * Formats date from user into a more reader-friendly format.
     * Accounts for both with time and without time.
     *
     * @param text Date in type String.
     * @return Formatted date in type String.
     */
    protected static String parseDate(String text) throws DukeException {
        if (text.isBlank()) {
            throw new DukeException("Missing date.");
        }

        if (text.contains(ALL_DAY)) {
            LocalDate localDate = LocalDate.parse("2019-01-01");
            String date = localDate.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
            return date;
        } else {
            LocalDateTime localDateTime = LocalDateTime.parse(text);
            String date = localDateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy HH:mm"));
            return date;
        }
    }

    /**
     * Initiates saving of tasks to duke.txt and causes exit of do-while loop.
     *
     * @return True to be assigned to isExit.
     */
    protected static boolean parseBye() {
        Storage.saveTaskToFile(TaskList.tasks);
        return true;
    }

    /**
     * Prints mockery to user for being stupid.
     */
    private static void parseGreetings() {
        Ui.mockUser();
    }

    /**
     * Prints list of all tasks.
     */
    private static void parseList() {
        Ui.showList(TaskList.tasks);
    }

    /**
     * Adds a task of type Todo to ArrayList tasks.
     * @param taskName Description of task.
     */
    protected static void parseTodo(String taskName) {
        TaskList.addTask("T", taskName, "");
    }

    /**
     * Adds a task of type Deadline to ArrayList tasks.
     *
     * @param taskName Description of task.
     * @param taskDetails Time of deadline in String format.
     */
    private static void parseDeadline(String taskName, String taskDetails) {
        TaskList.addTask("D", taskName, taskDetails);
    }

    /**
     * Adds a task of type Event to Arraylist tasks.
     *
     * @param taskName Description of task.
     * @param taskDetails Time of event in String format.
     */
    private static void parseEvent(String taskName, String taskDetails) {

        TaskList.addTask("E", taskName, taskDetails);
    }

    /**
     * Marks a task in ArrayList tasks as done, only if they exist or has not been completed.
     *
     * @param integer An integer in String format.
     */
    private static void parseDone(String integer) {
        TaskList.doneTask(integer);
    }

    /**
     * Deletes a task from ArrayList tasks, only if they exist or has not been completed.
     *
     * @param integer An integer in String format.
     */
    private static void parseDelete(String integer) {
        TaskList.deleteTask(integer);
    }

    /**
     * Searches the description of all tasks with a String query from user.
     * Prints the tasks that contains the query (as a substring).
     *
     * @param userInput Query of user.
     */
    private static void parseFind(String userInput) {
        try {
            TaskList.findTask(userInput);
        } catch (DukeException E) {
            Ui.showMissingTextError();
        }
    }

    /**
     * Prints all commands that can be used.
     *
     * @param extraText Additional text after `help` command to be printed for fun.
     */
    private static void parseHelp(String extraText) {
        Ui.showHelpMessage(extraText);
    }

    /**
     * Prints error message to user. Prompts user to input correct command.
     *
     * @param firstWord First word in the sentence user typed.
     * @param remainingWords Remaining words in the sentence user typed, if any.
     */
    protected static void parseDefault(String firstWord, String remainingWords) {
        Ui.showWrongTaskTypeError(firstWord + remainingWords);
    }
}
