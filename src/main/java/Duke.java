import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String userCommand;
        String[] list = new String[100];
        int i = 0;

        greet();
        do {
            Scanner userType = new Scanner(System.in);
            userCommand = userType.nextLine();
            if (!Objects.equals(userCommand, "list")) list[i] = userCommand;
            add(userCommand, list, i);
            if (!Objects.equals(userCommand, "list")) i++;
        } while (!Objects.equals(userCommand, "bye"));
        exit();
    }

    public static void greet() {
        printSign();
        System.out.println("Hello! I'm Duke\n");
        System.out.println("What can I do for you?\n");
        printSign();
    }

    public static void add(String args, String[] list, int i) {
        if (Objects.equals(args, "bye")) {
            printSign();
            return;
        }
        if (Objects.equals(args, "list")) {
            printList(list, i);
            return;
        }
        printSign();
        System.out.println("added: " + args);
        printSign();
    }

    public static void echo(String args) {
        if (Objects.equals(args, "bye")) {
            printSign();
            return;
        }
        printSign();
        System.out.println(args);
        printSign();
    }

    public static void printList(String[] args, int length) {
        printSign();
        for (int i = 0; i < length; i++) {
            System.out.print(i+1 + ".");
            System.out.println(" " + args[i]);
        }
        printSign();
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!\n");
        printSign();
    }

    public static void printSign(){
        for(int i = 1; i <= 20; i++){
            System.out.print("-");
        }
        System.out.println();
    }
}
