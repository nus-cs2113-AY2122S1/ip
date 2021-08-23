import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        String line;
        boolean isBye = false;

        String[] tasks = new String[100];
        int taskCount = 1;

        Scanner in = new Scanner(System.in);
        while (isBye == false) {
            line = in.nextLine();
            if (line.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                isBye = true;
            } else if (line.equalsIgnoreCase("list")) {
                for (int i = 1; i < taskCount; i++) {
                    System.out.println(tasks[i]);
                }
            } else {
                System.out.println("added: " + line);
                tasks[taskCount] = taskCount + ". " + line;
                taskCount++;
            }
        }
    }
}
