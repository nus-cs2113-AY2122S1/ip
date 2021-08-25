import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String line = "\t____________________________________________________________\n\t";
        String greeting = line
                + "Hello! I'm Duke\n\t"
                + "What can I do for you?\n"
                + line;
        String bye = line
                + "Bye. Hope to you again soon!\n"
                + line;

        String command;
        Scanner in = new Scanner(System.in);

        System.out.println("\tHello from\n" + logo);
        System.out.println(greeting);
        command = in.nextLine();
        while (!command.equals("bye")) {
            System.out.println(line + command + "\n" + line);
            command = in.nextLine();
        }

        System.out.println(bye);
    }
}
