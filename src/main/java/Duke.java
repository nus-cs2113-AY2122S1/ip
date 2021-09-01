import java.util.Scanner;

public class Duke {
    static String horizontal = " __________________________________________________\n";
    static String introduction = horizontal + "   Hello! I'm Duke\n" + "   What can I do for you?\n" + horizontal;
    static String farewell = horizontal + "   Bye. Hope to see you again soon!\n" + horizontal;
    static Task[] taskList = new Task[100];
    static int taskCount = 0;
    static int doneIndex = 0;

    public static void echoCommandList(Task[] tasklist) {
        System.out.println(horizontal + "Here are the tasks in your list:\n");
        for (int i = 0; i < taskCount; i++) {
            System.out.println("    " + (i+1) + ". [" + taskList[i].getTaskType() + "]" + "[" + taskList[i].getStatusIcon() + "] " + taskList[i].getDescription());
        }
        System.out.println(horizontal);
    }

    public static void echoCommandDone(String line) {
        System.out.println(horizontal + "Nice! I\'ve marked this task as done:\n" + "   [" + taskList[doneIndex-1].getTaskType() + "][X] " + line + "\n" + horizontal);
    }


    public static void main(String[] args) {
        System.out.println(introduction);

        String line;
        String[] splicedLine;
        String commandWord;

        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        String task;
        String taskDate;

        while (!line.equals("bye")) {

            splicedLine = line.split(" ");
            commandWord = splicedLine[0];

            if (line.equals("list")) {
                echoCommandList(taskList);
            }
            else if (commandWord.equals("done")) {
                doneIndex = Integer.parseInt(splicedLine[1]);
                echoCommandDone(taskList[doneIndex-1].getDescription());
                taskList[doneIndex-1].markAsDone();
            }
            else if (commandWord.equals("todo")) {
                task = line.substring(line.indexOf(' '));
                taskList[taskCount] = new Todo(task);
                System.out.println(horizontal + taskList[taskCount].toString() + "Now you have " + ++taskCount + " tasks on the list.\n" + horizontal);
            }
            else if (commandWord.equals("deadline")) {
                task = line.substring(line.indexOf(' ')+1, line.indexOf('/')-1);
                taskDate = line.substring(line.indexOf('/')+4);
                taskList[taskCount] = new Deadline(task, taskDate);
                System.out.println(horizontal + taskList[taskCount].toString() + "Now you have " + ++taskCount + " tasks on the list.\n" + horizontal);
            }
            else if (commandWord.equals("event")) {
                task = line.substring(line.indexOf(' ')+1, line.indexOf('/')-1);
                taskDate = line.substring(line.indexOf('/')+4);
                taskList[taskCount] = new Event(task, taskDate);
                System.out.println(horizontal + taskList[taskCount].toString() + "Now you have " + ++taskCount + " tasks on the list.\n" + horizontal);
            }
            else {
                System.out.println("Please indicate whether your task is a Todo, Deadline, or Event!");
            }
            line = in.nextLine();
        }
        System.out.println(farewell);
    }
}
