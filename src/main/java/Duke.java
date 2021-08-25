import java.util.Scanner;

public class Duke {
    static String horizontal = " __________________________________________________\n";
    static String introduction = horizontal + "   Hello! I'm Duke\n" + "   What can I do for you?\n" + horizontal;
    static String farewell = horizontal + "   Bye. Hope to see you again soon!\n" + horizontal;
    static String[] taskList = new String[100];
    static int taskCount = 0;

    public static void echoCommandList(String[] tasklist) {
        System.out.println(horizontal);
        for (int i = 0; i < taskCount; i++) {
            System.out.println("    " + (i+1) + "." + taskList[i]);
        }
        System.out.println(horizontal);
    }

    public static void echoCommandAdded(String line) {
        System.out.println(horizontal + "    added: " + line + "\n" + horizontal);
    }

    public static void main(String[] args) {
        System.out.println(introduction);

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                echoCommandList(taskList);
            }
            echoCommandAdded(line);
            taskList[taskCount] = line;
            taskCount++;
            line = in.nextLine();
        }
        System.out.println(farewell);
    }
}
