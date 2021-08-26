import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        String[] stringList = new String[100];
        Task[] taskList = new Task[100];
        int listCount = 0;

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        while (!line.equals("bye")) {
            if (line.startsWith("done")) {
                String taskNumber = line.substring(5);
                int taskListElement = Integer.parseInt(taskNumber) - 1;
                taskList[taskListElement].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[X] " + taskList[taskListElement].getDescription());
                line = in.nextLine();
            } else if (line.equals("list")) {
                int taskCount = 1;
                for (int i = 0; i < listCount; i++) {
                    System.out.println(taskCount + ". [" + taskList[i].getStatusIcon() + "] " + stringList[i]);
                    taskCount++;
                }
                line = in.nextLine();
            } else {
                System.out.println("added: " + line);
                stringList[listCount] = line;
                taskList[listCount] = new Task(line);
                listCount++;
                line = in.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
