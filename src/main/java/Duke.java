import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String LOGO = System.lineSeparator() +
            "    ___   ______   ________  _______      ___   ____  _____   .--." + System.lineSeparator() +
            "  .'   `.|_   _ \\ |_   __  ||_   __ \\   .'   `.|_   \\|_   _|.'_\\/_'." + System.lineSeparator() +
            " /  .-.  \\ | |_) |  | |_ \\_|  | |__) | /  .-.  \\ |   \\ | |  '. /\\,.'" + System.lineSeparator() +
            " | |   | | |  __'.  |  _| _   |  __ /  | |   | | | |\\ \\| |    \"||\"" + System.lineSeparator() +
            " \\  `-'  /_| |__) |_| |__/ | _| |  \\ \\_\\  `-'  /_| |_\\   |_    || /\\`" + System.lineSeparator() +
            "  `.___.'|_______/|________||____| |___|`.___.'|_____|\\____|/\\ ||//\\)" + System.lineSeparator() +
            "                                                           (/\\\\||/" + System.lineSeparator() +
            "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\\||^^^^";

    private static final String HORIZONTAL_BAR = "__________________" +
            "__________________" +
            "__________________" +
            "_______________";

    private static ArrayList<Task> taskList = new ArrayList<Task>();

    public static void greetingMessage() {
        System.out.println(HORIZONTAL_BAR);
        System.out.println(LOGO);
        System.out.println("  Hello! I'm Oberon");
        System.out.println("  What can I do for you?");
        System.out.println(HORIZONTAL_BAR);
    }

    public static void farewellMessage() {
        System.out.println(HORIZONTAL_BAR);
        System.out.println("  Goodbye. Hope to see you again soon!");
        System.out.println(LOGO);
        System.out.println(HORIZONTAL_BAR);
    }

    public static void echo(String input) {
        System.out.println(HORIZONTAL_BAR);
        System.out.println("  " + input);
        System.out.println(HORIZONTAL_BAR);
    }

    public static void editTaskList(Task taskInput) {
        taskList.add(taskInput);
        echo("added: " + taskInput.taskDescription);
    }

    public static void printTaskList() {
        if (taskList.isEmpty()) {
            System.out.println(HORIZONTAL_BAR);
            System.out.println("  List is empty!");
            System.out.println(HORIZONTAL_BAR);
        } else {
            System.out.println(HORIZONTAL_BAR);
            // Traverse down the ArrayList and prints out each task
            for (int i = 0; i < taskList.size(); i++) {
                int currentIndexInOnesIndexing = i + 1;
                System.out.println(Integer.toString(currentIndexInOnesIndexing)
                        + ". " + taskList.get(i).getStatusIcon()
                        + " " + taskList.get(i).taskDescription);
            }
            System.out.println(HORIZONTAL_BAR);
        }
    }

    public static void markTaskAsDone(String input) {
        // Extracting task number as an integer from input
        int taskNumber = Integer.parseInt(input.replace("done", "").trim(), 10);
        boolean taskNumberInRange = (taskNumber <= taskList.size()) && (taskNumber >= 1);
        if (taskNumberInRange) {
            taskList.get(taskNumber - 1).setDone();
            System.out.println(HORIZONTAL_BAR);
            System.out.println("Task " + Integer.toString(taskNumber) + ": "
                    + taskList.get(taskNumber - 1).taskDescription);
            System.out.println("  Marked as done!");
        } else {
            System.out.println(HORIZONTAL_BAR);
            System.out.println("  Invalid Task number!");
        }
        System.out.println(HORIZONTAL_BAR);
    }

    public static void main(String[] args) {
        greetingMessage();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
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
