package duke.io;

public class Formatter {

    private static final String INPUT_START = ">>> ";
    private static final String OUTPUT_START = "  ";
    private static final String OUTPUT_SEPARATOR = "********************************************";

    public static String returnInputStart() {
        return INPUT_START;
    }

    public static String returnOutputStart() {
        return OUTPUT_START;
    }
    
    public static void printInputStart() {
        System.out.print(INPUT_START);
    }
    public static void printOutputStart() {
        System.out.print(OUTPUT_START);
    }
    public static void printOutputSeparator() {
        System.out.println(OUTPUT_SEPARATOR);
    }
    
    public static void printFormattedOutput(String output) {
        String[] outputLines = output.split(System.lineSeparator());

        Formatter.printOutputSeparator();
        for (String line : outputLines) {
            Formatter.printOutputStart();
            System.out.println(line);
        }
    }
}
