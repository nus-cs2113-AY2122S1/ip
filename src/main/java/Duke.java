import java.util.Scanner;


public class Duke {
    private static Task[] tasks;
    private static int taskCount;

    //prints a string within two horizontal lines, @param is string to be printed
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
        tasks[taskCount] = new Task(task);
        taskCount ++;
        printWithLines("added: " + task);
    }

    public static void listTasks() {
        String taskList = "";
        for(int i = 0; i < taskCount; i++) {
            taskList = taskList.concat((i + 1) + ". " +
                    tasks[i].getStatusIcon() + " " + tasks[i].description + "\n");
        }
        //erase last newline character
        taskList = taskList.substring(0, taskList.length() - 1);

        printWithLines(taskList);
    }

    public static void setTaskDone(String input) {
        int taskIndexNumber = Integer.parseInt(input.replace("done ", "")) - 1;
        if (taskIndexNumber > taskCount - 1) {
            printWithLines("Task number " + (taskIndexNumber + 1) + " does not exist!\nEnter a valid task number.");
            return;
        }
        Task chosenTask = tasks[taskIndexNumber];
        chosenTask.setDone();
        printWithLines("Task has been marked as done:\n" + chosenTask.getStatusIcon() + " " + chosenTask.description);
    }


    public static void main(String[] args) {
        tasks = new Task[100];
        taskCount = 0;

        printHelloMessage();

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        while (!line.equals("bye")) {
            //display list of tasks
            if (line.equals("list")) {
                listTasks();

              //mark task as done
            } else if (line.startsWith("done")) {
                setTaskDone(line);
              //add task
            } else {
                addTask(line);
            }

            //inputs next command entered
            line = in.nextLine();
        }
        printByeMessage();
    }
}