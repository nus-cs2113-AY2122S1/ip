import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Parser {

    private static final int DESCRIPTION_PARAMETERS = 2;
    private static final int FIRST_ARRAY_PARAMETER = 0;
    private static final int SECOND_ARRAY_PARAMETER = 1;
    private static final int START_DATE = 0;
    private static final int END_DATE = 1;
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_DONE = "done";
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_SORT = "sort";
    public static final String COMMAND_FIND = "find";
    public static final String COMMAND_HELP = "help";

    /**
     * Execute commands based on the command word given
     *
     * @param words String input by user.
     */
    public static void checkCommand(String[] words, String input) throws
            UnknownCommandException, DukeException, IncorrectTimeFormatException {
        String[] descriptionInput = parseInput(words, input);
        switch (words[FIRST_ARRAY_PARAMETER]) {
        case COMMAND_BYE:
            executeCommandBye();
        case COMMAND_LIST:
            executeCommandList();
            break;
        case COMMAND_DONE:
            executeCommandDone(words, descriptionInput);
            break;
        case COMMAND_DELETE:
            executeCommandDelete(words, descriptionInput);
            break;
        case COMMAND_TODO:
            executeCommandTodo(words[FIRST_ARRAY_PARAMETER], descriptionInput);
            break;
        case COMMAND_DEADLINE:
            executeCommandDeadline(words[FIRST_ARRAY_PARAMETER], descriptionInput);
            break;
        case COMMAND_EVENT:
            executeCommandEvent(words[FIRST_ARRAY_PARAMETER], descriptionInput);
            break;
        case COMMAND_SORT:
            executeCommandSort(words[FIRST_ARRAY_PARAMETER], descriptionInput);
            break;
        case COMMAND_FIND:
            executeCommandFind(words[FIRST_ARRAY_PARAMETER], descriptionInput);
            break;
        case COMMAND_HELP:
            Ui.printHelp();
            break;
        default:
            throw new UnknownCommandException();
        }
    }

    private static void executeCommandFind(String word, String[] descriptionInput)
            throws DukeException {
        checkDescription(word, descriptionInput);
        Ui.printFilteredDateTimedTask(descriptionInput[FIRST_ARRAY_PARAMETER]);
    }

    private static void executeCommandSort(String word, String[] descriptionInput)
            throws DukeException {
        checkDescription(word, descriptionInput);
        sortRequiredList(descriptionInput[FIRST_ARRAY_PARAMETER]);
    }

    private static void executeCommandEvent(String word, String[] descriptionInput)
            throws DukeException, IncorrectTimeFormatException {
        checkDescription(word, descriptionInput);
        checkTimeframe(descriptionInput);
        Event event = new Event(descriptionInput[FIRST_ARRAY_PARAMETER],
                parseEventDate(descriptionInput[SECOND_ARRAY_PARAMETER]));
        TaskList.addTask(event);
    }

    private static void executeCommandDeadline(String word, String[] descriptionInput)
            throws DukeException, IncorrectTimeFormatException {
        checkDescription(word, descriptionInput);
        checkTimeframe(descriptionInput);
        Deadline deadline = new Deadline(descriptionInput[FIRST_ARRAY_PARAMETER],
                parseDeadlineDate(descriptionInput[SECOND_ARRAY_PARAMETER]));
        TaskList.addTask(deadline);
    }

    private static void executeCommandTodo(String word, String[] descriptionInput)
            throws DukeException {
        checkDescription(word, descriptionInput);
        Todo todo = new Todo(descriptionInput[FIRST_ARRAY_PARAMETER]);
        TaskList.addTask(todo);
    }

    private static void executeCommandDelete(String[] words, String[] descriptionInput)
            throws DukeException {
        int taskNumber;
        checkDescription(words[FIRST_ARRAY_PARAMETER], descriptionInput);
        taskNumber = Integer.parseInt(words[SECOND_ARRAY_PARAMETER]);
        TaskList.deleteTask(taskNumber);
    }

    private static void executeCommandDone(String[] words, String[] descriptionInput)
            throws DukeException {
        checkDescription(words[FIRST_ARRAY_PARAMETER], descriptionInput);
        int taskNumber = Integer.parseInt(words[SECOND_ARRAY_PARAMETER]);
        TaskList.checkDoneTask(taskNumber);
    }

    private static void executeCommandList() {
        Ui.printList();
    }

    private static void executeCommandBye() {
        Ui.printGoodbyeMessage();
    }

    /**
     * Check if the keyword given by the user is correct
     * Format: sort 'keyword'
     * e.g. sort time
     * @param keyword
     * @throws DukeException when the keyword is incompatible
     */
    private static void sortRequiredList(String keyword) throws DukeException {
        if (keyword.equals("time")) {
            Ui.printSortedDateTimedTask();
        } else {
            throw new DukeException("No such option for sort");
        }
    }

    /**
     * Throws different exception based on the command type
     * @param command is the first word of user input
     * @param descriptionInput is the string that followed after the command
     * @throws DukeException if the subsequent words after the command is empty
     */
    private static void checkDescription(String command, String[] descriptionInput) throws DukeException {
        if (descriptionInput[FIRST_ARRAY_PARAMETER].equals("")) {
            switch (command) {
            case COMMAND_TODO:
                throw new DukeException(ErrorMessage.EXCEPTION_MISSING_DESCRIPTION_TODO);
            case COMMAND_DEADLINE:
                throw new DukeException(ErrorMessage.EXCEPTION_MISSING_DESCRIPTION_DEADLINE);
            case COMMAND_EVENT:
                throw new DukeException(ErrorMessage.EXCEPTION_MISSING_DESCRIPTION_EVENT);
            default:
                throw new DukeException(ErrorMessage.EXCEPTION_MESSAGE_MISSING_PARAMETERS_AFTER_COMMAND);
            }
        }
    }

    /**
     * Throws input if necessary commands requires additional input
     * @param descriptionInput, line of text after the command word
     * @throws DukeException
     */
    private static void checkTimeframe(String[] descriptionInput) throws DukeException {
        boolean emptyInput = descriptionInput[SECOND_ARRAY_PARAMETER].equals("");
        if (emptyInput) {
            throw new DukeException(ErrorMessage.EXCEPTION_MESSAGE_MISSING_PARAMETERS_AFTER_KEYWORD);
        }
    }

    private static String[] parseInput(String[] words, String input) {
        String[] output = new String[DESCRIPTION_PARAMETERS];
        output[0] = "";
        output[1] = "";
        if (words.length < 2) {
            return output;
        }
        String[] newWord = input.split(" ", 2);
        if (words[0].equals("deadline") && newWord[1].contains("/by")) {
            output = newWord[1].split(" /by ", 2);
        } else if (words[0].equals("event") && newWord[1].contains(" /at ")) {
            output = newWord[1].split(" /at ", 2);
        } else {
            output[0] = newWord[1];
        }
        return output;
    }

    /**
     * Change datetime string into datetime object for deadline
     * if parser is YYYY-MM-DD, time will automatically be 0000
     * else if parser YYYY-MM-DD HHMM, time will follow based on user input
     * @param parser is the datetime part of input
     * @return datetime object
     * @throws IncorrectTimeFormatException if input format of time is incorrect or invalid numbers for time
     */
    private static LocalDateTime parseDeadlineDate (String parser) throws IncorrectTimeFormatException {
        String[] stringDate = parser.split(" ");
        LocalDateTime date;
        DateTimeFormatter formatter;
        if (stringDate.length == 1) {
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            date = LocalDateTime.parse(parser + " 0000", formatter);
        } else if (stringDate.length == 2) {
            // store both date and time
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            date = LocalDateTime.parse(parser, formatter);
        } else {
            //this will need to throw an exception
            throw new IncorrectTimeFormatException();
        }
        return date;
    }
    /**
     * Change datetime string into datetime object for event
     * if parser is YYYY-MM-DD to YYYY-MM-DD, time will be set to 0000
     * if parser is YYYY-MM-DD HHMM to HHMM, the second time will follow the date given,
     * assuming that the event start and end on the same day
     * if parser is YYYY-MM-DD to YYYY-MM-DD HHMM, the time will be based on user input
     * @param parser is the datetime part of input
     * @return datetime object
     * @throws IncorrectTimeFormatException if input format of time is incorrect
     */
    private static LocalDateTime[] parseEventDate (String parser) throws IncorrectTimeFormatException {
        String[] stringDates = parser.split(" ");
        LocalDateTime[] dates = new LocalDateTime[2];
        DateTimeFormatter formatter;
        if (stringDates.length == 3 && stringDates[1].equals("to")) {
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            String startDate = stringDates[0] + " 0000";
            String endDate = stringDates[2] + " 0000";
            dates[START_DATE] = LocalDateTime.parse(startDate, formatter);
            dates[END_DATE] = LocalDateTime.parse(endDate, formatter);
        } else if (stringDates.length == 4 && stringDates[2].equals("to")) {
            // store both date and time
            String startDate = stringDates[0] + ' ' + stringDates[1];
            String endDate = stringDates[0] + ' ' + stringDates[3];
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            dates[START_DATE] = LocalDateTime.parse(startDate, formatter);
            dates[END_DATE] = LocalDateTime.parse(endDate, formatter);
        }else if (stringDates.length == 5 && stringDates[2].equals("to")) {
            // store both date and time
            String startDate = stringDates[0] + ' ' + stringDates[1];
            String endDate = stringDates[3] + ' ' + stringDates[4];
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            dates[START_DATE] = LocalDateTime.parse(startDate, formatter);
            dates[END_DATE] = LocalDateTime.parse(endDate, formatter);
        } else {
            throw new IncorrectTimeFormatException();
        }
        return dates;
    }

}

