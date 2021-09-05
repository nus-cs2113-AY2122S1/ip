import java.util.Scanner;

public class Duke {

    public static final String LINE = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    public static final int MAX = 100;
    public static final String GOODBYE_MESSAGE = "GoodBye! Please finish up your task!";
    public static final String HELLO_MESSAGE = "Hey there! I am Chai!\n" + "What are you doing today?";
    public static final String UNKNOWN_COMMAND = "Error404!!! I don't understand what that is, please try again!";
    public static final String LOGO = " ______    __                     __\n" +
            "/  ____|  |  |                   |__|\n" +
            "|  |      |  |_____    _______    __\n" +
            "|  |      |   __   \\  /  __   |  |  |\n" +
            "|  |____  |  |  |  |  | |__|  |  |  |\n" +
            "\\______|  |__|  |__|  \\____/\\_|  |__|\n";

    public static void main(String[] args) {
        String userCommand; //store user input
        Task[] taskList = new Task[MAX]; //store all the task from user input
        int listSize = 0;

        printIntroduction();

        Scanner in = new Scanner(System.in);
        userCommand = in.nextLine(); //user input

        while (!userCommand.equalsIgnoreCase("bye")) { //exit command is bye
            if (userCommand.equalsIgnoreCase("list")) { //prints list of task
                printList(taskList, listSize);
            }
            else if (userCommand.toLowerCase().contains("done ")) { //check user input to see if task completed
                markCompletedTask(userCommand, taskList);
            }
            else if (isValid(userCommand.toLowerCase())){
                String[] taskFromCommand = userCommand.split(" ", 2);
                try {
                    if (taskFromCommand.length <= 1) {
                        throw new DukeException();
                    }
                    addTask(userCommand.toLowerCase(), taskList, listSize);
                    printAddedTask(taskList[listSize], listSize);
                    listSize++; //updates the size of list
                }
                catch (DukeException e) {
                    printEmptyTaskMessage(taskFromCommand[0]);
                }
            }
            else {
                printUnknownCommandMessage();
            }
            userCommand = in.nextLine();
        }
        printBye();
    }

    private static void printIntroduction() {
        System.out.println(LINE);
        System.out.print(LOGO);
        System.out.println(LINE);
        System.out.println(HELLO_MESSAGE);
        System.out.println(LINE);
    }

    private static void printList(Task[] taskList, int listSize) {
        System.out.println(LINE);
        System.out.println("Tasks list so far:");
        for (int i = 0; i < listSize; i++) {
            System.out.print(taskList[i].getTaskNumber());
            System.out.println("." + taskList[i]);
        }
        System.out.println(LINE);
    }

    private static void markCompletedTask(String userCommand, Task[] taskList) {
        String[] taskToMark = userCommand.split(" ");
        int taskNumberToMark;
        try {
            if (taskToMark.length <= 1) {
                throw new DukeException();
            }
            else {
                for (int i = 1; i <taskToMark.length; i++) {
                    taskNumberToMark = Integer.parseInt(taskToMark[i]);
                    taskList[taskNumberToMark - 1].markDone();
                    printCompletedTaskMessage(taskList[taskNumberToMark - 1]);
                }
            }
        }
        catch (DukeException e) {
            System.out.println(LINE);
            System.out.println("Please input a task number!");
            System.out.println(LINE);
        }
        catch (NumberFormatException e) {
            System.out.println(LINE);
            System.out.println("Please input a valid number!");
            System.out.println(LINE);
        }
        catch (RuntimeException e) {
            System.out.println(LINE);
            System.out.println("Task number not found!");
            System.out.println(LINE);
        }
    }

    private static void printCompletedTaskMessage(Task task) {
        System.out.println(LINE);
        System.out.println("Well done! Task marked:");
        System.out.println(" " + task);
        System.out.println(LINE);
    }

    private static boolean isValid(String userCommand) {
        return userCommand.contains("todo") || userCommand.contains("deadline") || userCommand.contains("event");
    }

    private static void addTask(String userCommand, Task[] taskList, int listSize) {
        String[] splitTaskString = userCommand.split(" ", 2);
        String[] taskNameAndDueDate;
        switch (splitTaskString[0]) {
        case "todo":
            taskList[listSize] = new ToDos(splitTaskString[1]);
            break;
        case "deadline":
            taskNameAndDueDate = splitTaskString[1].split("/by ", 2);
            taskList[listSize] = new Deadlines(taskNameAndDueDate[0], taskNameAndDueDate[1]);
            break;
        case "event":
            taskNameAndDueDate = splitTaskString[1].split("/at ", 2);
            taskList[listSize] = new Deadlines(taskNameAndDueDate[0], taskNameAndDueDate[1]);
            break;
        }
    }

    private static void printAddedTask(Task task, int listSize) {
        System.out.println(LINE);
        System.out.println("Alright! Added to the list:");
        System.out.println(" " + task);
        task.setTaskNumber(listSize + 1); //update the task number
        System.out.print("You currently have ");
        System.out.print(listSize + 1);
        System.out.println(" task recorded in your list.");
        System.out.println(LINE);
    }

    private static void printEmptyTaskMessage(String s) {
        System.out.println(LINE);
        System.out.println("Error404!!! " + s + " task description should not be empty!");
        System.out.println(LINE);
    }

    private static void printUnknownCommandMessage() {
        System.out.println(LINE);
        System.out.println(UNKNOWN_COMMAND);
        System.out.println(LINE);
    }

    private static void printBye() {
        System.out.println(LINE);
        System.out.println(GOODBYE_MESSAGE);
        System.out.println(LINE);
    }
}