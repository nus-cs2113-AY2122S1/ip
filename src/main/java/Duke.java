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

    public static void printList(Task[] taskList) {
        System.out.println("____________________________________________________________");
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < taskList.length; i++) {
            System.out.println(" " + (i + 1) + ". " + taskList[i].toString());
        }
        System.out.println("____________________________________________________________");
    }

    public static void printDukeGreet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        //Greeting the User
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
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
        System.out.println("____________________________________________________________");
        System.out.println(" Sorry, your input is invalid! Please enter a valid input :)");
        System.out.println("____________________________________________________________");
    }

    public static boolean checkValidDoneInstruction(String inWord) {
        String[] commands = inWord.split(" ");
        if (commands.length != 2 || !isNumeric(commands[1])) {
            return false;
        }
        return true;
    }

    public static void printTaskDone(String inWord, int index, Task[] taskList) {
        String[] commands = inWord.split(" ");
        int taskDoneIndex = Integer.parseInt(commands[1]);
        if (taskDoneIndex <= 0 || taskDoneIndex > index) {
            System.out.println("____________________________________________________________");
            System.out.println("Item out of Index! Please input a valid task number :)");
            System.out.println("____________________________________________________________");
        } else {
            taskList[taskDoneIndex - 1].markAsDone();
            System.out.println("____________________________________________________________");
            System.out.println(" Nice! I've marked this task as done:");
            System.out.println("   " + taskList[taskDoneIndex - 1].toString());
            System.out.println("____________________________________________________________");
        }
    }

    public static boolean checkValidEvent(String inWord) {
        String[] commands = inWord.split("\\s+", 2);
        if (commands.length != 2 || !inWord.contains(" /at")) {
            return false;
        }

        String[] details = commands[1].split("/at", 2);
        String descriptionDetails = details[0].trim();
        String descriptionAt = details[1].trim();

        if (details.length != 2 || descriptionDetails.isEmpty() || descriptionAt.isEmpty()) {
            return false;
        }
        return true;
    }

    public static void printEvent(String inWord, int index, Task[] taskList) {
        String[] commands = inWord.split("\\s+", 2);
        String[] details = commands[1].split("/at", 2);

        String description = details[0].trim();
        String at = details[1].trim();

        Event newItem = new Event(description, at);
        taskList[index] = newItem;
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + newItem);
        System.out.println(" Now you have " + (index + 1) +" tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public static boolean checkValidTodo(String inWord) {
        String[] commands = inWord.split("\\s+", 2);
        String details = commands[1];
        if (commands.length != 2 || details.isEmpty()) {
            return false;
        }
        return true;
    }

    public static void printTodo(String inWord, int index, Task[] taskList) {
        String[] commands = inWord.split("\\s+", 2);
        String description = commands[1];

        Todo newItem = new Todo(description);
        taskList[index] = newItem;
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + newItem);
        System.out.println(" Now you have " + (index + 1) +" tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public static boolean checkValidDeadline(String inWord) {
        String[] commands = inWord.split("\\s+", 2);
        if (commands.length != 2 || !inWord.contains(" /by")) {
            return false;
        }

        String[] details = commands[1].split("/by", 2);
        String descriptionDetails = details[0].trim();
        String descriptionDeadline = details[1].trim();

        if(details.length != 2 || descriptionDetails.isEmpty() || descriptionDeadline.isEmpty()) {
            return false;
        }
        return true;
    }

    public static void printDeadline(String inWord, int index, Task[] taskList) {
        String[] commands = inWord.split("\\s+", 2);
        String[] details = commands[1].split("/by", 2);

        String description = details[0].trim();
        String by = details[1].trim();

        Deadline newItem = new Deadline(description, by);
        taskList[index] = newItem;
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + newItem);
        System.out.println(" Now you have " + (index + 1) +" tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public static void printDukeExit() {
        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }


    public static void main(String[] args) {
        printDukeGreet();

        String inWord;
        Scanner scan = new Scanner(System.in);
        System.out.println();
        inWord = scan.nextLine();


        Task[] taskList = new Task[100];
        int index = 0;
        String[] instruction = inWord.split("\\s+", 2);
        String instructionType = instruction[0];

        while (!inWord.toLowerCase().equals(EXIT_STRING)) {
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
                    index++;
                } else {
                    returnException();
                }
                break;
            case TODO_COMMAND:
                if (checkValidTodo(inWord)) {
                    printTodo(inWord, index, taskList);
                    index++;
                } else {
                    returnException();
                }
                break;
            case DEADLINE_COMMAND:
                if (checkValidDeadline(inWord)) {
                    printDeadline(inWord, index, taskList);
                    index++;
                } else {
                    returnException();
                }
                break;
            default:
                returnException();
                break;
            }
            inWord = scan.nextLine();
            instruction = inWord.split("\\s+", 2);
            instructionType = instruction[0];
        }

        //Exits when user types "bye"
        printDukeExit();
    }
}