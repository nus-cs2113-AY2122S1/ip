import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void printList(String[] list, int index) {
        for (int i = 0; i < index; i++) {
            System.out.println((i + 1) + ". " + list[i]);
        }
    }

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

        String[] commands = new String[100];
        Arrays.fill(commands,"");

        int index = 0;

        while (!(command.equalsIgnoreCase("bye"))) {
            command = input.nextLine();
            if ((command.equalsIgnoreCase("bye"))) {
                break;
            }
            if (command.equalsIgnoreCase("list")) {
                System.out.println(line);
                printList(commands, index);
                System.out.println(line);
            } else {
                commands[index] = command;
                System.out.println(line + "added: " + command + "\n" + line);
                index++;
            }
        }

        System.out.println(line + byeGreeting + line);
    }
}
