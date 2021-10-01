package duke.io;

/**
 * Formats the input and output of Duke.
 */
public class Formatter {

    /** Input and output formatting strings */
    private static final String INPUT_START = ">>> ";
    private static final String OUTPUT_START = "  ";
    private static final String OUTPUT_SEPARATOR = "********************************************";

    /**
     * Returns the formatted text before an input.
     * @return formatted text before an input
     */
    public static String returnInputStart() {
        return INPUT_START;
    }

    /**
     * Returns the formatted text before an output.
     * @return formatted text before an output
     */
    public static String returnOutputStart() {
        return OUTPUT_START;
    }

    /** Prints the formatted text before an input. */
    public static void printInputStart() {
        System.out.print(INPUT_START);
    }

    /** Prints the formatted text before an output. */
    public static void printOutputStart() {
        System.out.print(OUTPUT_START);
    }

    /** Prints the formatted separator line between lines of output. */
    public static void printOutputSeparator() {
        System.out.println(OUTPUT_SEPARATOR);
    }

    /** Prints the formatted output to the standard output. */
    public static void printFormattedOutput(String output) {
        String[] outputLines = output.split(System.lineSeparator());

        Formatter.printOutputSeparator();
        for (String line : outputLines) {
            Formatter.printOutputStart();
            System.out.println(line);
        }
    }
}
