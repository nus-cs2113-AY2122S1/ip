package duke;
import java.util.List;
import java.util.Scanner;

class Ui {
    private static Scanner sc = new Scanner(System.in);

    public static void printException(Exception e) {
        System.out.println(e.getMessage());
    }

    public static void printWithIndex(List<?> list) {
        for (int i = 1; i <= list.size(); i += 1) {
            System.out.println(i + ": " + list.get(i - 1));
        }
    }

    public static String nextLine() {
        if (!sc.hasNextLine()) {
            return null;
        }
        return sc.nextLine();
    }
}
