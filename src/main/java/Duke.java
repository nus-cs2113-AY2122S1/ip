import java.util.Scanner;

public class Duke {
    public static final String MESSAGE = "Here are the tasks in your list:";
    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static String line = "___________________________________________________";

    public static void main(String[] args) {
        printWelcome(logo, line);
        String[] arrayInput = new String[100];
        int[] taskStatus = new int[100];
        initiateStatus(taskStatus);
        checkCommand(taskStatus, arrayInput);
    }

    private static void initiateStatus(int[] taskStatus) {
        for (int i = 0; i < 100; i++) {
            taskStatus[i] = 0;
        }
    }

    public static void printWelcome(String logo, String line) {
        System.out.println("Hello from\n" + logo);
        System.out.println(line);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(line);
    }
    public static void checkTodo(String userCommand) throws DukeException {
        if(userCommand.length() <= 5) {     //generate error when receiving invalid Todoinput
            DukeException e = new DukeException();
            throw e;
        }

    }

    public static void checkDeadline(String userCommand) throws DukeException {
        if(userCommand.length() <= 9) {     //generate error when receiving invalid Deadlineinput
            DukeException e = new DukeException();
            throw e;
        }
    }

    public static void checkEvent(String userCommand) throws DukeException {
        if(userCommand.length() <= 6) {     //generate error when receiving invalid Eventinput
            DukeException e = new DukeException();
            throw e;
        }
    }

    public static void checkCommand(int[] taskStatus, String[] arrayInput) {
        Scanner userInput = new Scanner(System.in);
        String userCommand = userInput.nextLine();
        String[] taskType = new String[100];
        int taskIDInt;
        int inputCount = 0;
        char taskID;
        while (!userCommand.equals("bye")) {
            if (userCommand.equals("list")) {
                printList(taskType, taskStatus, arrayInput, userInput, inputCount);
                userCommand = userInput.nextLine();
                continue;
            } else if (userCommand.contains("done")) {
                printDone(taskStatus, arrayInput, userCommand);
                userCommand = userInput.nextLine();
                continue;
            } else if (userCommand.contains("todo")) {
                try{
                    checkTodo(userCommand);
                } catch (DukeException e) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    System.out.println(line);
                    userCommand = userInput.nextLine();
                    continue;
                }
                inputCount = printTodo(arrayInput, userCommand, inputCount);
                taskType[inputCount - 1] = "T";
                userCommand = userInput.nextLine();
                continue;
            } else if (userCommand.contains("deadline")) {
                try{
                    checkDeadline(userCommand);
                } catch(DukeException e) {
                    System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                    System.out.println(line);
                    userCommand = userInput.nextLine();
                    continue;
                }
                inputCount = printDeadline(arrayInput, userCommand, inputCount);
                taskType[inputCount - 1] = "D";
                userCommand = userInput.nextLine();
                continue;
            } else if (userCommand.contains("event")) {
                try{
                    checkEvent(userCommand);
                } catch(DukeException e) {
                    System.out.println("☹ OOPS!!! The description of a event cannot be empty.");
                    System.out.println(line);
                    userCommand = userInput.nextLine();
                    continue;
                }
                inputCount = printEvent(arrayInput, userCommand, inputCount);
                taskType[inputCount - 1] = "E";
                userCommand = userInput.nextLine();
                continue;
            } else if(!userCommand.equals("bye")) {
                printInvalid();
                System.out.println(line);
                userCommand = userInput.nextLine();
                continue;

            }
            inputCount = printUpdate(arrayInput, userCommand, inputCount);
            userCommand = userInput.nextLine();
        }

        if (userCommand.equals("bye")) {
            printBye();
        }
    }

    public static void printInvalid() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    private static int printEvent(String[] arrayInput, String userCommand, int inputCount) {
        String at;
        int index = userCommand.indexOf("-");
        at = userCommand.substring(index - 5);
        Event event = new Event(userCommand, at, index);
        inputCount++;
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println(event);
        System.out.println("Now you have " + inputCount + " tasks in the list.");
        System.out.println(line);
        String taskName = event.description.substring(6, index - 10);
        String eventName = taskName + " (at: " + at + ")";
        arrayInput[inputCount - 1] = eventName;
        return inputCount;
    }

    private static int printDeadline(String[] arrayInput, String userCommand, int inputCount) {
        String by;
        int index = userCommand.indexOf("by");
        by = userCommand.substring(index + 3);
        Deadline deadline = new Deadline(userCommand, by, index);
        inputCount++;
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println(deadline);
        System.out.println("Now you have " + inputCount + " tasks in the list.");
        System.out.println(line);
        String taskName = deadline.description.substring(9, index - 2);
        String deadlineName = taskName + " (by: " + by + ")";
        arrayInput[inputCount - 1] = deadlineName;
        return inputCount;
    }

    private static int printTodo(String[] arrayInput, String userCommand, int inputCount) {
        Todo todo = new Todo(userCommand);
        inputCount++;
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println(todo);
        System.out.println("Now you have " + inputCount + " tasks in the list.");
        System.out.println(line);
        arrayInput[inputCount - 1] = todo.description.substring(5);
        return inputCount;
    }

    private static int printUpdate(String[] arrayInput, String userCommand, int inputCount) {
        arrayInput[inputCount++] = userCommand;
        System.out.println(line);
        System.out.println("added: " + userCommand);
        System.out.println(line);
        return inputCount;
    }

    private static void printBye() {
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }

    private static void printDone(int[] taskStatus, String[] arrayInput, String userCommand) {
        char taskID;
        int taskIDInt;
        int len = userCommand.length();
        taskID = userCommand.charAt(len - 1);
        taskIDInt = taskID - 49;
        taskStatus[taskIDInt] = 1;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("	" + "[X] " + arrayInput[taskIDInt]);
        System.out.println(line);
    }

    public static void printList(String[] taskType, int[] taskStatus,
                                 String[] arrayInput, Scanner userInput, int inputCount) {
        String userCommand;
        System.out.println(line);
        System.out.println(MESSAGE);

        for (int i = 1; i <= inputCount; i++) {
            if (taskStatus[i - 1] == 1) {
                System.out.println(i + ".[" + taskType[i - 1] + "]" + "[X] " + arrayInput[i - 1]);
            } else {
                System.out.println(i + ".[" + taskType[i - 1] + "]" + "[ ] " + arrayInput[i - 1]);
            }
        }
        System.out.println(line);
    }
}
