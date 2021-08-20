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

    private static ArrayList<String> list = new ArrayList<String>();

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

    public static void editList(String input) {
        list.add(input);
        echo("added: " + input);
    }

    public static void main(String[] args) {
        greetingMessage();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while(!input.equalsIgnoreCase("bye")) {
            editList(input);
            input = scanner.nextLine();
        }
        farewellMessage();
    }
}
