import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");
        System.out.println("_______________________________________\n");

        Scanner in = new Scanner(System.in);

        String list = in.nextLine();
        System.out.println(list + "\n");
        System.out.println("_______________________________________\n");

        String blah = in.nextLine();
        System.out.println(blah + "\n");
        System.out.println("_______________________________________\n");

        System.out.println("Bye. Hope to see you again soon!\n");
        System.out.println("_______________________________________\n");

        in.close();
    }
}

