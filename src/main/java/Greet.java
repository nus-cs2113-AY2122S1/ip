import java.util.Arrays;
import java.util.Scanner;

public class Greet {
    public static void main (String[] args) {
        String[] tasks = new String[100];
        int ntasks = 0;
        System.out.println("Hello! I'm Duke");
        String line;
        Scanner in = new Scanner(System.in);
        System.out.println("What can I do for you?");
        line = in.nextLine();
        boolean bool1 = false;
        while (!bool1) {
            System.out.println("added: " + line);
            tasks[ntasks]= line;
            ntasks += 1;
            line = in.nextLine();
            if (line.equals("list")) {
                for (int i = 0; i < ntasks; i += 1) {
                    System.out.println((i+1) + ". " + tasks[i]);
                }
            }
            if (line.contains("bye")) {
                bool1 = true;
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}