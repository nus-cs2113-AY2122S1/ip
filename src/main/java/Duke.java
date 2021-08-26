// import libraries here
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<String> list = new ArrayList<>();
    private static ArrayList<Integer> done = new ArrayList<>();

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
            System.out.printf("     %d.", i + 1);
            if (done.get(i) == 1) {
                System.out.print("[X] ");
            } else {
                System.out.print("[ ] ");
            }
            System.out.println(list.get(i));
        }
        System.out.println("    ____________________________________________________________");
    }
    public static void markAsDone(int index) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Nice! I've marked this task as done:");
        String task = list.get(index - 1);
        // mark as done
        done.set(index - 1, 1);
        System.out.println("       [X] " + task);
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
            if (line.startsWith("done")) {
                int index = Integer.parseInt(line.substring(line.length() - 1));
                markAsDone(index);
                line = in.nextLine();
                continue;
            }
            System.out.println("    ____________________________________________________________");
            list.add(line);
            done.add(0);
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
