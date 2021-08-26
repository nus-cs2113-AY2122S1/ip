import java.util.Scanner;

public class Duke {
    public static void greeting() {
        String greeting = " Hello! I'm pepepopo\n" +
                        " What can I do for you?";
        System.out.println(greeting);
        divider();
    }

    public static void divider() {
        String divider = "\n____________________________________________________________";
        System.out.println(divider);
    }

    public static void bye() {
        String bye = " Bye. Hope to see you again soon!";
        System.out.println(bye);
        divider();

    }

    public static String input() {
        String line;
        Scanner in = new Scanner(System.in);

        line = in.nextLine();
        return line;
    }

    public static void echo(String text) {
        if (!text.equals("bye")) {
            System.out.println(text);
            divider();

            echo(input());
        }
        else {
            bye();
        }
    }

    public static void main(String[] args) {
        greeting();
        echo(input());
    }
}
