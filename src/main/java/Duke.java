import java.util.Scanner;

public class Duke {
    private static String[] tasks;
    private static int taskCount;

    public static void printWithLines(String text) {
        String line = "____________________________________________________________";

        System.out.println(line);
        System.out.println(text);
        System.out.println(line);
    }

    public static void printHelloMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String helloMessage = "Hello! I'm Duke\n" +
                "What can I do for you?";

        System.out.println("Hello from\n" + logo);

        printWithLines(helloMessage);
    }

    public static void printByeMessage() {
        String byeMessage = "Bye. Hope to see you again soon!";
        printWithLines(byeMessage);
    }

    public static void addTask(String task) {
        tasks[taskCount] = task;
        taskCount ++;
        printWithLines("added: " + task);
    }

    public static void listTasks() {
        String taskList = "";
        for(int i = 0; i < taskCount; i++) {
            taskList = taskList.concat((i + 1) + ". " + tasks[i] + "\n");
        }

        taskList = taskList.substring(0, taskList.length() - 1);
        printWithLines(taskList);
    }

    public static void main(String[] args) {
        tasks = new String[100];
        taskCount = 0;

        printHelloMessage();

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        while (!line.equals("bye")) {
            if (line.equals("list")) {
                listTasks();
            } else {
                addTask(line);
            }
            line = in.nextLine();
        }
        printByeMessage();
    }
}