import java.util.Scanner;

public class Duke {
    public static void hi() {
        System.out.println("Hello I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void echo() {
        String line;
        Scanner in = new Scanner(System.in);
        while (true) {
            line = in.nextLine();
            if (line.equals("bye")) {
                return;
            }
            System.out.println(line);
        }
    }

    public static void main(String[] args) {
        hi();
        echo();
        bye();
    }
}
