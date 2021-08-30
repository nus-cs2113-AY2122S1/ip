import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");
        System.out.println("_______________________________________\n");
        inputTask();
    }

    public static void inputTask() {
        Scanner in = new Scanner(System.in);
        String inputTask = in.nextLine();
        String[] tasks = new String[100];
        int count = 0;
        boolean isTrue = true;

        while(!inputTask.equals("bye")) {
            if (inputTask.equals("list")) {
                System.out.println("_______________________________________\n");
                System.out.println("To do list:\n");
                for (int i = 0; i < count; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
            } else {
                System.out.println("_______________________________________\n");
                System.out.println("added: " + inputTask);
                tasks[count] = inputTask;
                count++;
            }
            inputTask = in.nextLine();
        }
        if (inputTask.equals("bye")) {
            System.out.println("_______________________________________\n");
            System.out.println("Bye. Hope to see you again soon!\n");
        }
        in.close();
    }
}