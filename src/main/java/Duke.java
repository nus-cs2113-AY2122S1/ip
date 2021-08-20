import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String line = "____________________________________________________________";
        System.out.println(" Hello! I'm Duke\n" +
                " What can I do for you?");
        System.out.println(line);

        Task[] tasks = new Task[100];
        int index = 0;
        String text;
        text = in.nextLine();
        while (!text.equals("bye")) {
            System.out.println(line);
            if (text.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < index; i++) {
                    Task currentTask = tasks[i];
                    System.out.println(i+1 + ".[" + currentTask.getStatusIcon() + "] " + currentTask.getDescription());
                }
            } else if (text.contains("done")) {
                String textArr[] = text.split(" ");
                int taskNum = Integer.parseInt(textArr[textArr.length - 1]);
                tasks[taskNum - 1].setDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[x] " + tasks[taskNum - 1].getDescription());
            }
            else {
                tasks[index] = new Task(text);
                System.out.println("added: " + text);
                index++;
            }
            System.out.println(line);
            text = in.nextLine();
        }

        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
