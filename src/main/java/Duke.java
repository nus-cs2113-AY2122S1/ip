import javax.lang.model.type.NullType;
import java.util.*;

class Information {
        String[] list = new String[100];
        int listLength;
}

public class Duke {
    public static void line() {
        System.out.println("\t____________________________________________________________\n");
    }
    public static void response(String input, Information user) {
        if (input.equals("list")) { // show list
            for (int i = 0; i < user.listLength; i++) {
                System.out.println("\t" + (i + 1) + ". " + user.list[i] + "\n");
            }
        } else if (input.substring(0,1).equals("+") || (input.length() > 3 && input.substring(0, 3).equals("add"))) { // add to list
            if (input.substring(0, 1).equals("+")) {
                user.list[user.listLength] = input.substring(2);
                System.out.println("\tadded: " + input.substring(2) + "\n");
            } else {
                user.list[user.listLength] = input.substring(4);
                System.out.println("\tadded: " + input.substring(4) + "\n");
            }
            user.listLength ++;
        } else {
            System.out.println("\t" + input + "\n");
        }
    }
    public static void greet(String logo) {
        System.out.println("Hello from\n" + logo);
        line();
        System.out.println("\tHello! I'm Duke\n" + "\tWhat can I do for you?\n"); // greet
        line();
    }
    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!\n"); //exit message
        line();
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        greet(logo);

        Information user = new Information();
        user.listLength = 0;

        Scanner sc= new Scanner(System.in);
        String input = sc.nextLine();
        line();
        while (!input.equals("bye")) {
            response(input, user);
            line();
            input = sc.nextLine();
            line();
        }
        exit();
    }
}
