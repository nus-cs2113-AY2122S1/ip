import javax.lang.model.type.NullType;
import java.util.*;

public class Duke {
    public static void line() {
        System.out.println("\t____________________________________________________________\n");
    }
    public static void response(String input, Information user) {
        if (input.equals("list")) { // show list
            for (int i = 0; i < user.listLength; i++) {
                if (user.list[i].complete) {
                    System.out.println("\t" + (i + 1) + ". [X] " + user.list[i].item + "\n");
                } else {
                    System.out.println("\t" + (i + 1) + ". [ ] " + user.list[i].item + "\n");
                }
            }
        } else if (input.charAt(0) == '+' || (input.length() > 3 && input.substring(0, 3).equals("add"))) { // add to list
            if (input.charAt(0) == '+') {
                user.list[user.listLength] = new Task(input.substring(2));
                System.out.println("\tadded: " + input.substring(2) + "\n");
            } else {
                user.list[user.listLength] = new Task(input.substring(4));
                System.out.println("\tadded: " + input.substring(4) + "\n");
            }
            user.listLength ++;
        } else if (input.length() > 4 && input.substring(0,4).equals("done")) {
            int index = Integer.parseInt(input.substring(5)) - 1;
            if (index < user.listLength) {
                user.list[index].markComplete();
                System.out.println("\tNice! I've marked this task as done:\n\t[X] " + user.list[index].item);
            }
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
