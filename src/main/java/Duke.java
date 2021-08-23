import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        Scanner in = new Scanner(System.in);
        TodoList list = new TodoList();
        String request = in.nextLine();
        while (!request.equals("bye")) {
            if (request.equals("list")) {
                list.printItems();
            } else {
                list.addItem(request);
                System.out.printf("added: %s\n", request);
            }
            request = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
