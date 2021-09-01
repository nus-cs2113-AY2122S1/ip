import java.util.Scanner;

public class Duke {

    public static final String LINE = "_______________________________________\n";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n" + LINE);
        inputTask();
    }

    public static void inputTask() {
        Scanner in = new Scanner(System.in);
        String inputTask = in.nextLine();
        Task[] tasks = new Task[100];
        int count = 0;

        while(!inputTask.equals("bye")) {
            if (inputTask.equals("list")) {
                System.out.println(LINE + "Here are the tasks in your list:\n");
                for (int i = 0; i < count; i++) {
                    System.out.println((i + 1) + ". " + tasks[i].getStatusIcon() + " " + tasks[i].getDescription());
                }
            } else if (inputTask.contains("done")) {
                int sep = inputTask.indexOf(" ");
                int number = Integer.parseInt(inputTask.substring(5));
                MarkasDone(tasks, number);
            }
            else {
                System.out.println(LINE + "added: " + inputTask);
                tasks[count] = new Task(inputTask);
                count++;
            }
            inputTask = in.nextLine();
        }
        if (inputTask.equals("bye")) {
            System.out.println(LINE + "Bye. Hope to see you again soon!\n");
        }
        in.close();
    }

    public static void MarkasDone(Task[] task, int number) {
        task[number-1].setDone();
        System.out.println(LINE + "Nice! I've marked this task as done:\n");
        System.out.println( task[number-1].getStatusIcon() + " " + task[number-1].getDescription());
    }
}