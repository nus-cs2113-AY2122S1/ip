import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static final int NUMBER_OF_TASKS = 100;
    public static final String DIVIDER = "/";

    /**
     * Splits the input string and returns the command at the start of the string
     *
     * @param args the input string
     * @return the command the user entered
     */
    public static String getCommand(String args) {
        String[] words = args.split(" ");
        String[] commandArray = Arrays.copyOf(words, 1);
        String command = getFormattedString(Arrays.toString(commandArray));
        return command;
    }

    /**
     * Splits the input string and returns the item after the command
     *
     * @param args the input string
     * @return the item after the command the user entered
     */
    public static String getItem(String args) {
        String[] words = args.split(" ");
        String[] itemArray = Arrays.copyOfRange(words, 1, words.length);
        String item = getFormattedString(Arrays.toString(itemArray));
        return item;
    }

    /**
     * Formats the string that was converted from an array. Delete brackets and commas
     *
     * @param args the input string that was converted from an array
     * @return the formatted string without brackets and commas
     */
    public static String getFormattedString(String args) {
        String formattedString = args.replace(",", "")
                .replace("]", "")
                .replace("[", "")
                .trim();
        return formattedString;
    }

    /**
     * Checks if the input string has an empty item after a command
     *
     * @param args the input string the user entered
     * @return true if the item after the command is empty, false otherwise
     */
    public static boolean isEmptyItem(String args) {
        if (getItem(args).equals("")) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the item from the user input is invalid for deadline and event tasks
     *
     * @param args the item to be checked
     * @return true if the item does not contain a '/', false otherwise
     */
    public static boolean isInvalidItem(String args) {
        if (getItem(args).contains(DIVIDER)) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);

        // greet
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("_____________________________");

        // to read input on each new line, Duke constantly scans input in this loop
        Scanner sc = new Scanner(System.in);
        int taskNumber = 0;
        TaskManager manager = new TaskManager(NUMBER_OF_TASKS);
        while(true) {
            String inputStr = sc.nextLine();

            if (getCommand(inputStr).equals("bye")) {
                // exit
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("_____________________________");
                break;
            } else if (getCommand(inputStr).equals("list")) {
                TaskManager.printTaskList();
            } else if (getCommand(inputStr).contains("done")) {
                if (isEmptyItem(inputStr)) {
                    System.out.println("Oops, invalid description!");
                    System.out.println("_____________________________");
                    continue;
                } else if (TaskManager.getNumberOfTasksUndone() == 0
                        | TaskManager.getNumberOfTasksUndone() < Integer.parseInt(getItem(inputStr))) {
                    System.out.println("Oops, invalid description");
                    System.out.println("_____________________________");
                    continue;
                }
                TaskManager.markTaskAsDone(inputStr);
            } else if (getCommand(inputStr).contains("todo")) {
                if (isEmptyItem(inputStr)) {
                    System.out.println("Oops, invalid description!");
                    System.out.println("_____________________________");
                    continue;
                }
                String item = getItem(inputStr);
                TaskManager.addToDoTaskToList(item, taskNumber);
                taskNumber++;
            } else if (getCommand(inputStr).contains("deadline")) {
                if (isInvalidItem(inputStr)) {
                    System.out.println("Oops, invalid description!");
                    System.out.println("_____________________________");
                    continue;
                }
                String item = getItem(inputStr);
                TaskManager.addDeadlineTaskToList(item, taskNumber);
                taskNumber++;
            } else if (getCommand(inputStr).contains("event")) {
                if (isInvalidItem(inputStr)) {
                    System.out.println("Oops, invalid description!");
                    System.out.println("_____________________________");
                    continue;
                }
                String item = getItem(inputStr);
                TaskManager.addEventTaskToList(item, taskNumber);
                taskNumber++;
            } else {
                System.out.println("Oops, command not recognised!");
                System.out.println("_____________________________");
            }
        }
    }
}
