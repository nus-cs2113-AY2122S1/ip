import java.util.Scanner;

public class Duke {
    public static void echo(String line) {
        String separator = "____________________________________________________________";
        System.out.println(separator);
        System.out.println(" " + line);
        System.out.println(separator);
    }

    public static void main(String[] args) {
        String separator = "____________________________________________________________";
        String line;
        Scanner in = new Scanner(System.in);

        System.out.println(separator);
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        System.out.println(separator);

        line = in.nextLine();
        while (!line.equals("bye")) {
            echo(line);
            line = in.nextLine();
        }

        System.out.println(separator);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(separator);
    }
}
