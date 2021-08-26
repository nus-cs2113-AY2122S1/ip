import java.util.Scanner;

public class Duke {
    static String exitTrigger = "bye";
    static String listTrigger = "list";
    static String[] commands = new String[100];

    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n"
                + "What can I do for you?\n");
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void add(String command, int item_num) {
        commands[item_num] = command;
        System.out.println("added: " + command);
    }

    public static void list() {
        for(int i = 0; commands[i] != null; i++) {
            int bullet_num = i+1;
            System.out.println(bullet_num +". " + commands[i]);
        }
    }

    public static void main(String[] args) {
        greet();

        Scanner in = new Scanner(System.in);
        String command = in.nextLine();

        boolean should_exit = command.equals(exitTrigger);

        for(int i = 0; !command.equals(exitTrigger) ; i++) {
            if (command.equals(listTrigger)) {
                list();
                i--;
            } else {
                add(command, i);
            }
            command = in.nextLine();
        }

        exit();
    }
}
