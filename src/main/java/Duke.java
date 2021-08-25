import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void main(String[] args) {
        String line;
        String[] taskList = new String[100];
        Scanner in = new Scanner(System.in);
        int taskCount = 0;

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        line = in.nextLine();
        while (!line.equals("bye")) {
            if(line.equals("list")) {
                String[] printList = Arrays.copyOf(taskList, taskCount);
                int i = 1;
                for(String task: printList) {
                    System.out.println(i + ". " + task);
                    i += 1;
                }
            } else {
                System.out.println("added: " + line);
                taskList[taskCount] = line;
                taskCount += 1;
            }
            line = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
