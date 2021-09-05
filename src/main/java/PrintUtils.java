public class PrintUtils {

    private static final String INDENTATION = "\t";
    private static final int HORIZONTAL_LINE_LENGTH = 100;

    public static void printHorizontalLine() {
        for (int i = 0; i < HORIZONTAL_LINE_LENGTH; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public static void printSpacing() {
        System.out.print(INDENTATION);
    }

    public static void printErrorMessage(String errorMessage) {
        printHorizontalLine();
        System.out.println(errorMessage);
        printHorizontalLine();
    }
}
