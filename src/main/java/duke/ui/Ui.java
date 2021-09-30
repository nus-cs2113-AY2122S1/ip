package duke.ui;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Text UI of the application.
 */
public class Ui {
    private static final String INDENTED_HORIZONTAL_LINE = " ".repeat(4) + "_".repeat(60);
    private static final String LINE_PREFIX = " ".repeat(5);
    /** Platform independent line separator. */
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final Scanner SCANNER = new Scanner(System.in);

    private static final String MESSAGE_GREETING = "Hello! I'm Duke\n" + "%1$s\n" + "What can I do for you?";
    private static final String MESSAGE_DATA_FILE_NEW = "No data file found. Will store data in new file: '%1$s'";
    private static final String MESSAGE_DATA_FILE_EXISTING = "Data file found. Using data from: '%1$s'";

    public static final DateTimeFormatter DATE_TIME_OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy h.mma");

    /**
     * Reads input commands from the user.
     * Ignores blank lines and trims input command.
     *
     * @return Trimmed input command.
     */
    public String getUserInput() {
        String line = SCANNER.nextLine();
        // Ignore blank lines
        while (line.trim().isEmpty()) {
            line = SCANNER.nextLine();
        }
        return line.trim();
    }

    /**
     * Prints out the specified text formatted as a block.
     * Horizontal lines will be printed before and after the
     * specified text, and the text will be indented.
     *
     * @param text Text to be printed out.
     */
    public void showToUser(String text) {
        System.out.println(INDENTED_HORIZONTAL_LINE);
        System.out.println(addPrefixAndReplaceNewline(text));
        System.out.println(INDENTED_HORIZONTAL_LINE);
        System.out.println();
    }

    /**
     * Prints out greeting message.
     * Indicates where the data file is located, and whether a new file is being used.
     *
     * @param path Path of the file where the data is stored in.
     * @param isUsingNewFile Whether a new file is being used (because an existing file cannot be found).
     */
    public void showGreeting(String path, boolean isUsingNewFile) {
        final String dataFileMessage = String.format(
                (isUsingNewFile ? MESSAGE_DATA_FILE_NEW : MESSAGE_DATA_FILE_EXISTING), path);
        showToUser(String.format(MESSAGE_GREETING, dataFileMessage));
    }

    /**
     * Adds {@link #LINE_PREFIX} to the start of each line of {@code text}, and replaces newline characters with the
     * platform independent line separator ({@link #LINE_SEPARATOR}).
     *
     * @param text Text to be processed.
     * @return Processed text.
     */
    private String addPrefixAndReplaceNewline(String text) {
        String[] lines = text.split("\n");
        for (int i = 0; i < lines.length; i++) {
            lines[i] = LINE_PREFIX + lines[i];
        }
        return String.join(LINE_SEPARATOR, lines);
    }
}
