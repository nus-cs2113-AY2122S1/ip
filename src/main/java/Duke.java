import java.util.Scanner;
import java.util.Arrays;

public class Duke {

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        String[] list = new String[100];
        int listCount = 0;


        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        while(!line.equals("bye")) {
            if (line.equals("list")) {
                int taskCount = 1;
                for (int i = 0; i < listCount; i++) {
                    System.out.println(taskCount + ". " + list[i]);
                    taskCount++;
                }
                line = in.nextLine();
            }
            else {
                System.out.println("added: " + line);
                list[listCount] = line;
                listCount++;
                line = in.nextLine();
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
