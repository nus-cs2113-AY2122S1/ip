import java.util.*;

public class Duke {
    public static void lineBreak() {
        System.out.println("\t____________________________________________________________\n");
    }
    public static void response(String input) {
        System.out.println("\t" + input + "\n");
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        lineBreak();
        System.out.println("\tHello! I'm Duke\n" + "\tWhat can I do for you?\n"); // greet
        lineBreak();
        Scanner sc= new Scanner(System.in);
        String input = sc.nextLine();
        lineBreak();
        while (!Objects.equals(input, "bye")) {
            response(input);
            lineBreak();
            input = sc.nextLine();
            lineBreak();
        }
        System.out.println("Bye. Hope to see you again soon!\n"); //exit message
        lineBreak();
    }
}
