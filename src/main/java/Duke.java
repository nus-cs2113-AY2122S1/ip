import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String horizontalLine = "____________________________________________________________" +
                System.lineSeparator();

        String greeting = horizontalLine +
                " Hello! I'm Duke" +
                System.lineSeparator() +
                " What can I do for you?" +
                System.lineSeparator() +
                horizontalLine;

        String bye = horizontalLine +
                " Bye. Hope to see you again soon!" +
                System.lineSeparator() +
                horizontalLine;

        String line;
        Scanner in = new Scanner(System.in);

        System.out.println(greeting);
        line = in.nextLine();

        while (!line.equals("bye")) {
            System.out.println(horizontalLine + ' ' + line + System.lineSeparator() + horizontalLine);
            line = in.nextLine();
        }

        System.out.println(bye);
    }
}
