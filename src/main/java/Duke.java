import java.util.Scanner;
import java.util.Arrays;

public class Duke {

    public static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String LINE = "\t____________________________________________________________\n\t";
    public static final String BYE = LINE
            + "Bye. try not to procrastinate!\n"
            + LINE;
    public static final String GREETING = LINE
            + "Hello! I'm Anderson\n\t"
            + "What do you need to do?\n"
            + LINE;

    public static Task[] FilterNulls(Task[] tasks) {
        Task[] isFilteredNull = new Task[100];
        int count = 0;
        for (int i = 0; i < 100; i++) {
            if (tasks[i] != null) {
                isFilteredNull[count] = tasks[i];
                count++;
            }
        }
        return Arrays.copyOf(isFilteredNull, count);
    }

    public static String getCommand(String userInput) {
        if (isEmpty(userInput)) {
            return "again";
        }
        if (userInput.length() > 2) {
            String[] isolateCommand = userInput.split(" ");
            return isolateCommand[0];
        }
        return userInput;
    }

    public static String GetItem(String userInput) {
        String item = "";
        String command = getCommand(userInput);
        if (isDeadline(command)) {
            item = notToDoItem(userInput);
        } else if (isEvent(command)) {
            item = notToDoItem(userInput);
        } else if (userInput.length() > 3) {
            item = getRequiredSubstring(userInput, " ", 1);
        }
        return item;
    }

    private static String getRequiredSubstring(String userInput, String s, int i) {
        return userInput.substring(userInput.indexOf(s) + i);
    }

    private static String notToDoItem(String userInput) {
        return userInput.substring(userInput.indexOf(" ") + 1, userInput.indexOf("/"));
    }

    public static String getTime(String userInput) {
        String time = "";
        String command = getCommand(userInput);
        if (isDeadline(command)) {
            time = getRequiredSubstring(userInput, "/", 3);
        } else if (isEvent(command)) {
            time = getRequiredSubstring(userInput, "/", 3);
        } else {
            return "";
        }
        return time;
    }

    public static void main(String[] args) {

        Task[] unfilteredTasks = new Task[100];
        int unfilteredCounter = 0;

        printGreetings();

        String userInput;
        Scanner in = new Scanner(System.in);
        userInput = in.nextLine();
        boolean closeDuke = false;

        do {
            String command = getCommand(userInput);
            doDone(command, unfilteredTasks, userInput);
            doList(command, unfilteredTasks);
            unfilteredTasks[unfilteredCounter] = isToDo(command) ? new ToDo((GetItem(userInput))) : isDeadline(command) ? new Deadline(GetItem(userInput), getTime(userInput)) : isEvent(command) ? new Event(GetItem(userInput), getTime(userInput)) : null;

            AcknowledgeAddition(command, unfilteredTasks[unfilteredCounter], unfilteredCounter);

            unfilteredCounter = isInvalidCommand(command) ? unfilteredCounter : isList(command) ? unfilteredCounter : unfilteredCounter + 1;
            if (!isBye(command)) {
                userInput = in.nextLine();
            }
            doBye(command);
            closeDuke = isBye(command);
        } while (!closeDuke);
    }

    private static void printGreetings() {
        System.out.println("\tHello from\n" + LOGO);
        System.out.println(GREETING);
    }

    private static boolean isToDo(String command) {
        return command.toLowerCase().equals("todo");
    }

    private static boolean isBye(String command) {
        return command.toLowerCase().equals("bye");
    }

    private static boolean isDeadline(String command) {
        return command.toLowerCase().equals("deadline");
    }

    private static boolean isEvent(String command) {
        return command.toLowerCase().equals("event");
    }

    private static boolean isList(String userInput) {
        return userInput.toLowerCase().equals("list");
    }

    private static boolean isDone(String command) {
        return command.toLowerCase().equals("done");
    }

    private static boolean isEmpty(String userInput) {
        return userInput.equals("\n");
    }

    private static boolean isInvalidCommand(String command) {
        return isEvent(command) ? false : isDeadline(command) ? false : isToDo(command) ? false : isList(command) ? false : isBye(command) ? false : true;
    }

    private static void doDone(String command, Task[] fullTaskList, String userIn) {
        if (isDone(command)) {
            markedDoneMessage(fullTaskList, userIn);
        }
    }

    private static void doList(String command, Task[] fullTaskList) {
        Task[] filteredNull = FilterNulls(fullTaskList);
        int count = 0;
        if (isList(command)) {
            if (filteredNull[0] == null) {
                System.out.println(LINE + "\tYou had no task to begin with\n" + LINE);
            } else {
                System.out.println(LINE + "Here are the tasks in your list:\n");
                for (Task task : filteredNull) {
                    count++;
                    System.out.println("\t" + count + "." + task);
                }
                System.out.println(LINE);
            }
        }
    }

    private static void doBye(String command) {
        if (isBye(command)) {
            System.out.println(BYE);
        }
    }

    private static void markedDoneMessage(Task[] unfilteredTasks, String userInput) {
        System.out.println(LINE + "Nice! I've marked this task as done:");
        int taskNumber = Integer.parseInt(GetItem(userInput));
        Task completedTask = unfilteredTasks[(taskNumber - 1)];
        completedTask.markAsDone();
        System.out.println("\t\t" + completedTask + "\n" + LINE);
    }

    private static void AcknowledgeAddition(String command, Task unfilteredTask, int unfilteredCounter) {
        if (!isInvalidCommand(command) && !isList(command) && !isBye(command)) {
            System.out.println(LINE + "Got it. I've added this task:\t");
            System.out.println(String.format("\t%d.", unfilteredCounter + 1) + unfilteredTask + "\n" + String.format("\tNow you have %d tasks in the list.\n", unfilteredCounter + 1) + LINE);
        }
    }
    
    private static void InvalidMessage() {
        System.out.println(LINE + "\tInvalid input, Please Try Again :)\n" + LINE);
    }
}
