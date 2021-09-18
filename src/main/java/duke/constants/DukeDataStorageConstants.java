package duke.constants;

public class DukeDataStorageConstants extends DukeConstants {
    // File path for file containing Duke's data
    public static final String DIRECTORY_NAME = "dukeData";
    public static final String FILE_NAME = "tasks.txt";
    public static final String FILE_PATH = DIRECTORY_NAME + "\\" + FILE_NAME;

    // Strings which help in storing and loading Duke's data
    public static final String ATTRIBUTE_SEPARATOR = " | ";
    public static final String DONE = "X";
    public static final String NOT_DONE = "O";

    // Messages to inform user of erroneous file I/O
    public static final String FILE_WRONG_FORMAT_MESSAGE = WHITESPACE + "Task in " + FILE_PATH + " is of the wrong format!";
}
