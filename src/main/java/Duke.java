import java.util.Scanner;

public class Duke {

    public static void reply(String input) {
        System.out.println("   ____________________________________________________________\n" +
                "       " + input + "\n" +
                "   ____________________________________________________________");
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String greetStart = "   ____________________________________________________________\n" +
                "       Hello! I'm Duke\n" +
                "       What can I do for you?\n" +
                "   ____________________________________________________________";

        String greetEnd = "   ____________________________________________________________\n" +
                "       Bye. Hope to see you again soon!\n" +
                "   ____________________________________________________________";

        System.out.println(greetStart);
        line = in.nextLine();
        while (!line.equals("bye")) {
            reply(line);
            line = in.nextLine();
        }
        System.out.println(greetEnd);
    }
}
