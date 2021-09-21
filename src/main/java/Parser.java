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

    /**
     * Returns lateral location of the specified position.
     *
     * @param words String input by user.
     */
    public static void checkCommand(String[] words, String input) throws
            UnknownCommandException, DukeException, IncorrectTimeFormatException {
        //all the items at the bottom must be factorised
        String[] descriptionInput = parseInput(words, input);
        switch (words[FIRST_ARRAY_PARAMETER]) {
        case COMMAND_BYE:
            Ui.printGoodbyeMessage();
            break;
        case COMMAND_LIST:
            Ui.printList();
            break;
        case COMMAND_DONE:
            int taskNumber;
            checkDescription(words[FIRST_ARRAY_PARAMETER], descriptionInput);
            taskNumber = Integer.parseInt(words[SECOND_ARRAY_PARAMETER]);
            TaskList.checkDoneTask(taskNumber);
            break;
        case COMMAND_DELETE:
            checkDescription(words[FIRST_ARRAY_PARAMETER], descriptionInput);
            taskNumber = Integer.parseInt(words[SECOND_ARRAY_PARAMETER]);
            TaskList.deleteTask(taskNumber);
            break;
        case COMMAND_TODO:
            checkDescription(words[FIRST_ARRAY_PARAMETER], descriptionInput);
            Todo todo = new Todo(descriptionInput[FIRST_ARRAY_PARAMETER]);
            TaskList.addTask(todo);
            break;
        case COMMAND_DEADLINE:
            checkDescription(words[FIRST_ARRAY_PARAMETER], descriptionInput);
            checkTimeframe(descriptionInput);
            Deadline deadline = new Deadline(descriptionInput[FIRST_ARRAY_PARAMETER],
                    parseDeadlineDate(descriptionInput[SECOND_ARRAY_PARAMETER]));
            TaskList.addTask(deadline);
            break;
        case COMMAND_EVENT:
            checkDescription(words[FIRST_ARRAY_PARAMETER], descriptionInput);
            checkTimeframe(descriptionInput);
            Event event = new Event(descriptionInput[FIRST_ARRAY_PARAMETER],
                    parseEventDate(descriptionInput[SECOND_ARRAY_PARAMETER]));
            TaskList.addTask(event);
            break;
        case COMMAND_SORT:
            checkDescription(words[FIRST_ARRAY_PARAMETER], descriptionInput);
            sortRequiredList(descriptionInput[FIRST_ARRAY_PARAMETER]);
            break;
        default:
            throw new UnknownCommandException();
        }
    }

    private static void sortRequiredList(String keyword) throws DukeException {
        if (keyword.equals("time")) {
            Ui.printSortedDateTimedTask();
        } else {
            throw new DukeException("No such option for sort");
        }
    }

    private static void checkDescription(String command, String[] descriptionInput) throws DukeException {

        if (descriptionInput[FIRST_ARRAY_PARAMETER].equals("") ||
                descriptionInput[FIRST_ARRAY_PARAMETER].equals(" ")) {
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

    private static void checkTimeframe(String[] descriptionInput) throws DukeException {
        boolean emptyInput = descriptionInput[SECOND_ARRAY_PARAMETER].equals("");
        boolean singleSpaceInput = descriptionInput[SECOND_ARRAY_PARAMETER].equals(" ");
        if (emptyInput || singleSpaceInput) {
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
            // need to try new outliers
            output = newWord[1].split(" /by ", 2);
        } else if (words[0].equals("event") && newWord[1].contains(" /at ")) {
            output = newWord[1].split(" /at ", 2);
        } else {
            output[0] = newWord[1];
        }
        return output;
    }
    public static LocalDateTime parseDeadlineDate (String parser) throws IncorrectTimeFormatException {
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

    public static LocalDateTime[] parseEventDate (String parser) throws IncorrectTimeFormatException {
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

