import java.util.Scanner;

public class Duke {
    public static final String DIVIDER = "-----------------------------------------";

    public static void main(String[] args) {
        System.out.println("Hello I'm Duke\nWhat can I do for you?");
        System.out.println(DIVIDER);
        boolean isExit;
        do {
            String line;
            Scanner in = new Scanner(System.in);
            line = in.nextLine();

            isExit = line.equals("bye");
            if (isExit) {
                System.out.println("Bye. Hope to see you again soon!");
            } else {
                System.out.println(line);
            }
            System.out.println(DIVIDER);
        } while (!isExit);
    }
}
