import java.util.*;
public class Duke {
    private static String[] taskList;
    private static int taskNum;

    public static void addTask(String task) {

        taskList[taskNum] = task;
        taskNum += 1;
        Output("added: " + task);

    }

    public static void listTask() {
        String tasks = "";
        for(int i = 0; i < taskNum; i++) {
            tasks = tasks.concat((i + 1) + ". " + taskList[i] + "\n");
        }

        tasks = tasks.substring(0, tasks.length() - 1);
        Output(tasks);
    }

    public static void Output(String output) {
        String horizontalLine = "____________________________________________________________";
        System.out.println(horizontalLine);
        System.out.println(output);
        System.out.println(horizontalLine);
    }

    public static void main(String[] args) {

        taskList = new String[100];
        taskNum= 0;
        boolean notBye = true;
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
        while(notBye)
        {
            if(userInput.equals("bye"))
                break;

            if(userInput.equals("list")) {
                listTask();
            }
            else
                addTask(userInput);

            userInput = in.nextLine();

        }
        Output("Bye. Hope to see you again soon!");
    }
}