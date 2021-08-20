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

    private static ArrayList<Task> taskList = new ArrayList<Task>();

    public static void greetingMessage() {
        System.out.println(horizontalBar);
        System.out.println(logo);
        System.out.println("  Hello! I'm Oberon");
        System.out.println("  What can I do for you?");
        System.out.println(horizontalBar);
    }

    public static void farewellMessage() {
        System.out.println(horizontalBar);
        System.out.println("  Goodbye. Hope to see you again soon!");
        System.out.println(logo);
        System.out.println(horizontalBar);
    }

    public static void echo(String input) {
            System.out.println(horizontalBar);
            System.out.println("  " + input);
            System.out.println(horizontalBar);
    }

    public static void editTaskList(Task taskInput) {
        taskList.add(taskInput);
        echo("added: " + taskInput.TaskDescription);
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
                                        + ". " + taskList.get(i).getStatusIcon()
                                        + " " + taskList.get(i).TaskDescription);
            }
            System.out.println(horizontalBar);
        }
    }

    public static void markTaskAsDone(String input) {
        int taskNumber = Integer.parseInt(input.replace("done", "").trim(), 10);
        boolean taskNumberInRange = (taskNumber <= taskList.size()) && (taskNumber >= 1);
        if (taskNumberInRange) {
            taskList.get(taskNumber - 1).setDone();
            System.out.println(horizontalBar);
            System.out.println("Task " + Integer.toString(taskNumber) + ": "
                                    + taskList.get(taskNumber - 1).TaskDescription);
            System.out.println("  Marked as done!");
        } else {
            System.out.println(horizontalBar);
            System.out.println("  Invalid Task number!");
        }
        System.out.println(horizontalBar);
    }

    public static void main(String[] args) {
        greetingMessage();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while(!input.equals("bye")) {
            if (input.equals("list")) {
                printTaskList();
            } else if (input.contains("done ")) {
                markTaskAsDone(input);
            } else {
                editTaskList(new Task(input));
            }
            input = scanner.nextLine();
        }
        farewellMessage();
    }
}
