package duke.parser;

import duke.TaskType;
import duke.exception.DukeException;
import duke.exception.FormatException;
import duke.exception.OutOfBoundsException;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Parser {

    private static Scanner setUpScanner;
    private static final Scanner commandScanner = new Scanner(System.in);

    public Parser() {
    }

    public void setScanner(String filePath) throws IOException {
        File storedData = new File(filePath);
        setUpScanner = new Scanner(storedData);
    }

    /**
     * Returns fields within a line of data in the form of a String array.
     * Each line of data is obtained as a String, as the Scanner scans the stored data in duke.txt.
     * If there are insufficient fields obtained, exception is thrown to break out of the function.
     *
     * @return Fields within a line of data, separated by separator.
     * @throws DukeException If there are insufficient fields within each line of data (i.e. < 3)
     */
    public String[] getLineData() throws DukeException {
        String lineDataString = setUpScanner.nextLine();
        String[] lineData = lineDataString.trim().split(" \\| ");
        if (isIncomplete(lineData)) {
            throw new DukeException();
        }
        return lineData;
    }

    /**
     * Returns the integer specified by the user after splitting the user input String.
     * If the integer is out-of-bounds, not formatted properly or left empty, an exception is thrown,
     * and the programme breaks out of the function.
     *
     * @param userInput    User's input in the Command Line.
     * @param taskListSize Size of array containing Task objects.
     * @return Integer within user's input for 'done' and 'delete' commands.
     * @throws OutOfBoundsException  If the input number > size of array i.e. task does not exist.
     * @throws NumberFormatException If the number in the form of string cannot be parsed into an Integer.
     * @throws DukeException         If description after the command is left empty.
     */
    public int getUserInputInt(String userInput, int taskListSize) throws OutOfBoundsException, NumberFormatException, DukeException {
        if (isEmptyDescription(userInput)) {
            throw new DukeException();
        }

        String[] splitStringBySpace = userInput.trim().split("\\s+", 2);
        String userInputIntString = splitStringBySpace[1];
        int userInputInt = Integer.parseInt(userInputIntString);

        if (userInputInt > taskListSize) {
            throw new OutOfBoundsException();
        }
        return userInputInt;
    }

    public String getUserInput() {
        String userInput = commandScanner.nextLine();
        return userInput;
    }

    /**
     * Returns the search key in String entered by the user after splitting the user Input String.
     * If the search key is missing, exception is thrown and the programme breaks out of this function.
     *
     * @param userInput User's input in the Command Line.
     * @return Search key entered by user.
     * @throws DukeException If search key after 'find' command is left empty.
     */
    public String getUserSearchKey(String userInput) throws DukeException {
        if (isEmptyDescription(userInput)) {
            throw new DukeException();
        }
        String[] splitStringBySpace = userInput.trim().split("\\s+", 2);
        String userInputSearchKey = splitStringBySpace[1];
        return userInputSearchKey;
    }

    /**
     * Returns a boolean value of whether the task in the task list (esp. the description) contains the search key.
     *
     * @param userSearchKey   User specified key to search within the tasks.
     * @param taskDescription Description of task, a variable within the Task object.
     * @return Boolean of whether task description contains String of user's search key.
     */
    public boolean hasSearchKey(String userSearchKey, String taskDescription) {
        boolean hasSearchKey = taskDescription.contains(userSearchKey);
        return hasSearchKey;
    }

    /**
     * Refactored the throw of exceptions in addTask method following OOP principles.
     *
     * @param userInput    User's input in the Command Line.
     * @param specificTask Enum of specific task type.
     * @throws DukeException   If descriptive fields are missing.
     * @throws FormatException If placeholder is missing.
     */
    public void addTaskExceptionHandler(String userInput, TaskType specificTask) throws DukeException, FormatException {
        if (isEmptyDescription(userInput)) {
            throw new DukeException();
        } else if (isIncorrectFormat(userInput, specificTask)) {
            throw new FormatException();
        }
    }

    /**
     * Returns a boolean value of whether a task's deadline is less than three days away from today's date.
     * A task is due in less than three days if the date has not been passed yet,
     * and the difference in days is less than three.
     *
     * @param deadline Deadline of a task in the form (d/M/yyyy HHmm)
     * @return Boolean of whether a task's deadline is three days away from the today's day (current date time)
     */
    public boolean isThreeDaysAway(String deadline) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        LocalDateTime deadlineDateTime = LocalDateTime.parse(deadline, formatter);
        long diff = ChronoUnit.DAYS.between(currentDateTime, deadlineDateTime);
        final int THREE_DAYS = 3;
        boolean isWithinThreeDays = (int) diff <= THREE_DAYS;
        boolean hasNotPassed = (int) diff > 0;
        return isWithinThreeDays && hasNotPassed;
    }

    public boolean hasNext() {
        return setUpScanner.hasNext();
    }

    public static boolean isIncomplete(String[] lineData) {
        return lineData.length < 3;
    }

    public boolean isDone(String isDoneString) {
        return isDoneString.equals("X");
    }

    /**
     * Returns boolean true if user's input lacks descriptive field, separated by space after the command keyword.
     *
     * @param userInput User's input in the Command Line.
     * @return Boolean for empty description in user's input.
     */
    public boolean isEmptyDescription(String userInput) {
        String[] trimDescription = userInput.trim().split("\\s+", 2);
        return trimDescription.length < 2;
    }

    /**
     * Returns true if user's input lacks '/at' placeholder for an 'event' command,
     * and if user's input lacks '/by' placeholder for a 'deadline' command.
     *
     * @param userInput    User's input in the Command Line.
     * @param specificTask Enum of taskType as a specifier
     * @return Boolean of whether user input string is formatted incorrectly, specifically 'event' and 'deadline'
     */
    public boolean isIncorrectFormat(String userInput, TaskType specificTask) {
        switch (specificTask) {
        case EVENT:
            boolean hasEventPlaceholder = userInput.contains("/at");
            boolean hasNoEventPlaceholder = !hasEventPlaceholder;
            return hasNoEventPlaceholder;

        case DEADLINE:
            boolean hasDeadlinePlaceholder = userInput.contains("/by");
            boolean hasNoDeadlinePlaceholder = !hasDeadlinePlaceholder;
            return hasNoDeadlinePlaceholder;

        default:
            return false;
        }
    }

}
