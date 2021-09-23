import java.util.Scanner;

public class Ui {
    public static String getLine(Scanner in) {
        String line = in.nextLine();
        return line;
    }

    public static Scanner getScanner() {
        Scanner in = new Scanner(System.in);
        return in;
    }

    /**
     * Breaks up a line of input into words and stores the words in an array.
     * @param line the line of input to be broken up
     * @return the array containing all the words in the line of input
     */
    public static String[] getWords(String line) {
        String[] words = line.split(" ");
        return words;
    }
}
