import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void main(String[] args) {
        final String DIVIDER = "    ——————————————————————————————————————————————————————————————";
        System.out.println(DIVIDER);
        System.out.println("    Hello! I'm Duke\n    What can I do for you?");
        System.out.println(DIVIDER);
        String line;
        String[] lists = new String[100];
        String[] flags = new String[100];
        String[] doneTasks = new String[100];
        String[] dates = new String[100];
        Arrays.fill(doneTasks," ");
        int number = 0;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                System.out.println(DIVIDER);
                System.out.println("    " + "Here are the tasks in your list:");
                for(int i = 0; i < number; i++) {
                    System.out.print("    " + (i + 1) + ". " + "[" + flags[i] + "]"
                            + "[" + doneTasks[i] + "] " + lists[i]);
                    if(flags[i].equals("D")) {
                        System.out.println(" (by: " + dates[i] + ")");
                    } else if (flags[i].equals("E")) {
                        System.out.println(" (at: " + dates[i] + ")");
                    } else {
                        System.out.println(" ");
                    }
                }
                System.out.println(DIVIDER);
                line = in.nextLine();
            }  else if (line.contains("done")) {
                int taskDone = Integer.parseInt(line.substring(5));
                System.out.println(DIVIDER);
                System.out.println("    Nice! I've marked this task as done:");
                doneTasks[taskDone - 1] = "X";
                System.out.print("      [" + flags[taskDone - 1] + "][X] " + lists[taskDone - 1]);
                if(flags[taskDone - 1].equals("D")) {
                    System.out.println(" (by: " + dates[taskDone - 1] + ")");
                } else if (flags[taskDone - 1].equals("E")) {
                    System.out.println(" (at: " + dates[taskDone - 1] + ")");
                } else {
                    System.out.println(" ");
                }
                System.out.println(DIVIDER);
                line = in.nextLine();
            } else if (line.contains("todo")) {
                String task = line.substring(5);
                flags[number] = "T";
                lists[number++] = task;
                System.out.println(DIVIDER);
                System.out.println("    Got it. I've added this task: ");
                System.out.println("      [T][ ] " + task);
                System.out.println("    Now you have " + number + " tasks in the list");
                System.out.println(DIVIDER);
                line = in.nextLine();
            } else if (line.contains("deadline")) {
                int taskEnd = line.indexOf('/');
                String task = line.substring(9,taskEnd);
                dates[number] = line.substring(taskEnd + 4);
                flags[number] = "D";
                lists[number++] = task;
                System.out.println(DIVIDER);
                System.out.println("    Got it. I've added this task: ");
                System.out.println("      [D][ ] " + task + " (by: " + dates[number - 1] + ")");
                System.out.println("    Now you have " + number + " tasks in the list");
                System.out.println(DIVIDER);
                line = in.nextLine();
            } else if(line.contains("event")) {
                int taskEnd = line.indexOf('/');
                String task = line.substring(6,taskEnd);
                dates[number] = line.substring(taskEnd + 4);
                flags[number] = "E";
                lists[number++] = task;
                System.out.println(DIVIDER);
                System.out.println("    Got it. I've added this task: ");
                System.out.println("      [E][ ] " + task + " (by: " + dates[number - 1] + ")");
                System.out.println("    Now you have " + number + " tasks in the list");
                System.out.println(DIVIDER);
                line = in.nextLine();
            }
        }
        System.out.println(DIVIDER);
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println(DIVIDER);
    }
}
