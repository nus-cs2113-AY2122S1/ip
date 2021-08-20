import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
     private static String logo = System.lineSeparator() +
            "    ___   ______   ________  _______      ___   ____  _____   .--." + System.lineSeparator() +
            "  .'   `.|_   _ \\ |_   __  ||_   __ \\   .'   `.|_   \\|_   _|.'_\\/_'." + System.lineSeparator() +
            " /  .-.  \\ | |_) |  | |_ \\_|  | |__) | /  .-.  \\ |   \\ | |  '. /\\,.'" + System.lineSeparator() +
            " | |   | | |  __'.  |  _| _   |  __ /  | |   | | | |\\ \\| |    \"||\"" + System.lineSeparator() +
            " \\  `-'  /_| |__) |_| |__/ | _| |  \\ \\_\\  `-'  /_| |_\\   |_    || /\\`" + System.lineSeparator() +
            "  `.___.'|_______/|________||____| |___|`.___.'|_____|\\____|/\\ ||//\\)" + System.lineSeparator() +
            "                                                           (/\\\\||/" + System.lineSeparator() +
            "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\\||^^^^";

    private static String horizontalBar = "__________________" +
                                          "__________________" +
                                          "__________________" +
                                          "_______________";

    private static ArrayList<String> taskList = new ArrayList<String>();

    public static void greetingMessage() {
        System.out.println(horizontalBar);
        System.out.println(logo);
        System.out.println("  Hello! I'm Oberon");
        System.out.println("  What can I do for you?");
        System.out.println(horizontalBar);
    }

    public static void farewellMessage() {
        System.out.println(horizontalBar);
        System.out.println("  Bye. Hope to see you again soon!");
        System.out.println(logo);
        System.out.println(horizontalBar);
    }

    public static void echo(String input) {
            System.out.println(horizontalBar);
            System.out.println("  " + input);
            System.out.println(horizontalBar);
    }

    public static void editTaskList(String input) {
        taskList.add(input);
        echo("added: " + input);
    }

    public static void printTaskList() {
        if (taskList.isEmpty()) {
            System.out.println(horizontalBar);
            System.out.println("  List is empty!");
            System.out.println(horizontalBar);
        } else {
            System.out.println(horizontalBar);
            for (int i = 0; i < taskList.size(); i++) {
                int currentIndexInOnesIndexing = i + 1;
                System.out.println(Integer.toString(currentIndexInOnesIndexing)
                                    + ". " + taskList.get(i));
            }
            System.out.println(horizontalBar);
        }
    }

    public static void main(String[] args) {
        greetingMessage();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while(!input.equalsIgnoreCase("bye")) {
            if (input.equalsIgnoreCase("list")) {
                printTaskList();
            } else {
                editTaskList(input);
            }
            input = scanner.nextLine();
        }
        farewellMessage();
    }
}
