import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String line = "____________________________________________________________";
        System.out.println(" Hello! I'm Duke\n" +
                " What can I do for you?");
        System.out.println(line);

        String text;
        text = in.nextLine();
        while (!text.equals("bye")) {
            System.out.println(line);
            System.out.println(text);
            System.out.println(line);
            text = in.nextLine();
        }

        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
