import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("_____________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("_____________________________________________");
        task();

    }

    public static void MarkAsDone(Task[] tasks, int taskNum) {
        tasks[taskNum - 1].setDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks[taskNum - 1].getStatusIcon() + " " + tasks[taskNum - 1].getDescription());
    }

    public static void task() {
        int count = 0; //number of task
        Task[] tasks = new Task[100];
        Scanner in = new Scanner(System.in);
        String inputCommand = in.nextLine();
        while (!(inputCommand.equals("bye"))) {
            if (inputCommand.equals("list")) {
                System.out.println("_____________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < count; i++) {

                    System.out.println((i + 1) + "." + tasks[i].getStatusIcon() + " " + tasks[i].getDescription());
                }
                System.out.println("_____________________________________________");
            } else if (inputCommand.contains("done")) {
                int position = inputCommand.indexOf(" ");
                int taskNum = Integer.parseInt(inputCommand.trim().substring(position + 1));
                MarkAsDone(tasks, taskNum);
            } else {
                System.out.println("_____________________________________________");
                tasks[count] = new Task(inputCommand);
                count++;
                System.out.println("added: " + inputCommand);
                System.out.println("_____________________________________________");
            }
            inputCommand = in.nextLine();
        }
        System.out.println("_____________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("_____________________________________________");
    }


}
