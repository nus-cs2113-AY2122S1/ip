import java.util.*;
public class Duke {
    private static Task[] taskList;
    private static int taskNumber;

    public static void addTask(String task) {
        taskList[taskNumber] = new Task(task);
        taskNumber += 1;
        printWithLines("added: " + task);
    }

    public static void listTask() {
        String tasksAsList = "";

        for(int i = 0; i < taskNumber; i++) {
            tasksAsList = tasksAsList.concat((i + 1) + ". [" +
                    taskList[i].getStatusIcon() + "] " +
                    taskList[i].description + "\n");
        }

        tasksAsList = tasksAsList.substring(0, tasksAsList.length() - 1);
        printWithLines(tasksAsList);
    }

    public static void markTaskDone(String task) {
        int taskIndex = Integer.parseInt(task.replace("done ", "")) -  1;

        if (taskIndex > taskNumber - 1) {
            printWithLines(" The task " + (taskIndex + 1) + " doesn't exist.\nMake sure a valid task number is entered.");
            return;
        }
        Task current = taskList[taskIndex];

        current.markAsDone();
        printWithLines("Nice! I've marked this task as done:\n [" +
                current.getStatusIcon() + "] " + current.description
        );
    }

    public static void printWithLines(String output) {
        String horizontalLine = "____________________________________________________________";
        System.out.println(horizontalLine);
        System.out.println(output);
        System.out.println(horizontalLine);
    }

    public static void main(String[] args) {

        taskList = new Task[100];
        taskNumber= 0;

        String userInput;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printWithLines("Hello! I'm Duke\n" + "What can I do for you?");


        Scanner in = new Scanner(System.in);
        userInput = in.nextLine();
        while(!userInput.equals("bye"))
        {
            if(userInput.equals("list")) {
                listTask();
            }
            else if(userInput.startsWith("done")) {
                markTaskDone(userInput);
            }

            else
                addTask(userInput);

            userInput = in.nextLine();

        }
        printWithLines("Bye. Hope to see you again soon!");
    }
}