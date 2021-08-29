import java.util.Scanner;

public class Duke {

    public static final String LINE = "____________________________________________________________";
    public static final int MAX = 100;

    public static void main(String[] args) {
        String userCommand; //store user input
        Task[] taskList = new Task[MAX]; //store all the task from user input
        int listSize = 0;

        printIntroduction();

        Scanner in = new Scanner(System.in);
        userCommand = in.nextLine(); //user input

        while (!userCommand.equals("bye")) { //exit command is bye
            if (userCommand.equals("list")) { //prints list of task
                printList(taskList, listSize);
            }
            else if (userCommand.contains("done ")) { //check user input to see if task completed
                printCompletedTask(userCommand, taskList);
            }
            else {
                addTask(userCommand, taskList, listSize);
                printAddedTask(taskList[listSize], listSize);
                listSize++; //updates the size of list
            }
            userCommand = in.nextLine();
        }
        printBye();
    }

    private static void printIntroduction() {
        String logo = " ______    __                     __\n" +
                        "/  ____|  |  |                   |__|\n" +
                        "|  |      |  |_____    _______    __\n" +
                        "|  |      |   __   \\  /  __   |  |  |\n" +
                        "|  |____  |  |  |  |  | |__|  |  |  |\n" +
                        "\\______|  |__|  |__|  \\____/\\_|  |__|\n" ;

        System.out.print(logo);
        System.out.println(LINE);
        System.out.println("Hey there! I am Chai!\n" + "What are you doing today?");
        System.out.println(LINE);
    }

    private static void printList(Task[] taskList, int listSize) {
        System.out.println(LINE);
        System.out.println("Tasks list so far:");
        for (int i = 0; i < listSize; i++) {
            System.out.print(taskList[i].getTaskNumber());
            System.out.println('.' + taskList[i].toString());
        }
        System.out.println(LINE);
    }

    private static void printCompletedTask(String userCommand, Task[] taskList) {
        int taskNumberToMark;
        taskNumberToMark = Integer.parseInt(userCommand.substring(5));
        taskList[taskNumberToMark - 1].markDone();
        System.out.println(LINE);
        System.out.println("Well done! Task marked:");
        System.out.println(" " + taskList[taskNumberToMark - 1].toString());
        System.out.println(LINE);
    }

    private static void addTask(String userCommand, Task[] taskList, int listSize) {
        if (userCommand.contains("todo")) {
            taskList[listSize] = new ToDos(userCommand.substring(5));
        }
        else if (userCommand.contains("deadline")) {
            taskList[listSize] = new Deadlines(userCommand.substring(9, userCommand.indexOf('/') - 1), userCommand.substring(userCommand.indexOf("/by") + 4));
        }
        else if (userCommand.contains("event")) {
            taskList[listSize] = new Events(userCommand.substring(6, userCommand.indexOf('/') - 1), userCommand.substring(userCommand.indexOf("/at") + 4));
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

    private static void printBye() {
        System.out.println(LINE);
        System.out.println("GoodBye! Please finish up your task!");
        System.out.println(LINE);
    }
}
