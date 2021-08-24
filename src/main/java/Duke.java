import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        //VISUALS//
        String horizontalLine = "____________________________________________________________\n";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greeting = horizontalLine
                + " Hello! I'm Duke\n"
                + " What can I do for you?\n"
                + horizontalLine;
        String farewell = horizontalLine
                + " Bye. Hope to see you again soon!\n"
                + horizontalLine;

        //MAIN//
        System.out.println("Hello from\n" + logo);
        System.out.println(greeting);

        Task[] taskList = new Task[100];
        int taskListSize = 0;
        String command = "start";
        Scanner in = new Scanner(System.in);

        while (!command.equals("bye")) {
            command = in.nextLine(); //scans user input
            if (command.equals("list")) { //prints out task list
                System.out.println(horizontalLine
                        + " Here are the tasks in your list:");
                for (int i = 0; i < taskListSize; i++) {
                    int j = i + 1;
                    String status = taskList[i].getStatusIcon();
                    System.out.println(" " + j + ".[" + status + "] " + taskList[i].description);
                }
                System.out.println(horizontalLine);
            } else if (command.contains("done")) { //marks task as 'done'
                String[] words = command.split(" ");
                int taskNumber = Integer.parseInt(words[1]) - 1;
                if (taskNumber > taskListSize - 1) {
                    System.out.println(horizontalLine
                            + " Sorry, invalid task number.\n"
                            + horizontalLine);
                } else {
                    System.out.println(horizontalLine
                            + " Nice! I've marked this task as done: ");
                    taskList[taskNumber].setDone();
                    System.out.println("   [" + taskList[taskNumber].getStatusIcon() + "] " + taskList[taskNumber].description + "\n"
                            + horizontalLine);
                }
            } else { //adds command to taskList
                taskList[taskListSize] = new Task(command);
                taskListSize++;
                String added = horizontalLine
                        + " added: " + command + "\n"
                        + horizontalLine;
                System.out.println(added);
            }
        }

        System.out.println(farewell);
    }
}
