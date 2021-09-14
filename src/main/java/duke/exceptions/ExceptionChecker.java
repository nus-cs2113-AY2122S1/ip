package duke.exceptions;

import duke.task.TaskManager;
import duke.util.InputParser;
import duke.util.FileManager;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ExceptionChecker {

    private static final InputParser PARSER = new InputParser();
    private static final FileManager FILE_MANAGER = new FileManager();

    private static final String ILLEGAL_CHAR = "|";
    private static final String ILLEGAL_CHAR_ERROR =
            "Please do not use \"|\" in your input...";
    private static final String ECHO_ERROR =
            "OH NO! I can't echo if you don't say anything...";
    private static final String TODO_ERROR =
            "OH NO! You need to provide a description for your todo...";
    private static final String DEADLINE_DESCRIPTION_ERROR =
            "OH NO! You need to provide a description for your deadline task...";
    private static final String DEADLINE_DATE_ERROR =
            "OH NO! You need to specify a due date for your deadline task...";
    private static final String EVENT_DESCRIPTION_ERROR =
            "OH NO! You need to provide a description for your event...";
    private static final String EVENT_DATE_ERROR =
            "OH NO! You need to specify a date and time for your event...";
    private static final String INVALID_COMMAND_ERROR =
            "Sorry... I did not understand that, can you try again?\n"
                    + "Or you can enter \"help\" to see what I can do for you!";
    private static final String MISSING_NUMBER_ERROR =
            "OH NO! You need to specify the task number...\n"
                    + "Enter \"list\" to check the task number!";
    private static final String NUMBER_FORMAT_ERROR = "OH NO! That wasn't a number...";
    private static final String NUMBER_NOT_FOUND_ERROR =
            "OH NO! The task number is invalid, I can't find any tasks matching that number...\n"
                    + "Enter \"list\" to check the task number!";
    public static final String FILE_NOT_FOUND_ERROR =
            "I can't seem to find any file containing your past tasks, I'll create a new file for you!";
    public static final String CORRUPTED_DATA_ERROR =
            "OH NO! Your data is corrupted, starting a new file for you...";

    private boolean hasNullParameter(String[] inputArray) {
        return (inputArray.length < 2);
    }

    private boolean hasCorruptedData(String[] dataArray) {
        switch (dataArray[0]) {
        case "T":
            return (dataArray.length < 3);
        case "D":
            // Fallthrough
        case "E":
            return (dataArray.length < 4);
        default:
            return true;
        }
    }

    public void checkForIllegalChar(String input) throws DukeException {
        if (input.contains(ILLEGAL_CHAR)) {
            throw new DukeException(ILLEGAL_CHAR_ERROR);
        }
    }

    public String retrieveEchoParameter(String[] inputArray) throws DukeException {
        if (hasNullParameter(inputArray)) {
            throw new DukeException(ECHO_ERROR);
        }
        return inputArray[1];
    }

    public int retrieveNumberParameter(String[] inputArray) throws DukeException {
        int taskNumber;

        if (hasNullParameter(inputArray)) {
            throw new DukeException(MISSING_NUMBER_ERROR);
        }
        try {
            taskNumber = Integer.parseInt(inputArray[1]);
        } catch (NumberFormatException exception) {
            throw new DukeException(NUMBER_FORMAT_ERROR);
        }
        if (!TaskManager.isValidTaskNumber(taskNumber)) {
            throw new DukeException(NUMBER_NOT_FOUND_ERROR);
        }

        return taskNumber;
    }

    public String retrieveTodoParameter(String[] inputArray) throws DukeException {
        if (hasNullParameter(inputArray)) {
            throw new DukeException(TODO_ERROR);
        }
        return inputArray[1];
    }

    public String[] retrieveDeadlineParameters(String[] inputArray) throws DukeException {
        String[] params;

        if (hasNullParameter(inputArray)) {
            throw new DukeException(DEADLINE_DESCRIPTION_ERROR);
        }
        params = PARSER.separateDeadline(inputArray[1]);
        if (hasNullParameter(params)) {
            throw new DukeException(DEADLINE_DATE_ERROR);
        }
        if (params[0].isEmpty()) {
            throw new DukeException(DEADLINE_DESCRIPTION_ERROR);
        }
        if (params[1].isEmpty()) {
            throw new DukeException(DEADLINE_DATE_ERROR);
        }

        return params;
    }

    public String[] retrieveEventParameters(String[] inputArray) throws DukeException {
        String[] params;

        if (hasNullParameter(inputArray)) {
            throw new DukeException(EVENT_DESCRIPTION_ERROR);
        }
        params = PARSER.separateEvent(inputArray[1]);
        if (hasNullParameter(params)) {
            throw new DukeException(EVENT_DATE_ERROR);
        }
        if (params[0].isEmpty()) {
            throw new DukeException(EVENT_DESCRIPTION_ERROR);
        }
        if (params[1].isEmpty()) {
            throw new DukeException(EVENT_DATE_ERROR);
        }

        return params;
    }

    public void throwInput() throws DukeException {
        throw new DukeException(INVALID_COMMAND_ERROR);
    }

    public ArrayList<String> tryToReadFile() throws DukeException {
        ArrayList<String> fileLines;
        try {
            fileLines = FILE_MANAGER.readFile();
        } catch (FileNotFoundException exception) {
            try {
                FILE_MANAGER.createFile();
            } catch (IOException ioException) {
                throw new DukeException("IO Exception encountered: " + exception.getMessage());
            }
            throw new DukeException(FILE_NOT_FOUND_ERROR);
        }
        return fileLines;
    }

    public ArrayList<String[]> tryToProcessFile(ArrayList<String> fileLines) throws DukeException {
        ArrayList<String[]> dataArrayList = new ArrayList<>();
        String[] dataArray;
        for (String line : fileLines) {
            dataArray = PARSER.parseData(line);
            if (hasCorruptedData(dataArray)) {
                throw new DukeException(CORRUPTED_DATA_ERROR);
            }
            dataArrayList.add(dataArray);
        }
        return dataArrayList;
    }

    public void tryToWriteFile(ArrayList<String> fileLines) throws DukeException {
        try {
            FILE_MANAGER.writeFile(fileLines);
        } catch (IOException exception) {
            throw new DukeException("IO Exception encountered: " + exception.getMessage());
        }
    }
}
