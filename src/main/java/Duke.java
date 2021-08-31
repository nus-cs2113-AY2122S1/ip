import java.util.Scanner;

public class Duke {

    public static String[] organize(String input) {
        String[] result = new String[4];
        if (!input.contains(" ")) { //list or done
            result[0] = input;
        } else if (input.contains("/")) { //event or deadline
            int temp1 = input.indexOf(" ");
            int temp2 = input.indexOf("/");
            result[0] = input.substring(0, temp1);
            result[1] = input.substring(temp1 + 1, temp2 - 1);
            String helper1 = input.substring(temp2 + 1);
            String[] helper2 = helper1.split(" ");
            result[2] = helper2[0];
            int temp3 = input.substring(temp2 + 1).indexOf(" ");
            result[3] = input.substring(temp2 + 1).substring(temp3);
        } else { //todo
            int temp1 = input.indexOf(" ");
            result[0] = input.substring(0, temp1);
            result[1] = input.substring(temp1 + 1);
        }
        return result;
    }

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
            String[] words = organize(command);
            if (words[0].equals("list")) { //prints out task list
                System.out.println(horizontalLine
                        + " Here are the tasks in your list:");
                for (int i = 0; i < taskListSize; i++) {
                    int j = i + 1;
                    String status = taskList[i].getStatusIcon();
                    String category = taskList[i].getCategory();
                    System.out.println(" " + j + ".[" + category + "][" + status + "] " + taskList[i].getDescription());
                }
                System.out.println(horizontalLine);
            } else if (words[0].equals("done")) { //marks task as 'done'
                int taskNumber = Integer.parseInt(words[1]) - 1;
                if (taskNumber > taskListSize - 1) {
                    System.out.println(horizontalLine
                            + " Sorry, invalid task number.\n"
                            + horizontalLine);
                } else {
                    System.out.println(horizontalLine
                            + " Nice! I've marked this task as done: ");
                    taskList[taskNumber].setDone();
                    System.out.println("   [" + taskList[taskNumber].getStatusIcon() + "] "
                            + taskList[taskNumber].description + "\n"
                            + horizontalLine);
                }
            } else if (words[0].equals("todo")) { //add todo command
                taskList[taskListSize] = new ToDo(command);
                taskListSize++;
                String added = horizontalLine
                        + " Got it. I've added this task: \n"
                        + "  [" + taskList[taskListSize - 1].getCategory() + "][" + taskList[taskListSize - 1].getStatusIcon() + "]"
                        + " " + words[1] + "\n"
                        + " Now you have " + taskListSize + " tasks in the list.\n"
                        + horizontalLine;
                System.out.println(added);
            } else if (words[0].equals("deadline")) { //add deadline command
                taskList[taskListSize] = new Deadline(words[1], words[3]);
                taskListSize++;
                String added = horizontalLine
                        + " Got it. I've added this task: \n"
                        + "  [" + taskList[taskListSize - 1].getCategory() + "][" + taskList[taskListSize - 1].getStatusIcon() + "]"
                        + " " + words[1] + " (" + words[2] + words[3] + ")" + "\n"
                        + " Now you have " + taskListSize + " tasks in the list.\n"
                        + horizontalLine;
                System.out.println(added);
            } else if (words[0].equals("event")) { //add event command
                taskList[taskListSize] = new Event(words[1], words[3]);
                taskListSize++;
                String added = horizontalLine
                        + " Got it. I've added this task: \n"
                        + "  [" + taskList[taskListSize - 1].getCategory() + "][" + taskList[taskListSize - 1].getStatusIcon() + "]"
                        + " " + words[1] + " (" + words[2] + words[3] + ")" + "\n"
                        + " Now you have " + taskListSize + " tasks in the list.\n"
                        + horizontalLine;
                System.out.println(added);
            }
        }

        System.out.println(farewell);
    }
}
