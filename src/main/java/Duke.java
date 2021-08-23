import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Prints message within horizontal lines.
     *
     * @param message The message to print.
     */
    public static void printMessage(String message) {
        System.out.println("------------------------------------------------------------");
        System.out.println(message);
        System.out.println("------------------------------------------------------------");
    }

    /**
     * Prints tasks in list.
     *
     * @param header Message header.
     */
    public static void printList(String header) {
        String message = header;
        for (int i = 0; i < taskList.size(); i += 1) {
            message += String.format("\n%d: %s",i + 1, taskList.get(i).getListEntryString());
        }
        printMessage(message);
    }

    /**
     * Checks if string value is an integer.
     *
     * @param string The string to check.
     * @return true if string can be converted to integer, else false.
     */
    public static boolean isInteger(String string) {
        try {
            int value = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        printMessage("Hello! I'm Duke\nWhat can I do for you?");

        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        do {
            System.out.print("$ ");
            String input = scanner.nextLine();
            switch (input) {
            case "list":
                String message;
                if (taskList.isEmpty()) {
                    printMessage("List is empty");
                } else {
                    printList("Task List:");
                }
                break;
            case "bye":
                run = false;
                break;
            default:
                if (input.startsWith("done")) {
                    String[] inputSplit = input.split("\\s+");
                    if (inputSplit.length == 2 && isInteger(inputSplit[1])) {
                        int taskIndex = Integer.parseInt(inputSplit[1]);
                        if (taskIndex > 0 && taskIndex <= taskList.size()) {
                            Task task = taskList.get(taskIndex - 1);
                            if (task.isDone()) {
                                printMessage(String.format("Task #%d is already marked as done",taskIndex));
                            } else {
                                task.setDone();
                                printMessage(String.format("Task marked as done:\n%s",task.getListEntryString()));
                            }
                        } else {
                            printMessage("Invalid task number");
                        }
                    } else {
                        printMessage("Usage: done <task number>");
                    }
                } else {
                    Task task = new Task(input);
                    taskList.add(task);
                    printMessage(String.format("Added: %s",task.getName()));
                }
                break;
            }
        } while (run);

        printMessage("Bye. Hope to see you again soon!");
    }
}
