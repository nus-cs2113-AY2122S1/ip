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

    public String[] getLineData() throws DukeException {
        String lineDataString = setUpScanner.nextLine();
        String[] lineData = lineDataString.trim().split(" \\| ");
        if (isIncomplete(lineData)) {
            throw new DukeException();
        }
        return lineData;
    }

    public int getUserInputInt(String userInput, int taskListSize) throws OutOfBoundsException, NumberFormatException, DukeException {
        if (isEmptyDescription(userInput)) {
            throw new DukeException();
        }

        String[] splitStringBySpace = userInput.trim().split("\\s+", 2);
        String userInputIntString = splitStringBySpace[1];
        int userInputInt = Integer.parseInt(userInputIntString);

        if (userInputInt >= taskListSize) {
            throw new OutOfBoundsException();
        }
        return userInputInt;
    }

    public String getUserInput() {
        String userInput = commandScanner.nextLine();
        return userInput;
    }

    public void addTaskExceptionHandler(String userInput, TaskType specificTask) throws DukeException, FormatException {
        if (isEmptyDescription(userInput)) {
            throw new DukeException();
        } else if (isIncorrectFormat(userInput, specificTask)) {
            throw new FormatException();
        }
    }

    public boolean isThreeDaysAway(String deadline) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        LocalDateTime deadlineDateTime = LocalDateTime.parse(deadline, formatter);
        long diff = ChronoUnit.DAYS.between(currentDateTime, deadlineDateTime);
        final int THREE_DAYS = 3;
        boolean isWithinThreeDays = (int) diff <= THREE_DAYS;
        boolean hasNotPassed = (int) diff > 0;
        if (isWithinThreeDays && hasNotPassed) {
            return true;
        } else {
            return false;
        }
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

    public boolean isEmptyDescription(String userInput) {
        String[] trimDescription = userInput.trim().split("\\s+", 2);
        return trimDescription.length < 2;
    }

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
