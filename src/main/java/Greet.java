import java.util.Arrays;
import java.util.Scanner;

public class Greet {
    public static void main (String[] args) {
        System.out.println("Hello! I'm Duke");
        String line;
        Scanner in = new Scanner(System.in);
        System.out.println("What can I do for you?");
        line = in.nextLine();
        boolean bool1 = false;
        while (!bool1) {
            System.out.println(line);
            line = in.nextLine();
            if (line.contains("bye")) {
                bool1 = true;
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}