import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    //List of Special User Commands
    public static final String EXIT_STRING = "bye";
    public static final String LIST_COMMAND = "list";
    public static final String DONE_COMMAND = "done";
    public static final String TODO_COMMAND = "todo";
    public static final String DEADLINE_COMMAND = "deadline";
    public static final String EVENT_COMMAND = "event";

    //Other constants
    public static final int NUM_OF_TASKS = 100;
    public static final String LINE = "____________________________________________________________";
    public static final String EVENT_KEYWORD = " /at";
    public static final String DEADLINE_KEYWORD = " /by";
    public static final String GOODBYE_MESSAGE = " Bye. Hope to see you again soon!";
    public static final String EXCEPTION = " Sorry, your input is invalid! Please enter a valid input :)";

    public static void printList(Task[] taskList) {
        if (taskList.length == 0) {
            System.out.println(LINE);
            System.out.println(" No Tasks here yet. Go include some tasks!");
            System.out.println(LINE);
            System.out.println();
            return;
        }

        System.out.println(LINE);
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < taskList.length; i++) {
            System.out.println(" " + (i + 1) + ". " + taskList[i].toString());
        }
        System.out.println(LINE);
        System.out.println();
    }

    public static void printDukeGreet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        //Greeting the User
        System.out.println(LINE);
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        System.out.println(LINE);
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int num = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static void returnException() {
        System.out.println(LINE);
        System.out.println(EXCEPTION);
        System.out.println(LINE);
        System.out.println();
    }

    public static boolean checkValidDoneInstruction(String inWord) {
        String[] commands = inWord.split(" ");
        boolean isNumericDigit = isNumeric(commands[1]);
        return commands.length == 2 && isNumericDigit;
    }

    public static void printTaskDone(String inWord, int index, Task[] taskList) {
        String[] commands = inWord.split(" ");
        int taskDoneIndex = Integer.parseInt(commands[1]);
        if (taskDoneIndex <= 0 || taskDoneIndex > index) {
            System.out.println(LINE);
            System.out.println("Item out of Index! Please input a valid task number :)");
            System.out.println(LINE);
            System.out.println();
        } else {
            taskList[taskDoneIndex - 1].markAsDone();
            System.out.println(LINE);
            System.out.println(" Nice! I've marked this task as done:");
            System.out.println("   " + taskList[taskDoneIndex - 1].toString());
            System.out.println(LINE);
            System.out.println();
        }
    }

    public static boolean checkValidEvent(String inWord) {
        String[] commands = inWord.split("\\s+", 2);
        if (commands.length != 2 || !inWord.contains(EVENT_KEYWORD)) {
            return false;
        }

        String[] details = commands[1].split(EVENT_KEYWORD, 2);
        String descriptionDetails = details[0].trim();
        String descriptionAt = details[1].trim();
        boolean isNonEmptyDescription = (!descriptionDetails.isEmpty() && !descriptionAt.isEmpty());

        return details.length == 2 && isNonEmptyDescription;
    }

    public static void printEvent(String inWord, int index, Task[] taskList) {
        String[] commands = inWord.split("\\s+", 2);
        String[] details = commands[1].split(EVENT_KEYWORD, 2);

        String description = details[0].trim();
        String at = details[1].trim();

        Event newItem = new Event(description, at);
        taskList[index] = newItem;
        System.out.println(LINE);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + newItem);
        System.out.println(" Now you have " + (index + 1) +" tasks in the list.");
        System.out.println(LINE);
        System.out.println();
    }

    public static boolean checkValidTodo(String inWord) {
        String[] commands = inWord.split("\\s+", 2);
        String details = commands[1];
        boolean isNonEmptyDetails = !details.isEmpty();
        return commands.length == 2 && isNonEmptyDetails;
    }

    public static void printTodo(String inWord, int index, Task[] taskList) {
        String[] commands = inWord.split("\\s+", 2);
        String description = commands[1];

        Todo newItem = new Todo(description);
        taskList[index] = newItem;
        System.out.println(LINE);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + newItem);
        System.out.println(" Now you have " + (index + 1) +" tasks in the list.");
        System.out.println(LINE);
        System.out.println();
    }

    public static boolean checkValidDeadline(String inWord) {
        String[] commands = inWord.split("\\s+", 2);
        if (commands.length != 2 || !inWord.contains(DEADLINE_KEYWORD)) {
            return false;
        }

        String[] details = commands[1].split(DEADLINE_KEYWORD, 2);
        String descriptionDetails = details[0].trim();
        String descriptionBy = details[1].trim();
        boolean isNonEmptyDescription = (!descriptionDetails.isEmpty() && !descriptionBy.isEmpty());

        return details.length == 2 && isNonEmptyDescription;
    }

    public static void printDeadline(String inWord, int index, Task[] taskList) {
        String[] commands = inWord.split("\\s+", 2);
        String[] details = commands[1].split(DEADLINE_KEYWORD, 2);

        String description = details[0].trim();
        String by = details[1].trim();

        Deadline newItem = new Deadline(description, by);
        taskList[index] = newItem;
        System.out.println(LINE);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + newItem);
        System.out.println(" Now you have " + (index + 1) +" tasks in the list.");
        System.out.println(LINE);
        System.out.println();
    }

    public static void executeUserInstruction(String inWord, int index, Task[] taskList) {
        String[] instruction = inWord.split("\\s+", 2);
        String instructionType = instruction[0];

        switch(instructionType) {
        case LIST_COMMAND:
            printList(Arrays.copyOf(taskList, index));
            break;
        case DONE_COMMAND:
            if (checkValidDoneInstruction(inWord)) {
                printTaskDone(inWord, index, taskList);
            } else {
                returnException();
            }
            break;
        case EVENT_COMMAND:
            if (checkValidEvent(inWord)) {
                printEvent(inWord, index, taskList);
            } else {
                returnException();
            }
            break;
        case TODO_COMMAND:
            if (checkValidTodo(inWord)) {
                printTodo(inWord, index, taskList);
            } else {
                returnException();
            }
            break;
        case DEADLINE_COMMAND:
            if (checkValidDeadline(inWord)) {
                printDeadline(inWord, index, taskList);
            } else {
                returnException();
            }
            break;
        default:
            returnException();
            break;
        }
    }

    public static int updateIndex(int index, String inWord) {
        String[] instruction = inWord.split("\\s+", 2);
        String instructionType = instruction[0];

        if (instructionType.equals(EVENT_COMMAND) || instructionType.equals(TODO_COMMAND) || instructionType.equals(DEADLINE_COMMAND)) {
            return index + 1;
        }
        return index;
    }

    public static void printDukeExit() {
        System.out.println(LINE);
        System.out.println(GOODBYE_MESSAGE);
        System.out.println(LINE);
    }

    public static void main(String[] args) {
        printDukeGreet();

        String inWord;
        Scanner scan = new Scanner(System.in);
        System.out.println();
        inWord = scan.nextLine();


        int index = 0;
        Task[] taskList = new Task[NUM_OF_TASKS];

        while (!inWord.equalsIgnoreCase(EXIT_STRING)) {
            executeUserInstruction(inWord, index, taskList);
            index = updateIndex(index, inWord);
            inWord = scan.nextLine();
        }

        //Exits when user types "bye"
        printDukeExit();
    }
}