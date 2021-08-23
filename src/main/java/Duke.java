import java.util.*;
public class Duke {
    private static Task[] taskList;
    private static int taskNum;


    public static void addTask(String task) {

        taskList[taskNum] = new Task(task);
        taskNum += 1;
        Output("added: " + task);

    }

    public static void listTask() {

        String tasks = "";

        for(int i = 0; i < taskNum; i++) {
            tasks = tasks.concat((i + 1) + ". [" +
                    taskList[i].getStatusIcon() + "] " +
                    taskList[i].description + "\n");
        }


        tasks = tasks.substring(0, tasks.length() - 1);

        Output(tasks);

    }

    public static void dispTaskDone(String task) {
        int taskIndex = Integer.parseInt(task.replace("done ", "")) -  1;

        if (taskIndex > taskNum - 1) {
            Output(" The task " + (taskIndex + 1) + " doesn't exist.\nMake sure a valid task number is entered.");
            return;
        }
        Task current = taskList[taskIndex];

        current.markAsDone();
        Output("Nice! I've marked this task as done:\n [" +
                current.getStatusIcon() + "] " + current.description
        );
    }



    public static void Output(String output) {
        String horizontalLine = "____________________________________________________________";
        System.out.println(horizontalLine);
        System.out.println(output);
        System.out.println(horizontalLine);
    }

    public static void main(String[] args) {

        taskList = new Task[100];
        taskNum= 0;

        String userInput;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Output("Hello! I'm Duke\n" + "What can I do for you?");


        Scanner in = new Scanner(System.in);
        userInput = in.nextLine();
        while(!userInput.equals("bye"))
        {
            if(userInput.equals("list")) {
                listTask();
            }
            else if(userInput.startsWith("done")) {
                dispTaskDone(userInput);
            }

            else
                addTask(userInput);

            userInput = in.nextLine();

        }
        Output("Bye. Hope to see you again soon!");
    }
}