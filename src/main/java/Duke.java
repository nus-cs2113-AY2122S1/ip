import java.util.Scanner;

public class Duke {
    public static void echoUserInput(String input) {
        System.out.println("  >>> " + input);
        System.out.println("********************************************");
    }

    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");

        line = in.nextLine();
        while (!line.toLowerCase().equals("bye")) {
          echoUserInput(line);
          line = in.nextLine();
        }
        System.out.println("Farewell. Hope to see you again soon!");
    }
}
