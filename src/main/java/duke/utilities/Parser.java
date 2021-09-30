package duke.utilities;

import duke.Duke;
import duke.commands.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Makes sense of the user inputs and returns the required commands for subsequent executions
 */
public class Parser {

    /**
     * Commands that tell the bot the event time or the due date of deadline
     */
    private static final String ENTRY_AT = "/at";
    private static final String ENTRY_BY = "/by";
    private static final String SPACING = " ";
    private static final String ERROR_FORMAT_INVALID = "Command format is invalid. Please try again.";
    private static final String ERROR_MISSING_DESCRIPTION = "Description is missing from the task!"
            + "\nPlease try deadline <description> /by <time> or event <description> /at <time>";
    private static final String ERROR_MISSING_TIME = "Time is missing from the task!"
            + "\nPlease try deadline <description> /by <time> or event <description> /at <time>";
    private static final String ERROR_GETTING_TASK = "There was an error in your input! Please try again";
    private static final String SAVE_AT = "(at:";
    private static final String SAVE_BY = "(by:";
    private static final int COUNT_SPACE = 1;


    private static boolean checkAtEntry(String entry) {
        if (entry.equals(ENTRY_AT) || entry.equals(SAVE_AT)) {
            return true;
        }
        return false;
    }

    private static boolean checkByEntry(String entry) {
        if (entry.equals(ENTRY_BY) || entry.equals(SAVE_BY)) {
            return true;
        }
        return false;
    }

    /**
     * Scans for the command
     *
     * @param input input of user
     * @return the command to be executed
     * @throws DukeException when the format is invalid
     */
    public static String scanCommand(String input) throws DukeException {
        String[] words = input.toLowerCase().split(SPACING);
        String command = words[0];

        boolean isInvalid = words.length <= 1
                && !command.equals(ByeCommand.COMMAND_WORD)
                && !command.equals(ListCommand.COMMAND_WORD)
                && !command.equals(HelpCommand.COMMAND_WORD);

        //Guard for invalid command format
        if (isInvalid) {
            throw new DukeException(ERROR_FORMAT_INVALID);
        }

        return command;
    }

    /**
     * Retrieves the command to execute for a given input by the user
     *
     * @param input Input of user
     * @return The Command class that executes its respective function
     */
    public static Command getCommand(String input) {

        try {
            String commandWord = scanCommand(input);

            switch (commandWord) {
            case ByeCommand.COMMAND_WORD:
                return new ByeCommand();
            case DeadlineCommand.COMMAND_WORD:
                return new DeadlineCommand();
            case DeleteCommand.COMMAND_WORD:
                return new DeleteCommand();
            case DoneCommand.COMMAND_WORD:
                return new DoneCommand();
            case EventCommand.COMMAND_WORD:
                return new EventCommand();
            case FindCommand.COMMAND_WORD:
                return new FindCommand();
            case ListCommand.COMMAND_WORD:
                return new ListCommand();
            case ToDoCommand.COMMAND_WORD:
                return new ToDoCommand();
            case HelpCommand.COMMAND_WORD:
            default:
                return new HelpCommand();
            }
        } catch (DukeException dukeE) {
            System.out.println(dukeE.getMessage());;
        }

        //defaults to help
        return new HelpCommand();
    }

    /**
     * Converts the type of task into the one defined by the user
     *
     * @param input Input by user
     * @param type Type to be converted to
     * @return Task of the correct type for storing
     * @throws DukeException If the given input has an invalid format
     */
    public static Task getTaskType(String input, String type) throws DukeException {
        Task temp = new Task("");
        switch (type) {
        case "T":
            temp = new ToDo(getDescription(input, false));
            break;
        case "E":
            temp = new Event(getDescription(input, false),
                    getTimeOfEvent(input, false));
            break;
        case "D":
            temp = new Deadline(getDescription(input, false),
                    getTimeOfEvent(input, false));
            break;
        default:
            System.out.println(ERROR_GETTING_TASK);
            break;
        }
        return temp;
    }

    /**
     * Scans for the event time/due date of deadline of task
     *
     * @param input input of user
     * @return either the due date of deadline or event time
     */
    public static String getTimeOfEvent(String input, boolean isSavingInput) throws DukeException {
        int startIdx = getTimeStartIdx(input, isSavingInput) + COUNT_SPACE;
        int endIdx = isSavingInput ? input.length() - 1 : input.length();

        if (startIdx >= endIdx) {
            throw new DukeException(ERROR_MISSING_TIME);
        }

        String timeOfEvent = input.substring(startIdx, endIdx);
        return timeOfEvent;
    }

    private static int getTimeStartIdx(String input, boolean isSavingInput) {
        String[] words = input.split(SPACING);
        // Accounting for space and semicolon
        int startIdx = isSavingInput ? 1 : 0;

        for (String word : words) {
            if (checkAtEntry(word) || checkByEntry(word)) {
                startIdx += ENTRY_AT.length();
                break;
            }
            startIdx += word.length() + COUNT_SPACE;
        }

        return startIdx;
    }

    /**
     * Scans for the description of the input task
     *
     * @param input input of the user
     * @return description of task
     */
    public static String getDescription(String input, boolean isSavingFile) throws DukeException {
        String[] words = input.split(SPACING);

        int spaceCount = 1;

        // If saving the file, we can start from the start and need not account for the command
        int startIdx = isSavingFile ? 0 : words[0].length() + spaceCount;
        int endIdx = getDescEndIdx(words, spaceCount);

        if (startIdx >= endIdx ) {
            throw new DukeException(ERROR_MISSING_DESCRIPTION);
        }

        return input.substring(startIdx, endIdx);
    }

    private static int getDescEndIdx(String[] words, int spaceCount) {
        int endIdx = 0;
        for (String word : words) {
            boolean isEndOfDescription = (checkAtEntry(word) || checkByEntry(word));
            if (isEndOfDescription) {
                break;
            }
            endIdx += word.length() + spaceCount;
        }
        return endIdx - spaceCount;
    }

    /**
     * Parses the task into a string for storing
     *
     * @param task The task to be stored
     * @return The string that is stored in data
     * @throws DukeException Unlikely but in case there is an error in the given Task
     */
    public static String parseLineForSaving(Task task) throws DukeException {
        String timeOfEvent = new String();
        String command = new String();

        String markDone = (task.isDone()) ? "1 " : "0 ";
        String description = getDescription(task.toString().substring(8), true);

        if (task instanceof ToDo) {
            command = "todo ";
        } else if (task instanceof Event) {
            command = "event ";
            timeOfEvent = " /at " + getTimeOfEvent(task.toString().substring(8), true);
        } else if (task instanceof Deadline) {
            command = "deadline ";
            timeOfEvent = " /by " + getTimeOfEvent(task.toString().substring(8), true);
        }

        return markDone + command + description + timeOfEvent + System.lineSeparator();
    }

}
