import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static final int NUMBER_OF_TASKS = 100;
    public static final String DIVIDER = "/";
    public static final String LINE_SEPARATOR = "_____________________________";
    public static final String COMMAND_EXIT = "bye";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_DONE = "done";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";

    public static boolean canRunDuke = true;
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
        System.out.println(LINE_SEPARATOR);

        // to read input on each new line, Duke constantly scans input in this loop
        Scanner sc = new Scanner(System.in);
        int taskNumber = 0;
        TaskManager manager = new TaskManager(NUMBER_OF_TASKS);
        while(canRunDuke) {
            String inputStr = sc.nextLine();
            String command = getCommand(inputStr);

            switch (command) {
            case COMMAND_EXIT:
                System.out.println(LINE_SEPARATOR);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(LINE_SEPARATOR);
                canRunDuke = false;
                break;
            case COMMAND_LIST:
                manager.printTaskList();
                break;
            case COMMAND_DONE:
                if (isEmptyItem(inputStr)) {
                    System.out.println(LINE_SEPARATOR);
                    System.out.println("Oops, invalid description!");
                    System.out.println(LINE_SEPARATOR);
                    continue;
                } else if (manager.getNumberOfTasksUndone() == 0
                        | manager.getNumberOfTasksUndone() < Integer.parseInt(getItem(inputStr))) {
                    System.out.println(LINE_SEPARATOR);
                    System.out.println("Oops, invalid description");
                    System.out.println(LINE_SEPARATOR);
                    continue;
                }
                manager.markTaskAsDone(inputStr);
                break;
            case COMMAND_TODO:
                if (isEmptyItem(inputStr)) {
                    System.out.println(LINE_SEPARATOR);
                    System.out.println("Oops, invalid description!");
                    System.out.println(LINE_SEPARATOR);
                    continue;
                }
                String item = getItem(inputStr);
                manager.addToDoTaskToList(item, taskNumber);
                taskNumber++;
                break;
            case COMMAND_DEADLINE:
                if (isInvalidItem(inputStr)) {
                    System.out.println(LINE_SEPARATOR);
                    System.out.println("Oops, invalid description!");
                    System.out.println(LINE_SEPARATOR);
                    continue;
                }
                item = getItem(inputStr);
                manager.addDeadlineTaskToList(item, taskNumber);
                taskNumber++;
                break;
            case COMMAND_EVENT:
                if (isInvalidItem(inputStr)) {
                    System.out.println(LINE_SEPARATOR);
                    System.out.println("Oops, invalid description!");
                    System.out.println(LINE_SEPARATOR);
                    continue;
                }
                item = getItem(inputStr);
                manager.addEventTaskToList(item, taskNumber);
                taskNumber++;
                break;
            default:
                System.out.println(LINE_SEPARATOR);
                System.out.println("Oops, command not recognised!");
                System.out.println(LINE_SEPARATOR);
                break;
            }
        }
    }
}
