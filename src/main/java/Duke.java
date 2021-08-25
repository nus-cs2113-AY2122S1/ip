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
            System.out.println("    " + (i+1) + ".[" + taskList[i].getStatusIcon() + "] " + taskList[i].getDescription() + "\n");
        }
        System.out.println(horizontal);
    }

    public static void echoCommandDone(String line) {
        System.out.println(horizontal + "Nice! I\'ve marked this task as done:\n" + "   [X] " + line + "\n" + horizontal);
    }

    public static void echoCommandAdded(String line) {
        System.out.println(horizontal + "    added: " + line + "\n" + horizontal);
    }

    public static void main(String[] args) {
        System.out.println(introduction);

        String line;
        String[] splicedLine;

        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            splicedLine = line.split(" ");
            if (line.equals("list")) {
                echoCommandList(taskList);
            }
            else if (splicedLine[0].equals("done")) {
                doneIndex = Integer.parseInt(splicedLine[1]);
                echoCommandDone(taskList[doneIndex-1].getDescription());
                taskList[doneIndex-1].markAsDone();
            }
            else {
                echoCommandAdded(line);
                taskList[taskCount] = new Task(line);
                taskCount++;
            }
            line = in.nextLine();
        }
        System.out.println(farewell);
    }
}
