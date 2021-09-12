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
        String[] taskType = new String[100];
        String[] doneTasks = new String[100];
        String[] dates = new String[100];
        Arrays.fill(doneTasks," ");
        int number = 0;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            if (line.substring(0,4).equals("list")) {
                try {
                    if (line.length() > 4) {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    System.out.println(DIVIDER);
                    System.out.println("    ☹ OOPS!!! The description of list must be empty.");
                    System.out.println(DIVIDER);
                    line = in.nextLine();
                    continue;
                }
                System.out.println(DIVIDER);
                System.out.println("    " + "Here are the tasks in your list:");
                for(int i = 0; i < number; i++) {
                    System.out.print("    " + (i + 1) + ". " + "[" + taskType[i] + "]"
                            + "[" + doneTasks[i] + "] " + lists[i]);
                    if(taskType[i].equals("D")) {
                        System.out.println(" (by: " + dates[i] + ")");
                    } else if (taskType[i].equals("E")) {
                        System.out.println(" (at: " + dates[i] + ")");
                    } else {
                        System.out.println(" ");
                    }
                }
                System.out.println(DIVIDER);
                line = in.nextLine();
            }  else if (line.contains("done")) {
                try {
                    int taskDone = Integer.parseInt(line.substring(5));
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println(DIVIDER);
                    System.out.println("    ☹ OOPS!!! The description of done cannot be empty.");
                    System.out.println(DIVIDER);
                    line = in.nextLine();
                    continue;
                }
                int taskDone = Integer.parseInt(line.substring(5));
                System.out.println(DIVIDER);
                System.out.println("    Nice! I've marked this task as done:");
                doneTasks[taskDone - 1] = "X";
                System.out.print("      [" + taskType[taskDone - 1] + "][X] " + lists[taskDone - 1]);
                if(taskType[taskDone - 1].equals("D")) {
                    System.out.println(" (by: " + dates[taskDone - 1] + ")");
                } else if (taskType[taskDone - 1].equals("E")) {
                    System.out.println(" (at: " + dates[taskDone - 1] + ")");
                } else {
                    System.out.println(" ");
                }
                System.out.println(DIVIDER);
                line = in.nextLine();
            } else if (line.contains("todo")) {
                try {
                    String task = line.substring(5);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println(DIVIDER);
                    System.out.println("    ☹ OOPS!!! The description of a todo cannot be empty.");
                    System.out.println(DIVIDER);
                    line = in.nextLine();
                    continue;
                }
                String task = line.substring(5);
                taskType[number] = "T";
                lists[number++] = task;
                System.out.println(DIVIDER);
                System.out.println("    Got it. I've added this task: ");
                System.out.println("      [T][ ] " + task);
                System.out.println("    Now you have " + number + " tasks in the list");
                System.out.println(DIVIDER);
                line = in.nextLine();
            } else if (line.contains("deadline")) {
                try {
                    if (!line.contains("/by")) {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    System.out.println(DIVIDER);
                    System.out.println("    ☹ OOPS!!! The description of a deadline must have '/by' to indicate time.");
                    System.out.println(DIVIDER);
                    line = in.nextLine();
                    continue;
                }
                int taskEnd = line.indexOf('/');
                String task = line.substring(9,taskEnd);
                dates[number] = line.substring(taskEnd + 4);
                taskType[number] = "D";
                lists[number++] = task;
                System.out.println(DIVIDER);
                System.out.println("    Got it. I've added this task: ");
                System.out.println("      [D][ ] " + task + " (by: " + dates[number - 1] + ")");
                System.out.println("    Now you have " + number + " tasks in the list");
                System.out.println(DIVIDER);
                line = in.nextLine();
            } else if (line.contains("event")) {
                try {
                    if (!line.contains("/at")) {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    System.out.println(DIVIDER);
                    System.out.println("    ☹ OOPS!!! The description of a event must have '/at' to indicate time.");
                    System.out.println(DIVIDER);
                    line = in.nextLine();
                    continue;
                }
                int taskEnd = line.indexOf('/');
                String task = line.substring(6,taskEnd);
                dates[number] = line.substring(taskEnd + 4);
                taskType[number] = "E";
                lists[number++] = task;
                System.out.println(DIVIDER);
                System.out.println("    Got it. I've added this task: ");
                System.out.println("      [E][ ] " + task + " (by: " + dates[number - 1] + ")");
                System.out.println("    Now you have " + number + " tasks in the list");
                System.out.println(DIVIDER);
                line = in.nextLine();
            } else if (line.contains("delete")) {
                try {
                    int taskDelete = Integer.parseInt(line.substring(7));
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println(DIVIDER);
                    System.out.println("    ☹ OOPS!!! The description of a delete cannot be empty.");
                    System.out.println(DIVIDER);
                    line = in.nextLine();
                    continue;
                }
                int taskDelete = Integer.parseInt(line.substring(7));
                System.out.println(DIVIDER);
                System.out.println("    Noted. I've removed this task:");
                System.out.print("      [" + taskType[taskDelete - 1] + "][" + doneTasks[taskDelete - 1] + "] " + lists[taskDelete - 1]);
                if(taskType[taskDelete - 1].equals("D")) {
                    System.out.println(" (by: " + dates[taskDelete - 1] + ")");
                } else if (taskType[taskDelete - 1].equals("E")) {
                    System.out.println(" (at: " + dates[taskDelete - 1] + ")");
                } else {
                    System.out.println(" ");
                }
                System.out.println("    Now you have " + number-- + " tasks in the list.");
                for (int i = taskDelete - 1; i < number - 1; i++) {
                    lists[i] = lists[i + 1];
                }
                System.out.println(DIVIDER);
                line = in.nextLine();
            } else {
                System.out.println(DIVIDER);
                System.out.println("    ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println(DIVIDER);
                line = in.nextLine();
            }
        }
        System.out.println(DIVIDER);
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println(DIVIDER);
    }
}
