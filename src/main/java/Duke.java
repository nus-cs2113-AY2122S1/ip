package ip.src.main.java;
import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?\n");

        String line = null;
        while (!Objects.equals(line, "bye")) {
            line = in.nextLine();
            if (!Objects.equals(line, "bye")) System.out.println(line);
        }

        System.out.println("Bye. Hope to see you again soon!\n");

    }
}
