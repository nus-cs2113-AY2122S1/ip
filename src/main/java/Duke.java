import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String horizontalLine = "____________________________________________";
        System.out.println(horizontalLine);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
        String[] tasks = new String[100];
        int taskCount = 0;
        while (!userInput.equals("bye")){
            if (userInput.equals("list")){
                String[] taskList = Arrays.copyOf(tasks, taskCount);
                int taskNum = 1;
                System.out.println(horizontalLine);
                for (int i = 0; i < taskCount; i++){
                    System.out.println(taskNum + "." + taskList[i]);
                    taskNum++;
                }
                System.out.println(horizontalLine);
            }
            else {
                tasks[taskCount] = userInput;
                taskCount ++;
                System.out.println(horizontalLine);
                System.out.println("added: " + userInput);
                System.out.println(horizontalLine);
            }
            userInput = in.nextLine();
        }
        System.out.println(horizontalLine);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);
    }
}
