import java.util.Objects;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        Scanner scanner = new Scanner(System.in);

        System.out.println("    Hello from\n" + logo);
        System.out.println("    _______________________________");
        System.out.println("    What up Dawg! I'm Duke");
        System.out.println("    What can I do for you?");
        String task = scanner.nextLine();
        String endTask = "bye";
        while (!task.equals(endTask)) {
            System.out.println("    _______________________________");
            System.out.println("    " + task);
            System.out.println("    _______________________________");
            task = scanner.nextLine();
        }
        System.out.println("    _______________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ______________________________");
    }
}
