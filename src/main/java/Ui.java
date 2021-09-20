import java.util.Scanner;

public class Ui {
    static String getLine(Scanner in) {
        String line = in.nextLine();
        return line;
    }

    static Scanner getScanner() {
        Scanner in = new Scanner(System.in);
        return in;
    }

    static String[] getWords(String line) {
        String[] words = line.split(" ");
        return words;
    }
}
