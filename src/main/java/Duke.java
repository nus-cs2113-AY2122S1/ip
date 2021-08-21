import java.awt.*;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String divider = "    ——————————————————————————————————————————————————————————————";
        System.out.println(divider);
        System.out.println("    Hello! I'm Duke\n    What can I do for you?");
        System.out.println(divider);
        String line;
        String[] lists = new String[100];
        boolean[] isDones = new boolean[100];
        for (boolean done : isDones) {
            done = false;
        }
        int number = 0;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                System.out.println(divider);
                System.out.println("    " + "Here are the tasks in your list:");
                for(int i = 0; i < number; i++) {
                    System.out.print("    " + (i + 1) + ". " + "[");
                    if (isDones[i]) {
                        System.out.print("X] ");
                    } else {
                        System.out.print(" ] ");
                    }
                    System.out.println(lists[i]);
                }
                System.out.println(divider);
                line = in.nextLine();
            }  else if (line.contains("done")) {
                int taskDone = Integer.parseInt(line.substring(5));
                System.out.println(divider);
                System.out.println("    Nice! I've marked this task as done:");
                isDones[taskDone - 1] = true;
                System.out.println("      [X] " + lists[taskDone - 1]);
                System.out.println(divider);
                line = in.nextLine();
            } else {
                lists[number++] = line;
                System.out.println(divider);
                System.out.println("    added: " + line);
                System.out.println(divider);
                line = in.nextLine();
            }
        }
        System.out.println(divider);
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println(divider);
    }
}
