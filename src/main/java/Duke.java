// import libraries here
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<String> list = new ArrayList<>();
    private static ArrayList<Integer> done = new ArrayList<>();
    private static int count = 0;

    public static void greeting() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");
        System.out.println();
    }
    public static void echo() {
        String line;
        Scanner in = new Scanner(System.in);
        // get user input
        line = in.nextLine();
        while (!line.equals("bye")) {
            System.out.println("    ____________________________________________________________");
            System.out.println("     " + line);
            System.out.println("    ____________________________________________________________");
            line = in.nextLine();
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
    public static void showList() {
        System.out.println("    ____________________________________________________________");
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("     %d. %s", i + 1, list.get(i));
            System.out.println();
        }
        System.out.println("    ____________________________________________________________");
    }
    public static void addList() {
        String line;
        Scanner in = new Scanner(System.in);
        // get user input
        line = in.nextLine();
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                showList();
                line = in.nextLine();
                continue;
            }
            System.out.println("    ____________________________________________________________");
            list.add(line);
            done.add(0);
            count++;
            System.out.println("     added: " + line);
            System.out.println("    ____________________________________________________________");
            line = in.nextLine();
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greeting();
//        echo();
        addList();
    }
}
