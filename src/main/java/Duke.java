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
        Task[] tasks = new Task[100];
        int taskCount = 0;  //number of tasks, counting from 0
        int taskNum = 1; //(max) numbering of tasks
        String checkMark;
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                System.out.println(horizontalLine);
                System.out.println("Here are the tasks in your list:");
                taskNum = 1;
                for (int i = 0; i < taskCount; i++) {
                    checkMark = tasks[i].getStatusIcon();
                    System.out.println(taskNum + "." + "[" + checkMark + "]" + tasks[i].description);
                    taskNum++;
                }
            } else if (userInput.contains("done")) {
                String[] splitInput = userInput.split(" ");
                int inputNum = Integer.parseInt(splitInput[1]);    //user input done number
                int doneTaskNum = inputNum - 1;  //index of task in array

                if ((inputNum > 0) && (inputNum < taskNum)) {
                    tasks[doneTaskNum].markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    checkMark = tasks[doneTaskNum].getStatusIcon();
                    System.out.println("[" + checkMark + "] " + tasks[doneTaskNum].description);
                } else {
                    System.out.println("Invalid input!");
                }
            } else {
                Task t = new Task(userInput);
                tasks[taskCount] = t;
                taskCount++;
                System.out.println(horizontalLine);
                System.out.println("added: " + userInput);
            }
            System.out.println(horizontalLine);
            userInput = in.nextLine();
        }
        System.out.println(horizontalLine);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);
    }
}
