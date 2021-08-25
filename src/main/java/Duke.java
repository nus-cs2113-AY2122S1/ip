import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String horizontal_line = "____________________________________________________________";
        System.out.println("Hello from\n" + logo + "\n");
        System.out.println(horizontal_line + "\n");
        System.out.println(" Hello! I'm Duke\n");
        System.out.println(" What can I do for you?\n");
        System.out.println(horizontal_line + "\n");
        String line;
        String[] tasks = new String[100];//fixed size array for now
        int curr_count=0;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            if (!line.equals("list")) {
                tasks[curr_count] = line;
                curr_count += 1;
                System.out.println(horizontal_line + "\n");
                System.out.println("added: " + line + "\n");
                System.out.println(horizontal_line + "\n");
            }
            else {
                System.out.println(horizontal_line + "\n");
                String[] task_list = Arrays.copyOf(tasks, curr_count);
                int count = 1;
                for (String elem : task_list) {
                    System.out.println(count + ". " + elem + "\n");
                    count += 1;
                }
                System.out.println(horizontal_line + "\n");
            }
            line = in.nextLine();
        }
        System.out.println(horizontal_line + "\n");
        System.out.println(" Bye. Hope to see you again soon!\n");
        System.out.println(horizontal_line + "\n");
    }
}
