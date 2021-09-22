package duke;

import duke.exception.DukeException;
import duke.task.TaskType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;

public class Parser {
    private static final String dateTimeFormat = "dd/MM/yyyy[ HH:mm]";
    private static final String printDateTimeFormat = "MMM-d-yyyy HH:mm";

    public static Command parseUserCommand(String input) {
        String[] separated = input.split(" ", 2);
        String firstWordLowerCase = separated[0].toLowerCase();

        switch (firstWordLowerCase) {
        case "bye":
            return Command.BYE;
        case "list":
            return Command.LIST;
        case "done":
            return Command.DONE;
        case "todo":
            return Command.ADD_TODO;
        case "deadline":
            return Command.ADD_DEADLINE;
        case "event":
            return Command.ADD_EVENT;
        case "delete":
            return Command.DELETE;
        case "find":
            return Command.FIND;
        default:
            return Command.UNKNOWN;
        }
    }

    /**
     * Split the description of a Task from its timing (e.g. deadline) if it has one
     *
     * @param type        The type of task
     * @param description Full string input of the task and its timing
     * @return Returns a string array with index 0 containing the task description and index 1 containing the timing
     */
    public static String[] splitDescriptionFromTiming(TaskType type, String description) throws DukeException {
        String[] separated;
        switch (type) {
        case DEADLINE:
            if (!description.contains("/by")) {
                throw new DukeException(Ui.getHorizontalLine() +
                        "Am I supposed to guess when your deadline is???\n" +
                        "TIP: Use \"/by\" to do so!\n" +
                        Ui.getHorizontalLine());
            }
            separated = description.split("/by +");
            break;
        case EVENT:
            if (!description.contains("/at")) {
                throw new DukeException(Ui.getHorizontalLine() +
                        "Am I supposed to guess when your event is happening???\n" +
                        "TIP: Use \"/at\" to do so!\n" +
                        Ui.getHorizontalLine());
            }
            separated = description.split("/at +");
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + type);
        }
        return separated;
    }

    /**
     * Extracts integers from a given String input and returns them in an array of Ints.
     * Method will extract integers separated by any character.
     * If there are no integers in the input, return -1 at the first element of the array.
     *
     * @param input String for which integers are to be extracted from.
     * @return An array of the integers extracted, returns -1 at the first element.
     */
    public static int[] extractInt(String input) throws DukeException {
        // Replacing every non-digit number with a space " "
        input = input.replaceAll("[^\\d]", " ");
        input = input.trim();

        // Split the integers (if any) using whitespace(s) as the delimiter
        String[] arrayOfStringInts = input.split(" +");
        int[] extractedInts = new int[arrayOfStringInts.length];

        // TODO: Tweak implementation such that all extra '0's at the end are not included in the final return
        // If there are no numbers, throw an exception and alert the user
        if (arrayOfStringInts[0].equals("")) {
            throw new DukeException(Ui.getHorizontalLine() +
                    "Monster... You have tricked me and given me NO VALID TASKS!\n" +
                    Ui.getHorizontalLine());
        } else {
            for (int i = 0; i < arrayOfStringInts.length; i++) {
                extractedInts[i] = Integer.parseInt(arrayOfStringInts[i]);
            }
        }

        return extractedInts;
    }

    public static String extractDescription(String input) throws DukeException {
        String[] splitArray = input.split(" +", 2);
        if (splitArray.length == 1) {
            throw new DukeException(Ui.getHorizontalLine() +
                    "Give me a DESCRIPTION too please???\n" +
                    Ui.getHorizontalLine());
        }
        return splitArray[1].trim();
    }

    // @@author crabnuggets-reused
    // Reused from https://stackoverflow.com/questions/48280447/java-8-datetimeformatter-with-optional-part
    // With minor modifications pertaining to date and time format
    public static LocalDateTime extractDateTime(String input) throws DukeException, DateTimeParseException {
        LocalDateTime dateTime;
        TemporalAccessor temporalAccessor = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateTimeFormat);
            temporalAccessor = formatter.parseBest(input, LocalDateTime::from, LocalDate::from);
            if (temporalAccessor instanceof LocalDateTime) {
                dateTime = (LocalDateTime) temporalAccessor;
            } else {
                throw new DukeException("Time not provided! Setting time as 00:00. Don't like it?\n" +
                        "DEAL WITH IT.");
            }
        } catch (DukeException e) {
            System.out.println(Ui.getHorizontalLine() + e.getMessage());
            dateTime = ((LocalDate) temporalAccessor).atStartOfDay();
        }
        return dateTime;
    }
    // @@author

    public static String stringifyDateTimeForStorage(LocalDateTime input) {
        return input.format(DateTimeFormatter.ofPattern(dateTimeFormat));
    }

    public static String stringifyDateTimeForPrinting(LocalDateTime input) {
        return input.format(DateTimeFormatter.ofPattern(printDateTimeFormat));
    }
}
