import java.util.Arrays;
import java.util.Scanner;

public class Greet {
    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        int ntasks = 0;
        System.out.println("Hello! I'm Duke");
        String line;
        Scanner in = new Scanner(System.in);
        System.out.println("What can I do for you?");
        line = in.nextLine();
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                for (int i = 0; i < ntasks; i += 1) {
                    System.out.println((i + 1) + ".[" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
                }
            }
            else if (line.contains("done")) {
                String[] splitTask = line.split(" ");
                int index = Integer.parseInt(splitTask[1]) - 1;
                tasks[index].setDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[" + tasks[index].getStatusIcon() + "] " + tasks[index].getDescription());
            }
            else {
                tasks[ntasks] = new Task(line);
                ntasks += 1;
                System.out.println("added: " + line);
            }
            line = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}