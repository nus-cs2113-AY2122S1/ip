public class PrintUtils {

    private static final String INDENTATION = "\t";

    public static void printHorizontalLine(int length) {
        for (int i = 0; i < length; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public static void printSpacing() {
        System.out.print(INDENTATION);
    }
}
