package duke.constants;

public class DukeDataStorageConstants extends DukeConstants {

    // File path for file containing Duke's data
    public static final String DIRECTORY_NAME = "dukeData";
    public static final String FILE_NAME = "tasks.txt";
    public static final String DEFAULT_STORAGE_FILEPATH = DIRECTORY_NAME + "\\" + FILE_NAME;

    // Strings which help in storing and loading Duke's data
    public static final String ENCODER_ATTRIBUTE_SEPARATOR = " | ";
    public static final String DECODER_ATTRIBUTE_SEPARATOR = "\\|";
    public static final String DONE = "X";
    public static final String NOT_DONE = "O";

    // Valid file type for Duke (readable)
    public static final String VALID_FILE_TYPE = ".txt";

    // Messages to inform user of erroneous file I/O
    public static final String FILE_WRONG_FORMAT_MESSAGE = WHITESPACE + "Task in " + DEFAULT_STORAGE_FILEPATH + " is of the wrong format!";
    public static final String INVALID_FILE_TYPE_MESSAGE = WHITESPACE + "Invalid file type! I can only store data in a \".txt\" file.";
}
