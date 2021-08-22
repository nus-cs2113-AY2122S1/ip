import java.util.Locale;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String line = "____________________________________________________________\n";

        String helloGreeting = "Hello! I'm Duke \n" + "What can I do for you?\n";

        String byeGreeting = "Bye. Hope to see you again soon!\n";

        System.out.println(line + helloGreeting + line);

        Scanner input = new Scanner(System.in);

        String command = "";

        while (!((command.toLowerCase()).equals("bye"))) {
            command = input.nextLine();
            if (((command.toLowerCase()).equals("bye"))) {
                break;
            }
            System.out.println(line + command + "\n" + line);
        }

        System.out.println(line + byeGreeting + line);

    }
}
