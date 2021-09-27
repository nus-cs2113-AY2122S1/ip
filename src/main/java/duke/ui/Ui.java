package duke.ui;

public class Ui {
    private static final String INDENTED_HORIZONTAL_LINE = " ".repeat(4) + "_".repeat(60);
    /** Platform independent line separator */
    private static final String LINE_SEPARATOR = System.lineSeparator();

    private static final String MESSAGE_GREETING = "Hello! I'm Duke\n" + "%1$s\n" + "What can I do for you?";
    private static final String MESSAGE_DATA_FILE_NEW = "No data file found. Will store data in new file: '%1$s'";
    private static final String MESSAGE_DATA_FILE_EXISTING = "Data file found. Using data from: '%1$s'";

    private static final String MESSAGE_FAREWELL = "Bye. Hope to see you again soon!";

    /**
     * Prints out the specified text formatted as a response block.
     * Horizontal lines will be printed before and after the specified text, and the text will be indented.
     *
     * @param text Text to be printed out.
     */
    public void showToUser(String text) {
        System.out.println(INDENTED_HORIZONTAL_LINE);
        System.out.println(indentAndReplaceNewLines(text));
        System.out.println(INDENTED_HORIZONTAL_LINE);
        System.out.println();
    }

    public void showGreeting(String path, boolean isUsingNewFile) {
        final String dataFileMessage = String.format(
                (isUsingNewFile ? MESSAGE_DATA_FILE_NEW : MESSAGE_DATA_FILE_EXISTING), path);
        showToUser(String.format(MESSAGE_GREETING, dataFileMessage));
    }

    private String indentAndReplaceNewLines(String text) {
        String[] lines = text.split("\n");
        for (int i = 0; i < lines.length; i++) {
            lines[i] = " ".repeat(5) + lines[i];
        }
        return String.join(LINE_SEPARATOR, lines);
    }

    public void showFarewell() {
        showToUser(MESSAGE_FAREWELL);
    }
}
