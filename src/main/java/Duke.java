import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private static final String DIVIDER = "    ____________________________________________________________";
    private static List<String> store = new ArrayList<String>();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        greet();
        while (true) {
            System.out.println("");
            String command = scan.nextLine();
            if (command.equals("bye")) {
                break;
            } else {
                switch (command) {
                case "list":
                    list();
                    break;
                default:
                    add(command);
                }
            }
        }
        bye();
        scan.close();
    }

    public static void greet() {
        System.out.println(DIVIDER);
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println(DIVIDER);
    }

    public static void bye() {
        System.out.println(DIVIDER);
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println(DIVIDER);
    }

    public static void echo(String command) {
        System.out.println(DIVIDER);
        System.out.println("     " + command);
        System.out.println(DIVIDER);
    }

    public static void add(String command) {
        System.out.println(DIVIDER);
        store.add(command);
        System.out.println("     added: " + command);
        System.out.println(DIVIDER);
    }

    public static void list() {
        System.out.println(DIVIDER);
        int index = 1;
        for (String item : store) {
            System.out.println("     " + index + ". " + item);
            index++;
        }
        System.out.println(DIVIDER);
    }
}
