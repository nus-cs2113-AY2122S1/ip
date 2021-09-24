package duke.constants;

public class DukeDataStorageConstants extends DukeConstants {

    /** Default file path for file containing Duke's data */
    public static final String DIRECTORY_NAME = "dukeData";
    public static final String FILE_NAME = "tasks.txt";
    public static final String BACKSLASH = "\\";
    public static final String DEFAULT_STORAGE_FILEPATH = DIRECTORY_NAME + BACKSLASH + FILE_NAME;
    public static final String BACKSLASH_SEPARATOR = "\\\\";

    /** Strings which help in encoding and decoding Duke's data */
    public static final String ENCODER_ATTRIBUTE_SEPARATOR = " | ";
    public static final String DECODER_ATTRIBUTE_SEPARATOR = "\\|";
    public static final String DONE = "X";
    public static final String NOT_DONE = "O";

    /** Valid file type for Duke must be readable, hence .txt file */
    public static final String VALID_FILE_TYPE = ".txt";

    /** Messages to inform user of erroneous file I/O, decoding and encoding */
    public static final String FILE_WRONG_FORMAT_MESSAGE = WHITESPACE + "Task in " + DEFAULT_STORAGE_FILEPATH + " is of the wrong format!";
    public static final String FILE_DATE_TIME_WRONG_FORMAT_MESSAGE = WHITESPACE + "Date and time read from " + DEFAULT_STORAGE_FILEPATH + " are invalid!";
    public static final String INVALID_FILE_TYPE_MESSAGE = WHITESPACE + "Invalid file type! I can only store data in a \".txt\" file.";
    public static final String FILE_WRITE_ERROR_MESSAGE = WHITESPACE + "Something went wrong when writing to this file:" + WHITESPACE;
    public static final String FILE_CREATION_ERROR_MESSAGE = WHITESPACE + "Something went wrong when creating this file:" + WHITESPACE;
}
