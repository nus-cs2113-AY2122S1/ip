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

        greet();
        do {
            Scanner userType = new Scanner(System.in);
            userCommand = userType.next();
            echo(userCommand);
        } while (!Objects.equals(userCommand, "bye"));
        exit();
    }

    public static void greet() {
        printSign();
        System.out.println("Hello! I'm Duke\n");
        System.out.println("What can I do for you?\n");
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
