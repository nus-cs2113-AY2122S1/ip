package duke.utilities;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

public class Parser {

    /**
     * Commands that tell the bot the event time or the due date of deadline
     */
    private static final String ENTRY_AT = "/at";
    private static final String ENTRY_BY = "/by";
    private static final String SPACING = " ";
    private static final int COUNT_SPACE = 1;


    private static boolean checkAtEntry(String entry) {
        if (entry.equals(ENTRY_AT) || entry.equals("(at:")) {
            return true;
        }
        return false;
    }

    private static boolean checkByEntry(String entry) {
        if (entry.equals(ENTRY_BY) || entry.equals("(by:")) {
            return true;
        }
        return false;
    }

    /**
     * Scans for the command (keyword)
     *
     * @param input input of user
     * @return the command to be executed
     */
    public static String getCommand(String input) {
        String[] words = input.toLowerCase().split(SPACING);
        return words[0];
    }

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
            System.out.println("Formatting error!");
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
        int startIdx = getStartIdx(input, isSavingInput) + COUNT_SPACE;
        int endIdx = isSavingInput ? input.length() - 1 : input.length();
        String timeOfEvent = input.substring(startIdx, endIdx);
        return timeOfEvent;
    }

    private static int getStartIdx(String input, boolean isSavingInput) throws DukeException {
        String[] words = input.split(SPACING);
        // Accounting for space and semicolon
        int startIdx = isSavingInput ? 1 : 0;
        for (int i = 0; i < words.length; i++) {
            if (i == words.length - 1) {
                throw new DukeException();
            }

            if (checkAtEntry(words[i])) {
                startIdx += ENTRY_AT.length();
                break;
            } else if (checkByEntry(words[i])) {
                startIdx += ENTRY_BY.length();
                break;
            }

            startIdx += words[i].length() + COUNT_SPACE;
        }
        return startIdx;
    }

    /**
     * Scans for the description of the input task
     *
     * @param input input of the user
     * @return description of task
     */
    public static String getDescription(String input, boolean isSavingFile) throws DukeException, StringIndexOutOfBoundsException {
        String[] words = input.split(SPACING);

        boolean isMissingDescription = (words.length <= 1 && !isSavingFile);

        if (isMissingDescription) {
            throw new DukeException();
        }

        int spaceCount = 1;

        // If saving the file, we can start from the start and need not account for the command
        int startIdx = isSavingFile ? 0 : words[0].length() + spaceCount;
        int endIdx = 0;

        for (int i = 0; i < words.length; i++) {

            // true if /by or (by: ...) is detected
            boolean isEndOfDescription = (words[i].charAt(0) == '/' || words[i].charAt(0) == '(');
            if (isEndOfDescription) {
                break;
            }
            endIdx += words[i].length() + spaceCount;
        }
        return input.substring(startIdx, endIdx - spaceCount);
    }

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
